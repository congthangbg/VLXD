package com.vn.VLXD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.vn.VLXD.common.ERole;
import com.vn.VLXD.common.JwtUtils;
import com.vn.VLXD.common.ResponseBodyDto;
import com.vn.VLXD.contants.MessageConstant;
import com.vn.VLXD.dto.JwtResponse;
import com.vn.VLXD.dto.LoginRequest;
import com.vn.VLXD.dto.MessageResponse;
import com.vn.VLXD.dto.SignupRequest;
import com.vn.VLXD.entities.Role;
import com.vn.VLXD.entities.Account;
import com.vn.VLXD.entities.Authorities;
import com.vn.VLXD.repositories.AuthoritiesRepository;
import com.vn.VLXD.repositories.RoleRepository;
import com.vn.VLXD.repositories.AccountRepository;
import com.vn.VLXD.services.AccountService;
import com.vn.VLXD.services.UserDetailsImpl;

import io.swagger.annotations.ApiOperation;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private AccountService accountService;
    

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return  ResponseEntity.ok(new JwtResponse(jwt, userDetails.getAccount().getId(), userDetails.getAccount().getAccountName(), roles));
    }
    @PostMapping("/signup")
//    @Transactional
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest){
        if (accountRepository.existsByAccountName(signUpRequest.getAccountName())){
        	
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken!","10"));
        }
        if(signUpRequest.getId() != null) {
        	Optional<Account> acOptional = accountRepository.findById(signUpRequest.getId());
        	if(acOptional.isPresent()) {
        		acOptional.get().setAccountName(signUpRequest.getAccountName());
        		acOptional.get().setPassword(encoder.encode(signUpRequest.getPassword()));
        		 Set<String> strRoles = signUpRequest.getRole();
                 Set<Role> roles = new HashSet<>();

                 if (strRoles == null){
                     Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                             .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                     roles.add(userRole);
                 }else {
                     strRoles.forEach(role -> {
                         switch (role){
                             case "ROLE_ADMIN":
                                 Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                         .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                 roles.add(adminRole);
                                 break;
                             case "ROLE_STAFF":
                                 Role modRole = roleRepository.findByName(ERole.ROLE_STAFF)
                                         .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                 roles.add(modRole);
                                 break;
                             default:
                                 Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                         .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                 roles.add(userRole);
                         }
                     });
                 }
                 acOptional.get().setRoles(roles);
                 accountRepository.save( acOptional.get());
                 return ResponseEntity.ok(new ResponseBodyDto<>());
        	}
        }else {

            Account account = new Account(signUpRequest.getAccountName(),encoder.encode(signUpRequest.getPassword()));
            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null){
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            }else {
                strRoles.forEach(role -> {
                    switch (role){
                        case "ROLE_ADMIN":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);
                            break;
                        case "ROLE_STAFF":
                            Role modRole = roleRepository.findByName(ERole.ROLE_STAFF)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(modRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }
            account.setRoles(roles);
            accountRepository.save(account);
        	for (Role role2 : roles) {
    			Authorities authorities = new Authorities();
    			authorities.setAccountId(account.getId());
    			authorities.setRoleId(role2.getId());
    			
    			authoritiesRepository.save(authorities);
    		}
//        	 return ResponseEntity.ok(new MessageResponse("User registered Successfully! "));
            return ResponseEntity.ok(new ResponseBodyDto<>());
        }
		return null;
   
    }
    
    @GetMapping("listAccount")
    @ApiOperation(value = "Danh sách All")
    public ResponseBodyDto<Object> findAllSearch(
    		@RequestParam(value = "keySearch",required = false) String keySearch,
    		@RequestParam(value = "page",required = false) Optional<Integer> page,
    		@RequestParam(value = "size",required = false) Optional<Integer> size ) {
    	int currentPage = page.orElse(0);
    	int limit = size.orElse(100);
    	Pageable pageable = PageRequest.of(currentPage, limit, Sort.by("id").descending());
    	ResponseBodyDto<Object> dto = accountService.findAllSearch(keySearch,pageable);
    	
        return dto;
    }
    @GetMapping("listRolse")
    @ApiOperation(value = "Danh sách role")
    public ResponseBodyDto<Object> findAllRole(@RequestParam(value = "page",required = false) Optional<Integer> page,
    		@RequestParam(value = "size",required = false) Optional<Integer> size) {
    	int currentPage = page.orElse(0);
    	int limit = size.orElse(100);
    	Pageable pageable = PageRequest.of(currentPage, limit, Sort.by("id").descending());
    	ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
    	Page<Role> roles = roleRepository.findAll(pageable);
    	dto.setData(roles.getContent());
    	
        return dto;
    }

    @GetMapping("delete/{id}")
    public 	ResponseBodyDto<Object>  deleteAccount(@PathVariable Long id) {
    	ResponseBodyDto<Object> dto = new ResponseBodyDto<>();
    	Optional<Account> account = accountRepository.findById(id);
    	if(account.isPresent()) {
    		account.get().setStatus(0L);
    		accountRepository.save(account.get());
    		return dto;
    		
    	}else {
    		dto.setMessage(MessageConstant.MSG_10);
    		dto.setMessageCode(MessageConstant.MSG_10_CODE);
    		return dto;
    	}
    }
}

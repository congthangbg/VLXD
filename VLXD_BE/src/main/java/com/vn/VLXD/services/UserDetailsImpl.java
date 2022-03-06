package com.vn.VLXD.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.VLXD.entities.Account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private Account account;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String accountName, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.account = new Account(id, accountName, password);
		this.authorities = authorities;
	}


    public static UserDetailsImpl build(Account account){
        List<GrantedAuthority> authorities = account.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserDetailsImpl(
                account.getId(),
                account.getAccountName(),
                account.getPassword(),
                authorities
        );
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getAccountName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Account getAccountLogin() {
		return account;
	}
}

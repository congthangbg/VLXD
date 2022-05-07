package com.vn.VLXD.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
	 private Long id;
    private String accountName;
    private String password;
    private Set<String> role;

  
}

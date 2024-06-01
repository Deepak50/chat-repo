package com.prj.chatapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
	private String access_token;
	private String expires_in;
	private String scope;
	private String token_type;
	private String id_token;
}

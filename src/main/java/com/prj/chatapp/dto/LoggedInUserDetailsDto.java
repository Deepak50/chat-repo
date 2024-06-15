package com.prj.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInUserDetailsDto {
	private String username;
	private String name;
	private String givenName;
	private String picture;
	private String familyName;
}

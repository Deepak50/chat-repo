package com.prj.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {
	private Integer statusCode;
	private String status;
	private String message;
	private Object Data;
}

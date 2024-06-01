package com.prj.chatapp.model;

import lombok.Data;

@Data
public class ResponseModel {
	private String statusCode;
	private String status;
	private String message;
}

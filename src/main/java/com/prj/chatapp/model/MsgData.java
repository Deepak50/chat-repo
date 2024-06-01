package com.prj.chatapp.model;

import lombok.Data;

@Data
public class MsgData {
	private String endpoint;
	private String to;
	private String message;
	
	private MsgData(){
		this.message = "Some message!!";
	}
}

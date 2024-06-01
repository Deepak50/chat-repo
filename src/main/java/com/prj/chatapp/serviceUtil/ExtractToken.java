package com.prj.chatapp.serviceUtil;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;

public class ExtractToken {

	public JSONObject decodeJwt(String token) throws UnsupportedEncodingException {
		String payload = token.split("\\.")[1];
		String jsonString = new String(Base64.decodeBase64(payload), "UTF-8");
		JSONObject json = new JSONObject(jsonString);
		return json;
	}

}

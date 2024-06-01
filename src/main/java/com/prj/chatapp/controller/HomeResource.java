package com.prj.chatapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HomeResource{

//    @GetMapping("/home")
//    public String home(OAuth2AuthenticationToken user)  {
//    	OAuth2AuthenticationToken a = (OAuth2AuthenticationToken) user;
//    	
//    	Map<String, Object> m = user.getPrincipal().getAttributes();
//        return ("<h1>Welcome</h1>"+m.get("given_name"));
//    }
    
    @GetMapping("/usr")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }
}


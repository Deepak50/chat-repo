package com.prj.chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@RequestMapping("/socket")
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
	@CrossOrigin(origins = "https://chatappui-gpqj.onrender.com", allowedHeaders = {"Requestor-Type", "Authorization"}, exposedHeaders = "X-Get-Header")
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp-endpoint")
        		.setAllowedOrigins("https://chatappui-gpqj.onrender.com")
                .withSockJS();
    }

	@CrossOrigin(origins = "https://chatappui-gpqj.onrender.com", allowedHeaders = {"Requestor-Type", "Authorization"}, exposedHeaders = "X-Get-Header")
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	registry.enableSimpleBroker("/user", "/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");

    }
	
}

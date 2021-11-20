package com.ssafy.happyhouse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

@Override
public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS(); //웹 소캣을 사용하기 위해 설정하는 부분
}

//메세지 브로커 등록
@Override
public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.setApplicationDestinationPrefixes("/"); //도착경로 에 대한 prefix 설정
    
    registry.enableSimpleBroker("/topic"); //한명이 message를 발행했을때 해당토픽을 구독하고 있는 n명에게 메시지 뿌려야 
}
}
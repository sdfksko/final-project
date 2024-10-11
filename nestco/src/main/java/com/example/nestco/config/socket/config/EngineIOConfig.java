//package com.example.nestco.config.socket.config;
//
//import com.example.nestco.config.socket.handler.EngineIoHandler;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
///**
// * Socket.IO와 WebSocket을 사용하는 통신 환경을 구축
// */
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSocket
//public class EngineIOConfig implements WebSocketConfigurer {
//
//    private final EngineIoHandler mEngineIoHandler;
//    private final WebSocketHandler webSocketHandler;    //  기본 WebSocket 연결을 처리하는 일반적인 핸들러
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(mEngineIoHandler,"/socket.io/") //클라이언트가 /socket.io/ 경로로 연결을 요청하면, EngineIoHandler가 이를 처리하여 실시간 통신을 설정
//                .addInterceptors(mEngineIoHandler) //추가적인 요청 가로채기나 연결 시 작업을 처리
//                .setAllowedOrigins("http://localhost:")
//                .withSockJS();    //  CORS 설정: 모든 도메인에서 이 서버에 WebSocket 연결을 허용
//    }
//}

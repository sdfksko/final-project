//package com.example.nestco.config.socket.config;
//
//import io.socket.engineio.server.EngineIoServer;
//import io.socket.engineio.server.EngineIoServerOptions;
//import io.socket.socketio.server.SocketIoServer;
//import io.socket.socketio.server.SocketIoSocket;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Socket.IO 서버를 설정하고 서버의 기본적인 동작을 관리
// * args[?] : 클라이언트가 이벤트를 서버로 보낼 때 여러 데이터를 함께 보낼 수 있으며, args 배열을 통해 이러한 데이터를 서버에서 순서대로 받아서 사용할 수 있음
// */
//@Configuration
//public class SocketIOConfig {
//
//    @Bean
//    EngineIoServer engineIoServer() {    //기본적인 WebSocket 통신을 처리하는 서버
//        // Engine.IO 서버 옵션 설정(WebSocket, Long Polling 지원)
//        EngineIoServerOptions options = EngineIoServerOptions.newFromDefault();
//        options.setCorsHandlingDisabled(true);  // CORS 설정 비활성화 (필요시 설정)
//        return new EngineIoServer(options);     // 엔진IO 서버 생성
//    }
//
//    /**
//     * 실제 Socket.IO 서버
//     *  on("connection") 이벤트 핸들러는 새로운 연결이 발생할 때 동작하며, 사용자가 메시지를 보낼 때 이를 받아 처리하고 응답
//     * @param engineIoServer
//     * @return
//     */
//    @Bean
//    SocketIoServer socketIoServer(EngineIoServer engineIoServer) {
//        // Socket.IO 서버를 Engine.IO 기반으로 생성
//        SocketIoServer socketIoServer = new SocketIoServer(engineIoServer);
//
//        // 네임스페이스(소켓을 묶어주는 단위) 설정 (IO -> NameSpace -> Room -> Socket ) :해당 경로로 들어오는 클라이언트의 연결 관리
//        var namespace = socketIoServer.namespace("/ws");
//
//        // 새로운 클라이언트가 연결되었을 때 실행될 핸들러 등록 >> 연결종료는 disconnect 사용
//        namespace.on("connection", args -> {
//            SocketIoSocket socket = (SocketIoSocket) args[0];   // 연결된 클라이언트 소켓
//
//            socket.send("welcome", "연결 성공!");   // 클라이언트에게 연결 성공 메시지 전송
//
//            // 클라이언트가 보낸 메시지를 수신하는 핸들러
//            socket.on("message", messageArgs -> {
//                String message = (String) messageArgs[0];   // 메세지 내용
//                socket.send("reply", "서버가 받은 메시지: " + message); // 응답 전송
//            });
//        });
//        // 교환 수락 후 채팅 방 생성 로직
//        namespace.on("accept_exchange", args -> {
//            var socket = (SocketIoSocket) args[0];
//            var exchangeId = (String) args[1];
//
//            // 교환 ID로 채팅 방을 생성
//            socket.joinRoom("exchange_" + exchangeId);
//
//            // 상대방에게 교환 수락 알림과 함께 채팅 방 초대
//            socket.("exchange_" + exchangeId).send("chat_invite", "상대방과의 채팅이 시작되었습니다.");
//        });
//
//// 채팅 메시지 처리(실시간)
//        namespace.on("chat_message", args -> {
//            var socket = (SocketIoSocket) args[0];
//            var message = (String) args[1];
//            var exchangeId = (String) args[2];
//
//            // 같은 교환 방에 있는 사용자에게 메시지 전달
//            socket.to("exchange_" + exchangeId).send("chat_message", message);
//        });
//
//        return socketIoServer;
//    }
//
//}

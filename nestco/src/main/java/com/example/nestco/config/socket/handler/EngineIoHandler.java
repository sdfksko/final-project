package com.example.nestco.config.socket.handler;

import io.socket.socketio.server.SocketIoSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EngineIoHandler {
    public void onConnect(SocketIoSocket socket) {
        log.info("새로운 클라이언트 연결: {}", socket.getId());   // 연결된 클라이언트의 ID 로그 기록
        socket.send("connected", "서버에 연결되었습니다!");   // 연결 알림 메시지 전송

        // 메시지 수신 이벤트 처리
        socket.on("message", args -> {
            String message = (String) args[0];  // 수신된 메시지
            log.info("수신된 메시지: {}", message);   // 로그로 메시지 출력
            socket.send("reply", "서버에서 메시지를 받았습니다: " + message);    // 응답 메시지 전송
        });

        // 연결 해제 이벤트 처리
        socket.on("disconnect", args -> {
            log.info("클라이언트 연결 해제: {}", socket.getId());    // 연결 해제 로그 기록
        });
    }
}

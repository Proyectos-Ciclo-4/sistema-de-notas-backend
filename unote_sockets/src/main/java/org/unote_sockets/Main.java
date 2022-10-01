package org.unote_sockets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(
                Main.class, args
        );
    }
}
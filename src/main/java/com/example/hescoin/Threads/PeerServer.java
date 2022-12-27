package com.example.hescoin.Threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PeerServer extends Thread {

    private ServerSocket serverSocket;

    public PeerServer(Integer socketPort) throws IOException {

        this.serverSocket = new ServerSocket(socketPort);

    }

    public void run() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Thread peerRequest = new PeerRequestThread(socket);
                peerRequest.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

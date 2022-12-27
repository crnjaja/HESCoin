package com.example.hescoin.Threads;

import com.example.hescoin.Model.Block;
import com.example.hescoin.ServiceData.BlockchainData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class PeerRequestThread extends Thread {

    private Socket socket ;

    public PeerRequestThread(Socket socket) {
        this.socket = socket ;
    }

    public void run(){
        try {
            ObjectOutputStream objectOuput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());

            LinkedList<Block> recievedBlockchain = (LinkedList<Block>) objectInput.readObject();
            System.out.println("LedgerID" + recievedBlockchain.getLast().getLedgerId());
            objectOuput.writeObject(BlockchainData.getInstance().getBlockchainConsensus(recievedBlockchain));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

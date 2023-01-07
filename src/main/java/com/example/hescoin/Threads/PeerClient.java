package com.example.hescoin.Threads;

import com.example.hescoin.Model.Block;
import com.example.hescoin.ServiceData.BlockchainData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class PeerClient extends Thread {

    public PeerClient(){

    }
    public void run(){

        // while true fait qu'il run tout le temps
        while(true){
            try {
                Socket socket = new Socket("127.0.0.1", 65001);

                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                LinkedList<Block> blockChain = BlockchainData.getInstance().getCurrentBlockChain();
                outputStream.writeObject(blockChain);

                LinkedList<Block> returnedBlockchain = (LinkedList<Block>) inputStream.readObject();
                BlockchainData.getInstance().getBlockchainConsensus(returnedBlockchain);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

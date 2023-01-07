package com.example.hescoin.Threads;

import com.example.hescoin.Model.Block;
import com.example.hescoin.ServiceData.BlockchainData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;

public class PeerClient extends Thread {

    public PeerClient(){

    }
    public void run(){

        // while true fait qu'il run tout le temps
        while(true){
            try {
                InetAddress serverAddress;
                String serverName = "192.168.108.10";
                serverAddress = InetAddress.getByName(serverName);
                Socket socket = new Socket(serverAddress, 45000);

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

package com.example.hescoin.Threads;

import com.example.hescoin.ServiceData.BlockchainData;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MiningThread extends Thread {

    @Override
    public void run() {
        while(true){

            //milisecond timestamp
            long lastMinedBlock = LocalDateTime.parse(BlockchainData.getInstance().getCurrentBlockChain()
                    .getLast().getTimeStamp()).toEpochSecond(ZoneOffset.UTC);

            if(lastMinedBlock + BlockchainData.getMiningInterval() < LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)){
                System.out.println("Too old for mining");
            } else if (((lastMinedBlock + BlockchainData.getMiningInterval()) - LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) > 0) {
                System.out.println("Blockchain is current, it will start shortly");
            }
            else{
                System.out.println("MINING NEW BLOCK");
                BlockchainData.getInstance().mineBlock();
                System.out.println(BlockchainData.getInstance().getWalletBallanceFX());
            }
            try {
                Thread.sleep(2000);
                if (BlockchainData.getInstance().isExit()){
                    break;
                }
                BlockchainData.getInstance().setMiningPoints(BlockchainData.getInstance().getMiningPoints()+2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

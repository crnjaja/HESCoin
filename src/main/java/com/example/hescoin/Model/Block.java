package com.example.hescoin.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Block implements Serializable {

    private byte[] prevHash;
    private byte[] currHash;
    private String timestamp;
    private byte[] mineBy;
    private Integer legderIP = 0;
    private Integer miningPoint = 0;
    private double luck = 0.0;

    private ArrayList<Transaction> transactionLedger = new ArrayList<>();

    public Block(byte[] prevHash, byte[] currHash, String timestamp, byte[] mineBy, Integer legderIP, Integer miningPoint, double luck, ArrayList<Transaction> transactionLedger) {
        this.prevHash = prevHash;
        this.currHash = currHash;
        this.timestamp = timestamp;
        this.mineBy = mineBy;
        this.legderIP = legderIP;
        this.miningPoint = miningPoint;
        this.luck = luck;
        this.transactionLedger = transactionLedger;
    }
}

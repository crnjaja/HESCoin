package com.example.hescoin.ServiceData;

import com.example.hescoin.Model.Wallet;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.*;
import java.security.spec.*;

public class WalletData {

    private Wallet wallet;

    private static WalletData instance;

    static {
        instance = new WalletData();
    }

    public static WalletData getInstance(){ return instance;}

    public void loadWallet() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {

        //getting datafrom database
        Connection walletConnection = DriverManager.getConnection("jdbc:sqlite:db\\wallet.db");
        Statement walletStatment = walletConnection.createStatement();
        ResultSet resultSet;
        resultSet = walletStatment.executeQuery("SELECT * FROM WALLET");
        KeyFactory keyFactory = KeyFactory.getInstance("DSA");
        PublicKey pub2 = null;
        PrivateKey prv2 = null;
        while(resultSet.next()){
            pub2 = keyFactory.generatePublic(new X509EncodedKeySpec(resultSet.getBytes("PUBLIC_KEY")));
            prv2 = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(resultSet.getBytes("PRIVATE_KEY")));
        }
        this.wallet = new Wallet(pub2, prv2);

    }
    public Wallet getWallet(){return wallet;}
}

package com.example.hescoin;

import com.example.hescoin.Model.*;
import com.example.hescoin.ServiceData.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.*;
import java.sql.*;
import java.time.LocalDateTime;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        Connection walletConnection = DriverManager
                .getConnection("jdbc:sqlite:C:\\db\\wallet.db");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init(){
        try {
            //This creates your wallet if there is none and gives you a KeyPair.
            //We will create it in separate db for better security and ease of portability.
            Connection walletConnection = DriverManager
                    .getConnection("jdbc:sqlite:C:\\db\\wallet.db");
            Statement walletStatment = walletConnection.createStatement();

            ResultSet resultSet = walletStatment.executeQuery(" SELECT * FROM WALLET ");
            if (!resultSet.next()) {
                Wallet newWallet = new Wallet();
                byte[] pubBlob = newWallet.getPublicKey().getEncoded();
                byte[] prvBlob = newWallet.getPrivateKey().getEncoded();
                PreparedStatement pstmt = walletConnection
                        .prepareStatement("INSERT INTO WALLET(PRIVATE_KEY, PUBLIC_KEY) " +
                                " VALUES (?,?) ");
                pstmt.setBytes(1, prvBlob);
                pstmt.setBytes(2, pubBlob);
                pstmt.executeUpdate();
            }
            resultSet.close();
            walletStatment.close();
            walletConnection.close();


            Connection blockchainConnection = DriverManager
                    .getConnection("jdbc:sqlite:C:\\db\\blockchain.db");
            Statement blockchainStmt = blockchainConnection.createStatement();
            ResultSet resultSetBlockchain = blockchainStmt.executeQuery(" SELECT * FROM BLOCKCHAIN ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
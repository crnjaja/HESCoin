package com.example.hescoin;


import com.example.hescoin.Threads.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.*;

public class HEScoin extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new UI().start(stage);
        new PeerClient().start();
        new PeerServer(6000).start();
        new MiningThread().start();
    }

    public static void main(String[] args) {

        launch(args);

    }


    public void init() {
        try {
            Connection blockchainConnection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");

            // INSERT example
            PreparedStatement pstmt = blockchainConnection.prepareStatement("INSERT INTO BLOCKCHAIN(PREVIOUS_HASH, CURRENT_HASH, LEDGER_ID," +
                    "CREATED_ON, CREATED_BY, MINING_POINTS, LUCK)" +
                    "VALUES(?,?,?,?,?,?,?)" );
            pstmt.setBytes(1, );
            pstmt.setBytes(2, );
            pstmt.setInt(3, );

            // SELECT example
            Statement blockchainstatement = blockchainConnection.createStatement();
            ResultSet resultSet = blockchainstatement.executeQuery("SELECT * FROM BLOCKCHAIN");
            if(resultSet.next()){
                String firstColumn = resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

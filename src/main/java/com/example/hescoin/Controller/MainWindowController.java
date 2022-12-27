package com.example.hescoin.Controller;

import com.example.hescoin.Model.Transaction;
import com.example.hescoin.ServiceData.BlockchainData;
import com.example.hescoin.ServiceData.WalletData;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.util.Base64;

public class MainWindowController {

    @FXML
    public TableView<Transaction> tableView = new TableView<>();
    @FXML
    private TableColumn<Transaction, String> from;
    @FXML
    private TableColumn<Transaction, String> to;
    @FXML
    private TableColumn<Transaction, Integer> value;
    @FXML
    private TableColumn<Transaction, String> timestamp;
    @FXML
    private TableColumn<Transaction, String> signature;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField eCoins;
    @FXML
    private TextArea publicKey;

    public void initialize(){
        // la key est convertie en un string
        Base64.Encoder encoder = Base64.getEncoder();
        from.setCellValueFactory(new PropertyValueFactory<>("fromFX"));
        to.setCellValueFactory(new PropertyValueFactory("toFX"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));
        signature.setCellValueFactory(new PropertyValueFactory<>("signatureFX"));
        timestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        eCoins.setText(BlockchainData.getInstance().getWalletBallanceFX());
        publicKey.setText(encoder.encodeToString(WalletData.getInstance().getWallet().getPublicKey().getEncoded()));

        tableView.setItems(BlockchainData.getInstance().getTransactionLedgerFX());
        // le cursor est sur la 1ere ligne
        tableView.getSelectionModel().select(0);


    }
    public void toNewTransactionController(){

    }
    public void refresh(){

    }
    public void handleExit(){

    }


}

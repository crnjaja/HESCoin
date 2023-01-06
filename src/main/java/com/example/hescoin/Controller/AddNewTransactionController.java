package com.example.hescoin.Controller;

import com.example.hescoin.Model.Transaction;
import com.example.hescoin.ServiceData.BlockchainData;
import com.example.hescoin.ServiceData.WalletData;
import javafx.fxml.FXML;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.security.GeneralSecurityException;
import java.security.Signature;
import java.sql.Blob;
import java.util.Base64;

public class AddNewTransactionController {


    @FXML
    private DialogPane dialogPanePane;
    @FXML
    private TextField toAddress;
    @FXML
    private TextField value ;

    @FXML
    public void createNewTransaction() throws GeneralSecurityException {
        Base64.Decoder decoder = Base64.getDecoder();
        Signature signing = Signature.getInstance("SHA256withRSA");
        Integer ledgerId = BlockchainData.getInstance().getTransactionLedgerFX().get(0).getLedgerId();
        byte[] sendB = decoder.decode(toAddress.getText());
        Transaction transaction = new Transaction(WalletData.getInstance()
                .getWallet(),sendB ,Integer.parseInt(value.getText()), ledgerId, signing);
        BlockchainData.getInstance().addTransaction(transaction,false);
        BlockchainData.getInstance().addTransactionState(transaction);
    }

}

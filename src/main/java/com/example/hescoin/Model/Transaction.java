package com.example.hescoin.Model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

public class Transaction implements Serializable {

    private byte[] from;
    private String fromFX;
    private byte[] to;
    private String toFX;
    private Integer value;
    private String timestamp;
    private byte[] signature;
    private  String signatureFX;
    private Integer ledgerId;


    //Constructor for loading with existing signature
    public Transaction(byte[] from, byte[] to, Integer value, byte[] signature, Integer ledgerId,
                       String timeStamp) {
        Base64.Encoder encoder = Base64.getEncoder();
        this.from = from;
        this.fromFX = encoder.encodeToString(from);
        this.to = to;
        this.toFX = encoder.encodeToString(to);
        this.value = value;
        this.signature = signature;
        this.signatureFX = encoder.encodeToString(signature);
        this.ledgerId = ledgerId;
        this.timestamp = timeStamp;
    }

    //Constructor for creating a new transaction and signing it.
    public Transaction (Wallet fromWallet, byte[] toAddress, Integer value, Integer ledgerId,
                        Signature signing) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
        Base64.Encoder encoder = Base64.getEncoder();
        this.from = fromWallet.getPublicKey().getEncoded();
        this.fromFX = encoder.encodeToString(fromWallet.getPublicKey().getEncoded());
        this.to = toAddress;
        this.toFX = encoder.encodeToString(toAddress);
        this.value = value;
        this.ledgerId = ledgerId;
        this.timestamp = LocalDateTime.now().toString();
        signing.initSign(fromWallet.getPrivateKey(), SecureRandom.getInstanceStrong());
        String sr = this.toString();
        signing.update(sr.getBytes());
        this.signature = signing.sign();
        this.signatureFX = encoder.encodeToString(this.signature);
    }



    public Boolean isVerified(Signature signing)
            throws InvalidKeyException, SignatureException, InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(this.getFrom());
        RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        signing.initVerify(publicKey);
        signing.update(this.toString().getBytes());
        return signing.verify(this.signature);
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "from=" + Arrays.toString(from) +
                ", to=" + Arrays.toString(to) +
                ", value=" + value +
                ", timeStamp= " + timestamp +
                ", ledgerId=" + ledgerId +
                '}';
    }

    public byte[] getFrom() { return from; }
    public void setFrom(byte[] from) { this.from = from; }

    public byte[] getTo() { return to; }
    public void setTo(byte[] to) { this.to = to; }

    public Integer getValue() { return value; }
    public void setValue(Integer value) { this.value = value; }
    public byte[] getSignature() { return signature; }

    public Integer getLedgerId() { return ledgerId; }
    public void setLedgerId(Integer ledgerId) { this.ledgerId = ledgerId; }

    public String getTimestamp() { return timestamp; }

    public String getFromFX() { return fromFX; }
    public String getToFX() { return toFX; }
    public String getSignatureFX() { return signatureFX; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Arrays.equals(from, that.from) &&
                Arrays.equals(to, that.to) &&
                value.equals(that.value) &&
                timestamp.equals(that.timestamp) &&
                ledgerId.equals(that.ledgerId);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(from);
        result = 31 * result + Arrays.hashCode(to);
        result = 31 * result + value.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + ledgerId.hashCode();
        return result;
    }

}
package com.example.hescoin.Model;

import java.io.Serializable;

public class Transaction implements Serializable {

    private byte[] from;
    private String fromFX;
    private byte[] to;
    private String toFX;
    private Integer value;
    private String ;
    private byte[] signature;
    private String signatureFX;
    private Integer ledgerId;

}

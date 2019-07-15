package com.dustess.sdk;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class EncryptHelperTest {
    private String textToEncrypt = "将要加密的字符";
    private String key ="5dd69f95-a82a-4b01-b6c1-a2d2f8f8061b";
    private String encryptedText = "neD0iV1eFLpIy4/F1XHFEomEfx2kV/EPMg+edO9TU/8=";

    @Test
    public void encode() throws Exception {

        String encrypted = EncryptHelper.encode(textToEncrypt, key);
        Assert.assertEquals(encryptedText, encrypted);

        String expected="b3S32/AUQV226VWeiB9KUqRNfsjPH2AsFiKaoOriiu8=";
        Assert.assertEquals(expected,EncryptHelper.encode(textToEncrypt,"12af99efb03f4ca7853a31f3977062eb"));
    }

    @Test
    public void decode() throws Exception {
        String blankText = EncryptHelper.decode(encryptedText, key);
        Assert.assertEquals(textToEncrypt, blankText);

        String test="suqkH4wvafV94QFhIjbBk3eTB8T1Uc9sE0Ez+medUu8W++KdbohO6pF0mY4JQTibERuDQZf+WOLydvs+RaKlRw==";
        System.out.println(EncryptHelper.decode(test,"ec0c9d8c9a2b4162ab09da7d476e0cad"));
    }
}
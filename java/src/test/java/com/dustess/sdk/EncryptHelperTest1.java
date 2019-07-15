package com.dustess.sdk;

import org.junit.Assert;
import org.junit.Test;

public class EncryptHelperTest1 {

    private String textToEncrypt = "{category: 'card',compId: 20002054,appId: '59cf456794d36ad251d5cc9e',time: 1506769933223,data:{userId: 'wx_134447',uniqueId: 'adfe550c510a43be07f864bf455dbaef39',mobile: '15858110505',name: '15858110505'}}";
    private String key ="12af99efb03f4ca7853a31f3977062eb";
    private String encryptedText = "b3S32/AUQV226VWeiB9KUqRNfsjPH2AsFiKaoOriiu8=";

    @Test
    public void encode() throws Exception {

        String encrypted = EncryptHelper.encode(textToEncrypt, key);
        System.out.println(encrypted);
        String blankText = EncryptHelper.decode(encrypted, key);
        System.out.println(blankText);
    }

    @Test
    public void decode() throws Exception {
        // String blankText = EncryptHelper.decode(encryptedText, key);
        // Assert.assertEquals(textToEncrypt, blankText);
    }
}

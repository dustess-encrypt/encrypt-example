<?php

class EncryptHelper
{

    private static $iv = "0123456789ABCDEF";

    public static function encrypt($decrypted_data, $secret_key)
    {
        if (empty($secret_key)) {
            return null;
        }
        $encrypted = openssl_encrypt($decrypted_data, 'AES-128-CBC',md5($secret_key, true),OPENSSL_RAW_DATA,EncryptHelper::$iv);
        $encrypted_data = base64_encode($encrypted);
        return $encrypted_data;
    }


    public static function decrypt($encrypted_data, $secret_key)
    {
        if (empty($secret_key)) {
            return null;
        }
        $encrypted_data = base64_decode($encrypted_data);
        $decrypted = openssl_decrypt($encrypted_data, 'AES-128-CBC',  md5($secret_key, true), OPENSSL_RAW_DATA, EncryptHelper::$iv);

        return $decrypted;
    }


}
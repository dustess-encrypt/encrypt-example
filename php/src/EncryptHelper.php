<?php

class EncryptHelper
{

    private static $iv = "0123456789ABCDEF";

    public static function encrypt($decrypted_data, $secret_key)
    {
        if (empty($secret_key)) {
            return null;
        }

        $blocksize = mcrypt_get_block_size(MCRYPT_RIJNDAEL_128, MCRYPT_MODE_CBC);
        $padded_data = EncryptHelper::pkcs5_pad($decrypted_data, $blocksize);
        $encrypted = mcrypt_encrypt(MCRYPT_RIJNDAEL_128, md5($secret_key, true),
            $padded_data, MCRYPT_MODE_CBC, EncryptHelper::$iv);
        $encrypted_data = base64_encode($encrypted);
        return $encrypted_data;
    }


    public static function decrypt($encrypted_data, $secret_key)
    {
        if (empty($secret_key)) {
            return null;
        }
        $encrypted_data = base64_decode($encrypted_data);
        $decrypted = mcrypt_decrypt(MCRYPT_RIJNDAEL_128, md5($secret_key, true), $encrypted_data, MCRYPT_MODE_CBC, EncryptHelper::$iv);
        $decrypted_data = EncryptHelper::pkcs5_unpad($decrypted, "\0");
        return $decrypted_data;
    }

    public static function pkcs5_pad($text, $blocksize)
    {
        $pad = $blocksize - (strlen($text) % $blocksize);
        return $text . str_repeat(chr($pad), $pad);
    }

    public static function pkcs5_unpad($text)
    {
        $pad = ord($text{strlen($text) - 1});
        if ($pad > strlen($text)) {
            return false;
        }
        if (strspn($text, chr($pad), strlen($text) - $pad) != $pad) {
            return false;
        }
        return substr($text, 0, -1 * $pad);
    }
}
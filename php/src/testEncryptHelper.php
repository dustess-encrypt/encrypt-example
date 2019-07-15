<?php
include 'EncryptHelper.php';

$helper = new EncryptHelper();
$textToEncrypt = "将要加密的字符";
$key = "5dd69f95-a82a-4b01-b6c1-a2d2f8f8061b";
$encryptedText = "neD0iV1eFLpIy4/F1XHFEomEfx2kV/EPMg+edO9TU/8=";


if (strcmp($helper->encrypt($textToEncrypt, $key), $encryptedText) != 0) {
    throw new Exception('found error when encode string');
}
if (strcmp($helper->decrypt($encryptedText, $key), $textToEncrypt) != 0) {
    throw new Exception('found error when decode string');
}
?>
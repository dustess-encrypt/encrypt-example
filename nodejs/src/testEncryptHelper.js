var helper = require('./EncryptHelper.js');
var crypto = require('crypto');
var textToEncrypt = '将要加密的字符';
var key = '5dd69f95-a82a-4b01-b6c1-a2d2f8f8061b';
var encryptedText = 'neD0iV1eFLpIy4/F1XHFEomEfx2kV/EPMg+edO9TU/8=';

console.log(helper.encrypt(textToEncrypt,key) == encryptedText);
console.log(helper.decrypt(encryptedText,key) == textToEncrypt);
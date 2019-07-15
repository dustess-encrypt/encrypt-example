var crypto = require('crypto');
var helper = (function () {
    var iv = '0123456789ABCDEF';
    var clearEncoding = 'utf8';
    var cipherEncoding = 'base64';
    var getKey=function (key) {
        var md5 = crypto.createHash('md5');
        var hexString = md5.update(key).digest('hex');
        hexString = hexString.replace(/(\w{2,2})/g, '0x$1 ').trim();
        var arr = hexString.split(' ');
        return Buffer.from(arr);
    };
    return {
        encrypt: function (plain, key) {
            var cipherChunks = [];

            var cipher = crypto.createCipheriv('aes-128-cbc', getKey(key), iv);
            cipher.setAutoPadding(true);
            cipherChunks.push(cipher.update(plain, clearEncoding, cipherEncoding));
            cipherChunks.push(cipher.final(cipherEncoding));
            return cipherChunks.join('');
        },
        decrypt: function (encrypted, key) {
            var decipher = crypto.createDecipheriv('aes-128-cbc',getKey(key), iv);
            decipher.setAutoPadding(true);
            var cipherChunks = [];
            cipherChunks.push(decipher.update(encrypted, cipherEncoding, clearEncoding));
            cipherChunks.push(decipher.final(clearEncoding));
            return cipherChunks.join('');
        }
    }
})();
module.exports=helper;
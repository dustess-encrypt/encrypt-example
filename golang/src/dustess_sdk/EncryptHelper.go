package dustess

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/md5"
	"encoding/base64"
	"io"
)

const HEX string = "0123456789ABCDEF"

func Encrypt(plainText string, key string) (string, error) {
	h := md5.New()
	io.WriteString(h, key)
	keyBytes := h.Sum(nil)
	plainBytes := []byte(plainText)

	block, err := aes.NewCipher(keyBytes)
	if err != nil {
		return "", err
	}

	ecb := cipher.NewCBCEncrypter(block, []byte(HEX))

	plainBytes = PKCS5Padding(plainBytes, block.BlockSize())
	crypted := make([]byte, len(plainBytes))
	ecb.CryptBlocks(crypted, plainBytes)

	return base64.StdEncoding.EncodeToString(crypted), nil

}
func Decrypt(encrypted string, key string) (string, error) {
	h := md5.New()
	io.WriteString(h, key)
	keyBytes := h.Sum(nil)

	block, err := aes.NewCipher(keyBytes)
	if err != nil {
		return "", err
	}
	encryptedBytes, err := base64.StdEncoding.DecodeString(encrypted)
	if err != nil {
		return "", err
	}

	ecb := cipher.NewCBCDecrypter(block, []byte(HEX))
	decryptedBytes := make([]byte, len(encryptedBytes))
	ecb.CryptBlocks(decryptedBytes, encryptedBytes)

	decryptedBytes = PKCS5Trimming(decryptedBytes)
	return string(decryptedBytes[:]), nil
}
func PKCS5Padding(ciphertext []byte, blockSize int) []byte {
	padding := blockSize - len(ciphertext)%blockSize
	padtext := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(ciphertext, padtext...)
}

func PKCS5Trimming(encrypt []byte) []byte {
	padding := encrypt[len(encrypt)-1]
	return encrypt[:len(encrypt)-int(padding)]
}

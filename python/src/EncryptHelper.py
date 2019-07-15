#!/usr/bin/env python
# -*- coding: utf-8 -*-
from Crypto.Cipher import AES
import base64
import hashlib
PADDING = '\0'

def _unpadding(str):
    l = len(str)
    if l == 0 :
        return str
    asc = ord(str[-1])
    if asc >= 16:
        return str
    return str[0:-asc]

def _padding(data):
    length = 16 - (len(data.encode('utf-8')) % 16)
    data += chr(length)*length
    return data

def _cipher(key):
    iv = '0123456789ABCDEF'
    m2 = hashlib.md5()
    m2.update(key.encode('utf-8'))
    return AES.new(key=m2.digest(), mode=AES.MODE_CBC, IV=iv)

class EncryptHelper:
    def __init__(self):
        pass

    @staticmethod
    def encrypt(plain_text, key):
        data=_cipher(key).encrypt(_padding(plain_text))
        return str(base64.b64encode(data),'utf-8')

    @staticmethod
    def decrypt(encrypted_text, key):
        data = base64.b64decode(encrypted_text)
        bytes= _cipher(key).decrypt(data)
        blank=str(bytes,'utf-8').strip()
        return _unpadding(blank)





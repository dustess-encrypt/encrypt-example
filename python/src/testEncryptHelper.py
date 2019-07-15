#!/usr/bin/env python
# -*- coding: utf-8 -*-import unittest
import unittest
from EncryptHelper import EncryptHelper

textToEncrypt = "将要加密的字符"
key ="5dd69f95-a82a-4b01-b6c1-a2d2f8f8061b"
encryptedText = "neD0iV1eFLpIy4/F1XHFEomEfx2kV/EPMg+edO9TU/8="
class TestEncryptHelper(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def testEncrypt(self):
        encrypted = EncryptHelper.encrypt(textToEncrypt,key)
        print(encrypted)
        self.assertEqual(encryptedText,encrypted)


    def testDecrypt(self):
        plain = EncryptHelper.decrypt(encryptedText,key)
        print(plain)
        self.assertEqual(textToEncrypt,plain)
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

// .net frameWork version : 4.0

namespace ConsoleApplication1
{
    class EncryptTest
    {
        public static void TestEncrypt()
        {
            string key = "5dd69f95-a82a-4b01-b6c1-a2d2f8f8061b";
            string textToEncrypt = "将要加密的字符";
            string encryptedText = "neD0iV1eFLpIy4/F1XHFEomEfx2kV/EPMg+edO9TU/8=";

            string strEncrypt = EncryptHelper.AesEncrypt(textToEncrypt, key);
            string strValue = EncryptHelper.AesDecrypt(strEncrypt, key);

            if (strEncrypt == encryptedText)
                Console.WriteLine("AesEncrypt success");

            if (strValue == textToEncrypt)
                Console.WriteLine("AesDecrypt success");

            Console.WriteLine(strValue);
        }
    }
}

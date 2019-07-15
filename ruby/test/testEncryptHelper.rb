# encoding: UTF-8
require 'test/unit'
require_relative "../src/encryptHelper"

$textToEncrypt = "将要加密的字符"
$key ="5dd69f95-a82a-4b01-b6c1-a2d2f8f8061b"
$encryptedText = "neD0iV1eFLpIy4/F1XHFEomEfx2kV/EPMg+edO9TU/8="

class TestEncryptHelper < Test::Unit::TestCase

  def test_encrypt
    assert_equal($encryptedText,EncryptHelper.encrypt($textToEncrypt,$key))
  end

  def test_decrypt
    assert_equal($textToEncrypt,EncryptHelper.decrypt($encryptedText,$key))
  end
end
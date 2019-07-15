# encoding: UTF-8
require 'base64'
require 'openssl'
require 'digest'

$iv="0123456789ABCDEF"
class EncryptHelper
  def initialize()

  end

  def self.encrypt(blank, key)
    cipher = OpenSSL::Cipher.new("AES-128-CBC")
    cipher.encrypt
    cipher.iv = $iv
    cipher.key = Digest::MD5.digest(key)
    encrypted = cipher.update(blank) + cipher.final
    Base64.encode64(encrypted).chomp
  end

  def self.decrypt(encrypted, key)
    encryptedText = Base64.decode64(encrypted)
    cipher = OpenSSL::Cipher.new("AES-128-CBC")
    cipher.decrypt
    cipher.iv = $iv
    cipher.key = Digest::MD5.digest(key)
    blank = cipher.update(encryptedText) + cipher.final
    blank.force_encoding("iso-8859-1").force_encoding('utf-8')
  end
end

package com.pachong.demo.Tools;

import com.pachong.demo.Entity.ConstValue;
import com.pachong.demo.Entity.Params;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class AesCBCUtils {
    public String encrypt(String sSrc, String encodingFormat, String sKey, String ivParameter) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
        //此处使用BASE64做转码
        return new BASE64Encoder().encode(encrypted);
    }

    public Params getParams(String d) throws NoSuchPaddingException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        ConstValue constValue = new ConstValue();
        String encText = encrypt(d, "utf-8", constValue.getG(),
                constValue.getIv()).replace("\r\n", "");
        encText = encrypt(encText, "utf-8", constValue.getI(),
                constValue.getIv()).replace("\r\n", "");
        Params params = new Params();
        params.setParams(encText);
        params.setEncSecKey(constValue.getEncSecKey());
        return params;
    }
}

package com.petshop.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
/**
 * ʹ��AES-128-ECB����ģʽ��key��ҪΪ16λ��
 * @author Administrator
 */

public class AesUtils {

    // ����
    public static String enCode(String sSrc, String sKey) {
        if (sKey == null) {
            System.out.print("KeyΪ��null");
            return null;
        }
        // �ж�Key�Ƿ�Ϊ16λ
        if (sKey.length() != 16) {
            System.out.print("Key���Ȳ���16λ");
            return null;
        }
        byte[] raw={};
		try {
			raw = sKey.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher;
        byte[] encrypted={};
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}//"�㷨/ģʽ/���뷽ʽ"
        

        return new Base64().encodeToString(encrypted);//�˴�ʹ��BASE64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�
    }

    // ����
    public static String deCode(String sSrc, String sKey) {
        try {
            // �ж�Key�Ƿ���ȷ
            if (sKey == null) {
                System.out.print("KeyΪ��null");
                return null;
            }
            // �ж�Key�Ƿ�Ϊ16λ
            if (sKey.length() != 16) {
                System.out.print("Key���Ȳ���16λ");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//����base64����
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}

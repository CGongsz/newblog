package com.nov.newblog.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-11 17:50
 * @Version: 1.0
 */
public class RSAUtil {

    /**
     * RSA公钥加密
     *
     * @param str       加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
    }



    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte;
        try {
            inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }



    public static void main(String[] args) throws Exception {
        //公钥
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDp9FaWlZc/doHX" +
                "il60NcJB56trQOoYSMx5LUgBr+rGANJNBJvUJOI3Jqfs5axf4Lx/JqlhGcmlBuNE" +
                "gt16iEwwBLj6GSpHIm+qZ/bT1xHqJqJzS55PUu6JNhZsV6ERz/tvs3dITuTa0zxT" +
                "CSX4c9DniIF0VuWCLgEvRVuI2pqBYQIDAQAB";

        //私钥
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAOn0VpaVlz92gdeK" +
                "XrQ1wkHnq2tA6hhIzHktSAGv6sYA0k0Em9Qk4jcmp+zlrF/gvH8mqWEZyaUG40SC" +
                "3XqITDAEuPoZKkcib6pn9tPXEeomonNLnk9S7ok2FmxXoRHP+2+zd0hO5NrTPFMJ" +
                "Jfhz0OeIgXRW5YIuAS9FW4jamoFhAgMBAAECgYBS+Rg4gcdJd7MnCWj7CtHhY9OU" +
                "z0n24OK720ZdatGd9a6gKBFIFOix3KDT0MTooCDaC5Eo4ed1AtNZefwot+wksf2R" +
                "dXgoAf/uS1GpxbzXhzFHmfDBOf7yKRXm4K/xzVWu7+1DI2siGwuQ6GWfyhZlGCgj" +
                "DASHmSYOAidG8t6KQQJBAPeotqqoY0Q7yICWyRbJIVgtABRxSR6xE+4fGjHev219" +
                "pYopK7r0KRQsyAgHsFoFwzx9Rzr8YG85LBQ7lAeenzkCQQDx1XchnIRRuAl5f9EL" +
                "QuZjz8orT08nKhrM2aNswfmY5UC7amJPvPSZihYGO+V75NBGii6glG1Fskp+o4Hb" +
                "kctpAkA/DChUkcGToVSNx9Uxg8qFVA9tJNnkglf8itZ0U6yQByiZX5nOYlIC3NOf" +
                "l7WBdW4afBoiHhW+uuNJ01EAD5WhAkANBpx2GQIfjLQl8TaHTsEuGyEemBUBXQk4" +
                "g3iEASa6j46fCanl9Z2PL5rkFxHTTI3TXtoIGDaSjHnqvE/gTcgRAkEA92TtlNrc" +
                "VMm7ZkXz26YXkJ5dLDAFB7X8GxZU7DBJ3nFU8Ezj3V5y+5ka494A7/8WVYZUpnm9" +
                "KspvMmNNK4l/lQ==";

        String pri2 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANsUv7XEytN+quK2\n" +
                "ldp/1rTwnbZXML8MLkuvRGrg/pWVgQUHvP3w/HDi7k/lTKXehM99hKYaq4ReWYgH\n" +
                "AZzUumNPpC70mHukDqaWSIHbGB+rtv4jjiAYhM3JrOtqCIBd0eUG4c7NqTc3Vyxq\n" +
                "NCZ4779oWe9jtFuAZG31hUQWdelXAgMBAAECgYAsLr4PCXYiOxx5hnsoSrnhWdlk\n" +
                "qabiELHRf7Jbn66YOkN/4TSjPhObLJkFMehIV10Pf2/eE+jdVC3YOhmAKILCHtJu\n" +
                "XYyreeUGcrnYytpT9Ud3KBNMrRN1UzKHSsGXWJbmzHRUhNM2lhalGf/Ed2Ilkr5z\n" +
                "4q6VxqJCszD1vtlEOQJBAP16SuU3eJ0CD+MLzXldAAhBzIVLWDbTz3TRYdkAHM6x\n" +
                "qPhfXJU9OX9OwjZRG9zbpK2Jj9DPB9fr6n9+NmWxDoMCQQDdQtWha6UYqk3C5qNc\n" +
                "uz5TnE+NXL10LS4BV1+MtzsYAHZA7K+FL7Txl9G3rRIGa/t9WwsLf1cVCIP5qnOY\n" +
                "MIGdAkB9CP4444wTpxi/HutUmpORfyOr/Oae5QzIyyBBMtUDAJAhNKku1Oyv5+HV\n" +
                "eV0NLSumjtzqW7W6xRz/lvquXxCRAkEAm4/K3TPunnnWNEqt3puzBVvnvo7OkKjX\n" +
                "KJBCmI+W15gzjBAck3poMFe6VfYzrxoqdgcVq9663gnfqCBEJ2MrLQJAPF8y2YbC\n" +
                "Ka+T+sH0NIE2eGr7/emajKxfOl9eT6m4P8kMuA2X9w9fE6gNPNWCc2xA0VSsIIpQ\n" +
                "Q84IuEE4ykKzRg==";
        String pub2 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDGQ/2YbpXdPB+yh9dFgYLaP9Kt\n" +
                "XTlU/mM+7RQo6avEsoC3qxbmFopV3OxW4yz7BllRP0RDTZzpXLlJzOBmmutgbaab\n" +
                "3orzcTC8A0Vu3vkITlVX9lLtbiifCtvd4LZBJQ50bRLhIqWuy5fdNFuDdF/Tn/52\n" +
                "BfrKuc7Xgl0Anib7IQIDAQAB";

        String str = "123456";

        // 用公钥进行加密
        String strEncryptByte = RSAUtil.encrypt(str, publicKey);

        // 用私钥进行解密
        String strDecryptByte = RSAUtil.decrypt(strEncryptByte, privateKey);

        System.out.println("加密前：" + str);
        System.out.println("加密后：" + strEncryptByte);
        System.out.println("解密后：" + strDecryptByte);
        System.out.println("加解密前后是否相同：" + str.equals(strDecryptByte));
    }
}

package org.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EncryptDecryptUtil {
    public static String encrypt(String propertyValue,String passworkKey) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(passworkKey);
        String encryptedPassword = encryptor.encrypt(propertyValue);
        return encryptedPassword;
    }

    public static String decrypt(String encryptedPropertyValue,String passworkKey)  {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(passworkKey);
        String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);

        return decryptedPropertyValue;
    }



    public  static void main(String[] args){
        //System.out.println(" input password："+args[0]);
 //       String password = "youtellme";
//        System.out.println("++++++++aes+++begin++++++++++++++++++++");
//        String passfactorASE =  EncryptDecryptUtil.encryptASE(passfactor);
//        System.out.println("++++++++aes encrypt:"+passfactorASE);
//        String password = EncryptDecryptUtil.decryptASE(passfactorASE);
//        System.out.println("++++++++aes decrypt:"+password);
//        System.out.println("++++++++aes+++end++++++++++++++++++++");


 //       System.out.println("++++++++jasypt+++++++++++++++++++begin++++");
//        String value =  EncryptDecryptUtil.encrypt("msflink",password);
        //String value =  EncryptDecryptUtil.encrypt("aoaAyIMcm5nCJbxxkI+4jk8Zx2e42y5bpZHZEThxW9FHl160o518U4pwbe4YU/s3EJMXUOh4B2+g2EKwdlv/JnY5SbUZJR5GVZuin6Mci9HpRHNN8fnzPTL7JetSxaQwsGxcyjS+kwD/ALltMZbJPLw1foaZ31J38Zi42tfPUF8cv5MR0A4eWABj+HCpu1V9jKDzWsumpZFBWJ9aXSerJH96Z6UdlrpSIkYXOB6nRL+lMHfMzCHKBGxjuLWI=",password);
//        String value =  EncryptDecryptUtil.encrypt("0TTr9sjwp39xaKwfg5psuZvMKvfTiG+aeIiCaY9x44kM=",password);
//
//        System.out.println("encode:"+value);
//        value = EncryptDecryptUtil.decrypt("W7GADZJ79wo0VS9rhWxsjBoqQ5HKklnNl",password);
//        System.out.println("decode:"+value);
//
//        System.out.println("++++++++jasypt+++++++++++++++++++end++++");
 //       refreshToken();
    }
    //{
    //    "id": "4864fbae-5cfe-4c62-b9f7-f60fccb52d70",
    //    "description": "studio 运维文档",
    //    "scheme": "KNOWLEDGE_BASE_2",
    //    "created_at": "2024-04-01T05:41:43.836672124Z",
    //    "updated_at": "2024-04-01T05:41:43.836672915Z",
    //    "status": "UPLOADED",
    //    "file_name": "SenseStudio常见问题 _20230705.docx"
    //}
    public static void refreshToken()  {
        String senseNovaToken;
        try {
            Date expiredAt = new Date(System.currentTimeMillis() + 1800*1000);
            Date notBefore = new Date(System.currentTimeMillis() - 5*1000);
            Algorithm algo = Algorithm.HMAC256("8KLWQYCbIxn9ZrSiAQ8KT9hZ1yf0zwSU7");
            Map<String, Object> header = new HashMap<String,Object>();
            header.put("alg", "HS256");
            senseNovaToken= JWT.create()
                    .withIssuer("2eToeLAxUfId87Q3AYc96AlzDLV9")
                    .withHeader(header)
                    .withExpiresAt(expiredAt)
                    .withNotBefore(notBefore)
                    .sign(algo);
        } catch (Exception e) {
            e.printStackTrace();
            senseNovaToken="";
        }
        System.out.println("token:"+senseNovaToken);
    }
}

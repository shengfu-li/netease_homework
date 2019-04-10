package com.netease.homework;


import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.security.*;

import static org.hamcrest.MatcherAssert.assertThat;

public class SimpleTest {
    @Test
    public static void main(String[] args) {
//        String password = "reyub";
//        try {
//            byte[] passwordByte = password.getBytes("UTF8");
//            MessageDigest md5 = MessageDigest.getInstance("MD5");
//            byte[] digitByte = md5.digest(passwordByte);
////            String h = new String(digitByte, "utf-8");
////            System.out.println(h);
//            System.out.println(Arrays.toString(digitByte));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        String hash = "35454B055CC325EA1AF2126E27707052";
//        String password = "ILoveJava";

//        String hash = "37254660e226ea65ce6f1efd54233424";
//        String password = "reyub";
//
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        md.update(password.getBytes());
//        byte[] digest = md.digest();
//        String myHash = DatatypeConverter
//                .printHexBinary(digest).toUpperCase();
//
//        System.out.println(hash);
//        System.out.println(myHash);

        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        String str=scanner.next();
        for(int i=0;i<str.length();i++)
            System.out.println(str.charAt(i));


    }
}

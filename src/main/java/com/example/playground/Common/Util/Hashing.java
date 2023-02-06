package com.example.playground.Common.Util;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class Hashing {

    private static final int SALT_SIZE = 16;
    private static final String SALT = "HELLO_HOPS_A000000000_0427";

    private String doHashing(String plainText) throws Exception {
        String encString = "";

        encString = Hashing(plainText.getBytes());

        return encString;
    }

    // 비밀번호 해싱
    private String Hashing(byte[] password) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256 해시함수를 사용

//        // key-stretching
//        for(int i = 0; i < 10000; i++) {
//            String temp = Byte_to_String(password) + Salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성
//            md.update(temp.getBytes());						// temp 의 문자열을 해싱하여 md 에 저장해둔다
//            password = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다
//        }

        String temp = Byte_to_String(password) + SALT;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성
        md.update(temp.getBytes());						// temp 의 문자열을 해싱하여 md 에 저장해둔다
        password = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다
        System.out.println(password);

        return Byte_to_String(password);
    }

    // SALT 값 생성
    private String getSALT() throws Exception {
        SecureRandom rnd = new SecureRandom();
        byte[] temp = new byte[SALT_SIZE];
        rnd.nextBytes(temp);

        return Byte_to_String(temp);

    }

    // 바이트 값을 16진수로 변경해준다
    private String Byte_to_String(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }



}

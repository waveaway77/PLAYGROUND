package com.example.playground;

import com.example.model.StrClass;
import org.junit.jupiter.api.Test;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestClassTest {

    @Test
    void nullTest() {
        List<Integer> nullList = null;
        for (Integer i : nullList) {
            System.out.println("is this work?");
        }
    }

    @Test
    void funcSubstring() {
        String capaDayTime = "20230101AM";
        String capaDay = capaDayTime.substring(0,8); // 20230101
        String capaTime = capaDayTime.substring(8); // AM
        System.out.println(capaDay+" / "+capaTime);
    }

    @Test
    void resplaceFunc() {
        String a = "안녕하세요";
        a = a.replace("안녕","식사");
        System.out.println(a);
    }

    @Test
    void timeTemplate() throws Exception {

        String msg = "#{이름} 님 안녕하세요. With KMI입니다.\\n[#{검진기관} 검진예약 접수안내]\\n■고객명 : #{이름}\\n■검진센터 : #{검진기관}\\n■희망일자 : #{희망일시}\\n위와 같이 건강검진 [예약신청] 이 접수 되었습니다.\\n검진센터 예약 담당자 확인 후 예약이 확정 됩니다.";

        String originStr1st = "20230104AM";
        String originStr2nd = "20230201PM";

        LocalDate firstHopeDay;
        LocalDate secondHopeDay;

        String resultStr = "";
        try {
            // 제1희망일자 변환
            firstHopeDay = LocalDate.parse(originStr1st.substring(0,8), DateTimeFormatter.ofPattern("yyyyMMdd"));
            resultStr = firstHopeDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            resultStr += " ";
            resultStr += originStr1st.substring(8,10);
            System.out.println("---------resultStr is "+resultStr);

            // 제2희망일자 변환
            secondHopeDay = LocalDate.parse(originStr2nd.substring(0,8), DateTimeFormatter.ofPattern("yyyyMMdd"));

            // 제1희망일자와 결합
            resultStr += " / ";
            resultStr += secondHopeDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            resultStr += " ";
            resultStr += originStr2nd.substring(8,10);
            System.out.println("---------resultStr is "+resultStr);
        } catch (Exception e) {
            System.out.println("error "+e);
        }
        System.out.println("---------resultStr is "+resultStr);

        // 변수 변환
        msg = msg.replace("#{희망일시}", resultStr);
        System.out.println("---------msg is "+msg);
    }

    private static final String SALT = "HELLO_HOPS_A000000000_0427";

    @Test
    void masking() throws Exception {

        String plainText = "passw0rd!";
        String maskingUserId = "";

        // 마스킹 영역 지정
        String middleMask = plainText.substring(plainText.length()-3, plainText.length()-1); // 뒤에서부터 두번째, 뒤에서부터 세번째 글자
        System.out.println("middleMask is "+middleMask); // bc
        // 마스킹 생성
        String masking = "";
        for (int i=0; i<middleMask.length(); i++) {
            masking += "*";
        }

        maskingUserId = plainText.replace(middleMask, masking); // a**d

        System.out.println("maskingUserId = "+maskingUserId);
    }

    @Test
    void lengthTest() throws Exception {
        String str = "abcd";
        System.out.println("str length is : "+str.length()); // 4
    }

    @Test
    void objectList() throws Exception {

        List<StrClass> strClassList = new ArrayList<>();

        StrClass strClass1 = new StrClass();
        strClass1.setName("민수");
        strClass1.setAge("20");
        strClassList.add(strClass1);
        StrClass strClass2 = new StrClass();
        strClass2.setName("영희");
        strClass2.setAge("20");
        strClassList.add(strClass2);
        StrClass strClass3 = new StrClass();
        strClass3.setName("철수");
        strClass3.setAge("20");
        strClassList.add(strClass3);

        System.out.println("list size is .. "+strClassList.size()); // 3

        System.out.println("if문 시작");
        if (strClassList.contains("민수")) {
            System.out.println("it has 민수"); // 출력되지 않음
        }
        System.out.println("if문 종료");

        System.out.println("for문 시작");
        for (StrClass a : strClassList) {
            if (a.getName().equals("민수")) {
                System.out.println("it has 민수"); // 출력됨
            }
        }
        System.out.println("for문 종료");

        System.out.println("for문 시작2");
        for (StrClass a : strClassList) {
            System.out.println("a is "+a.getName());
//            a is 민수
//            a is 영희
//            a is 철수
            if (a.getName().equals("철수")) {
                System.out.println("it has 철수"); // 출력됨
            } else if (a.getName().equals("민수")) {
                System.out.println("it has 민수"); // 출력됨
            }

            if (a.getName().equals("민수") && a.getName().equals("철수")) {
                System.out.println("it has 민수와 철수"); // 출력안됨
            }
        }
        System.out.println("for문 종료2");

    }

    @Test
    public void equalTest() {
        /*
        동등비교연산자(==) : 두 비교 대상의 저장 위치가 같은가, 원시형 데이터(자료형, Boolean)
        .equals() : 두 비교 대상의 데이터의 내용이 같은가, 비원시형 데이터(String, Array, File ...)
        */
    }

    @Test
    void testDoHashing() throws Exception {

//        String plainTextAddSalt = "Passw0rd!"+SALT; // 송월
        String plainTextAddSalt = "kmi@1324!!"+SALT; // 우아한
//        String plainTextAddSalt = "19960427"+SALT;
        String encString = encrypt(plainTextAddSalt.getBytes());
        System.out.println("encString = "+encString);

    }

    @Test
    String encrypt(byte[] password) throws Exception {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256 해시함수를 사용
            md.update(password);						// temp 의 문자열을 해싱하여 md 에 저장해둔다
            password = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다
        } catch (Exception e) {

        }

//        // key-stretching
//        for(int i = 0; i < 10000; i++) {
//            String temp = Byte_to_String(password) + Salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성
//            md.update(temp.getBytes());						// temp 의 문자열을 해싱하여 md 에 저장해둔다
//            password = md.digest();							// md 객체의 다이제스트를 얻어 password 를 갱신한다
//        }

        return byteToString(password);
    }

    @Test
    // 바이트 값을 16진수로 변경해준다
    String byteToString(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for(byte a : temp) {
            sb.append(String.format("%02x", a));
//            sb.append(String.format("%02X", a));
        }
        return sb.toString();
    }

    @Test
    void testListContains() {
        List<String> strList = new ArrayList<>();
        strList.add("a");
        strList.add("b");
        strList.add("c");
        strList.add("d");
        strList.add("e");

        if (strList.contains("b")) {
            System.out.println("List has b");
        }
    }

    @Test
    void testDatePattern() {
        //LocalDateTime > String (now.format)
        LocalDateTime localDateTime = LocalDateTime.now(); //now
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");; //format
        String formedDt = localDateTime.format(formatter);
        System.out.println("formedDt is ... "+formedDt);

        //String > LocalDateTime (LocalDateTime.parse)
        formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateTime = "20220101010000";
        LocalDateTime localDateTime1 = LocalDateTime.parse(dateTime, formatter);
        System.out.println("localDateTime1 is ... "+localDateTime1);
    }

    @Test
    void testDateAMPM() {
        //dateFormat간의 포맷 전환은 불가능한듯하다.
        //1. String(YYYYMMDDHHMI) -> DateFormat
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String dateTime = "202201011730";
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);

        //2. DateFormat to AMPm version
        DateTimeFormatter ampmFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmma");
//        LocalDateTime.parse(LocalDateTime,ampmFormatter) ;

        //3. DateFormat -> String
    }

    @Test
    void testToAMPM() {
        String dateTime = "202201011730";
        dateTime = "202201012330";
        String strTime = dateTime.substring(8,10); //17 //8부터 10전까지(9까지)
        int intTime = Integer.parseInt(strTime); //String.valueOf();

        if(0<=intTime && intTime<=12) {
            dateTime += "AM";
        } else {
            dateTime += "PM";
        }

        System.out.println("ampm is ... "+dateTime);
    }

    @Test
    void testContains() {
        String a = "가나다라";
        if(a.contains("가")) {
            System.out.println("contains는 String에서도 적용이 됩니다.");
        } else {
            System.out.println("contains는 String에서 적용이 안 됩니다.");
        }
    }

    @Test
    void cal() {

        List<Integer> array1 = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        List<String> array2 = new ArrayList<>(Arrays.asList("a","b","c"));

        if(array1.contains(1) || array2.contains("a")) {
            System.out.println("1. it contains 1 and a");
        }

        if(array1.contains(6) && array2.contains("a")) {
            System.out.println("2. it contains 1 and a");
        }

    }

    @Test
    public void length() {
        /*
        1. length
         - arrays(int[], double[], String[])
         - length는 배열의 길이를 알고자 할때 사용된다.

        2. length()
         - String related Object(String, StringBuilder etc)
         - length()는 문자열의 길이를 알고자 할때 사용된다.

        3. size()
         - Collection Object(ArrayList, Set etc)
         - size()는 컬렉션프레임워크 타입의 길이를 알고자 할때 사용된다.

         */
        int[] lengthTest1 = new int[7];
        System.out.println(lengthTest1.length); //7

        String lengthTest2 = "lengthSizeTest";
        System.out.println(lengthTest2.length()); //14

        ArrayList<Object> sizeTest = new ArrayList<>();
        System.out.println(sizeTest.size()); //0
    }

    @Test
    public void booleanTest() {
        /*
        Boolean의 기본값은 null
        boolean의 기본값은 flase
        bool > String : String.valudeOf(bool)
        */
    }

    @Test
    public void arrayTest() {
        /*

        // 크기 할당 & 초기화 없이 배열 참조변수만 선언
        int[] arr;
        int arr[];

        // 선언과 동시에 배열 크기 할당
        int[] arr = new int[5];

        // 선언과 동시에 배열의 크기 지정 및 값 초기화
        int[] arr = {1,2,3,4,5}
        int arr = new int[] {1,2,3,4,5};

        ArrayList<Integer> integers1 = new ArrayList<Integer>(); // 타입 지정
        ArrayList<Integer> integers2 = new ArrayList<>(); // 타입 생략 가능
        ArrayList<Integer> integers3 = new ArrayList<>(10); // 초기 용량(Capacity) 설정
        ArrayList<Integer> integers4 = new ArrayList<>(integers1); // 다른 Collection값으로 초기화
        ArrayList<Integer> integers5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5)); // Arrays.asList()
        */
    }

    @Test
    public void split1() {

        String[] array = "127.0.0.0.1".split("\\."); //ip.split("[.]");
        String[] moNum = "010-1111-2222".split("-");
        for(String a : moNum) {
            System.out.println(a);
            /*
            010
            1111
            2222
             */
        }

        String str1 = "hi my name is";
        String[] array1 = str1.split(" ");
        /*
        hi
        my
        name
        is
         */
        String[] array2 = str1.split("");
        /*
        h
        i

        m
        y

        n
        ...
         */
    }

    @Test
    public void replaceFirst1() {
        String str = "test%test";
        String str1 = str.replace("test", "@"); //@%@
        String str2 = str.replaceFirst("test","!"); //!%test
    }
    //오른쪽으로 0으로 채우는 String.format

    @Test
    public void continueBreak() {
        /*
        break : 해당 조건문 블록 & 그 밖의 반복문 자체를 탈출
        continue : 해당 조건문 블록 탈출하여 아래 실행문 스킵(반복문의 끝으로 이동하여), 다음 반복문 실행절차를 수행
            for : 증감식으로 이동
            while : 조건식으로 이동
        */
    }
}
package com.example.userservice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Utils {
//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("password"));
//    }
private static final String RRN_REGEX =
        "^\\s*(\\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01])\\s*[-]?\\s*([1-4])\\d{6}\\s*$";

    public static void main(String[] args) {

        Pattern pattern = Pattern.compile(RRN_REGEX);

        // 테스트 케이스
        String[] testInputs = {
                "801212-1234567",   // 정상 (하이픈)
                "801212 1234567",   // 정상 (공백)
                " 801212 1234567 ",   // 정상 (공백)
                "8012121234567",    // 정상 (붙여쓰기)
                " 801212 - 1234567 ", // 정상 (앞뒤 공백)
                "991331-1234567",   // ❌ 날짜 오류 (13월)
                "990231-1234567",   // ⚠ 형식상 통과 (2월 31일)
                "801212-5234567",   // ❌ 뒷자리 첫 숫자 5
                "안녕하세요 801212-1234567 입니다", // ❌ 문장 포함
                "123456-1234567",   // ❌ 월/일 오류
                "801212-123456"     // ❌ 자리수 부족
        };

        System.out.println("주민번호 정규식 테스트 결과");
        System.out.println("------------------------------------------------");

        for (String input : testInputs) {
            Matcher matcher = pattern.matcher(input);
            boolean isMatch = matcher.matches();

            System.out.printf("입력값: %-35s → %s%n",
                    "\"" + input + "\"",
                    isMatch ? "MATCH ✅" : "NO MATCH ❌");
        }
    }
}

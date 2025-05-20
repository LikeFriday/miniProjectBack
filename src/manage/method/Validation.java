package manage.method;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean checkEmail(String email) {
        if(email.indexOf("@") == -1){
            System.out.println("이메일 형식이 아닙니다.");
            return false;
        }else{
            return true;
        }
    }

    public static boolean checkPwd(String pw) {
        String regex = "[!@#$%^&*]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pw);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println("비밀번호에 특수문자가 없습니다. ");
            return false;
        }
    }

    public static boolean checkRole(String role) {
        if(role.equals("A") || role.equals("B") || role.equals("C")){
            return true;
        }else{
            System.out.println("등급 입력 오류 A/B/C 입력가능");
            return false;
        }
    }
}

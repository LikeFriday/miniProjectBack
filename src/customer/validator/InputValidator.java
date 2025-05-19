package customer.validator;

public class InputValidator {

    //이름 유효성 검사 - 한글 및 영어만 입력 가능
    public static void isValidName(String name) {
        if (!name.matches("^[a-zA-Z가-힣]+$")) {
            throw new IllegalArgumentException("이름은 한글 또는 영어만 입력해주세요.\n");
        }
    }

    //아이디 유효성 검사 - 특수문자 _,$와 영어 숫자만 가능/한글 불가
    public static void isValidUserId(String userId) {
        if (!userId.matches("^[a-zA-Z0-9_$]+$")) {
            throw new IllegalArgumentException("❌ 유효한 아이디 형식이 정확하지 않습니다. 영어, 숫자, _, $만 입력 가능합니다.\n");
        }
    }

    //전화번호 유효성 검사 - (- 없이는 입력 불가)(010-1234-5678) 형식에 맞춰서
    public static void isValidPhone(String phone) {
        if (!phone.matches("^01[016789]-\\d{4}-\\d{4}$")) {
            throw new IllegalArgumentException("❌ 유효한 전화번호 형식이 아닙니다. 예: 010-1234-5678\n");
        }
    }

    // 이메일 유효성 검사 - 한글 입력 불가 및 특수문자(_,$,@)만 가능
    // 이메일 형식이 아니어도 입력되니까 이거 수정
    public static void isValidEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("❌ 유효한 이메일 형식이 아닙니다. 예: example@domain.com\n");
        }
    }



}

package manage.dto;

public class Admin {
    //1 관리자 고유 번호
    private int uid;
    //2 관리자 id
    private String adminId;
    //3 관리자 pw
    private String adminPw;
    //4 관리자 이름
    private String adminName;
    //5 관리자 메일
    private String adminEmail;
    //6 관리자 등급
    private String role;
    //7 관리자 생성날짜
    private String createDate;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminPw() {
        return adminPw;
    }

    public void setAdminPw(String adminPw) {
        this.adminPw = adminPw;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "[" +
                "uid=" + uid +
                ", adminId= " + adminId+
                ", adminPw= " + adminPw+
                ", adminName= " + adminName +
                ", adminEmail= " + adminEmail +
                ", role= " + role  +
                ", createDate= " + createDate +
                "]";

    }
}

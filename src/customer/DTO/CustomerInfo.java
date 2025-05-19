package customer.DTO;//고객 정보 클래스(DTO)

public class CustomerInfo {

    private String customerName;
    private String userId;
    private String email;
    private String phone;
    private String membershipLevel;

    public CustomerInfo(String customerName, String userId, String email, String phone, String membershipLevel) {
        this.customerName = customerName;
        this.userId = userId;
        this.email = email;
        this.phone = phone;
        this.membershipLevel = membershipLevel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @Override
    public String toString() {
        return "customerName='" + customerName + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", membershipLevel='" + membershipLevel + '\'' +
                '}';
    }
}

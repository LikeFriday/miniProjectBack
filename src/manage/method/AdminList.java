package manage.method;

import manage.dao.AdminDAO;
import manage.dto.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminList {
    public void adminList(){
        AdminDAO dao = new AdminDAO();
        List<Admin> list = new ArrayList<Admin>();
        list = dao.adminFindAll();
        System.out.println("관리자 리스트");
        for (Admin admin : list) {
            System.out.println(admin);
        }
    }
}

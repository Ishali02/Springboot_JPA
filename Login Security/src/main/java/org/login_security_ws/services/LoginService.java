package org.login_security_ws.services;


import org.login_security_ws.model.*;
import org.login_security_ws.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<String> loginAuth(Login login){

        User user = loginRepository.findByUserNameAndUserPwd(login.getUserName(),login.getPassword());
        List<String> menuList = new ArrayList<>();

        if(user  == null) {
             menuList.add("UserNotFound/IncorrectPassword");
             return  menuList;
        }
        String user_id = user.getUserId();
        menuList = menuRepository.findByUser(user_id);
        return menuList;
    }

//   // public List<String> getRoleList(String userId)
//    {
//        return  roleRepository.findByUser(userId);
//    }
//    public List<String> getMenuList(String roleId)
//    {
//        return menuRepository.findByUser(roleId);
//    }


}

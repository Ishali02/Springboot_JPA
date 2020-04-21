package org.login_security_ws.controller;

import org.login_security_ws.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.login_security_ws.model.Login;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/loginAuth", method = RequestMethod.POST )
    public List<String> loginAuth (@RequestBody Login login)
    {
        return loginService.loginAuth(login);
    }

//    @RequestMapping(value = "/getMenuList1/{roleId}",method =RequestMethod.GET)
//    public @ResponseBody List<String> getMenuList(@PathVariable String roleId){
//        return loginService.getMenuList(roleId);
//    }
//
//    @RequestMapping(value = "/getRoleList/{userId}",method =RequestMethod.GET)
//    public @ResponseBody List<String> getRoleList(@PathVariable String userId){
//        return  loginService.getRoleList(userId);
//    }

}


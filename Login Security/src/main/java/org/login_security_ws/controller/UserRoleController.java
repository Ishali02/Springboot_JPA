package org.login_security_ws.controller;

import org.login_security_ws.model.User_Role;
import org.login_security_ws.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userrole")
public class UserRoleController {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @RequestMapping(value = "/addUserRole", method = RequestMethod.POST )
    public @ResponseBody
    String addUserRole(@RequestBody User_Role user_role)
    {
        try
        {
            if(user_role.getUserRoleId() == null)
            {
                userRoleRepository.save(user_role);
                return "Success";
            }
            else
            {
                return "'userId' key is not expected to add user_role.";
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to create user_role.");
            return "Failed";
        }
    }

    @RequestMapping(value = "/getUserRole", method = RequestMethod.GET)
    public @ResponseBody List<User_Role> getUserRole()
    {
        try
        {
            List<User_Role> user_role_list = userRoleRepository.findAll();
            return user_role_list;
        }
        catch (Exception e)
        {
            System.out.println("Unable to get users.");
            return null;
        }
    }

    @RequestMapping(value = "/getRoles/{user_id}", method = RequestMethod.GET )
    public @ResponseBody List<String> getRolesOfUser(@PathVariable("user_id") String userId)
    {
        List<String> roleList =  new ArrayList<>();
        List<User_Role> userRole = new ArrayList<>();
        try
        {
            userRole = userRoleRepository.findByUserId(userId);
           // System.out.println(userRoleRepository.findByUserId(userId));
            for(User_Role ur : userRole)
            {
                roleList.add(ur.getRoleId());
            }
            return roleList;
        }
        catch (Exception e)
        {
            System.out.println("Unable to get user.");
            return null;
        }
    }

    @RequestMapping(value = "/getUsers/{role_id}", method = RequestMethod.GET )
    public @ResponseBody List<User_Role> getUsersOfRole(@PathVariable("role_id") String role_id)
    {
        try
        {
            System.out.println(userRoleRepository.findByRoleId(role_id));
            return userRoleRepository.findByRoleId(role_id);
        }
        catch (Exception e)
        {
            System.out.println("Unable to get user.");
            return null;
        }
    }

    @RequestMapping(value = "/deleteUserRole", method = RequestMethod.DELETE )
    public @ResponseBody String deleteUserRole(@RequestBody User_Role user_role)
    {
        try
        {
            userRoleRepository.deleteById(user_role.getUserRoleId());
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete user_role.");
            return "Failed to Delete!";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteByUserId", method = RequestMethod.DELETE )
    public @ResponseBody String deleteByUserId(@RequestBody User_Role user_role)
    {
        try
        {
            long deleteCount = userRoleRepository.deleteByUserId(user_role.getUserId());
            System.out.println("Deleted count : " + deleteCount);
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete user_role : " + e.toString());
            return "Failed to Delete!";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteByRoleId", method = RequestMethod.DELETE )
    public @ResponseBody String deleteByRoleId(@RequestBody User_Role user_role)
    {
        try
        {
            long deleteCount = userRoleRepository.deleteByRoleId(user_role.getRoleId());
            System.out.println("Deleted count : " + deleteCount);
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete user_role.");
            return "Failed to Delete!";
        }
    }


    @Transactional
    @RequestMapping(value = "/deleteByUserIdRoleId", method = RequestMethod.DELETE )
    public @ResponseBody String deleteByUserIdRoleId(@RequestBody User_Role user_role)
    {
        try
        {
            long deleteCount = userRoleRepository.deleteByUserIdAndRoleId(user_role.getUserId(),user_role.getRoleId());
            System.out.println("Deleted count : " + deleteCount);
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete user_role.");
            return "Failed to Delete!";
        }
    }
}

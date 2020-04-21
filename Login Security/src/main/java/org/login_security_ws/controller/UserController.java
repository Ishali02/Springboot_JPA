package org.login_security_ws.controller;

import org.login_security_ws.model.User;
import org.login_security_ws.repositories.UserRepository;
import org.login_security_ws.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @RequestMapping(value = "/addUser", method = RequestMethod.POST )
    public @ResponseBody String addUser(@RequestBody User user)
    {
        try
        {
            if(user.getUserId() == null)
            {
                userRepository.save(user);
                return "Success";
            }
            else
            {
                return "'userId' key is not expected to add User.";
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to create user.");
            return "Failed";
        }
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public @ResponseBody List<User> getUser()
    {
        try
        {
            List<User> user_list = userRepository.findAll();
            return user_list;
        }
        catch (Exception e)
        {
            System.out.println("Unable to get users.");
            return null;
        }
    }

    @RequestMapping(value = "/getUser/{user_id}", method = RequestMethod.GET )
    public @ResponseBody Optional<User> getOneUser(@PathVariable("user_id") String user_id)
    {
        try
        {
            return userRepository.findById(user_id);
        }
        catch (Exception e)
        {
            System.out.println("Unable to get user.");
            return Optional.empty();
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.PUT)
    public @ResponseBody String updateUser(@RequestBody User user)
    {
        try
        {
            userRepository.save(user);
            return "Updated Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to update user.");
            return "Failed to Update!";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE )
    public @ResponseBody String deleteUser(@RequestBody User user)
    {
        try
        {
            long deleteCount = userRoleRepository.deleteByUserId(user.getUserId());
            System.out.println("Deleted count : " + deleteCount);
            userRepository.deleteById(user.getUserId());
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete user.");
            return "Failed to Delete!";
        }
    }
}

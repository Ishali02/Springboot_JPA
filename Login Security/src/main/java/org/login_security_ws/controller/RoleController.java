package org.login_security_ws.controller;

import org.login_security_ws.model.Role;
import org.login_security_ws.repositories.RoleMenuRepository;
import org.login_security_ws.repositories.RoleRepository;
import org.login_security_ws.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @RequestMapping(value = "/addRole", method = RequestMethod.POST )
    public @ResponseBody String addRole(@RequestBody Role role)
    {
        try
        {
            if(role.getRoleId() == null)
            {
                roleRepository.save(role);
                return "Role created successfully.";
            }
            else
            {
                return "'roleId' key is not expected to add Role.";
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to create role.");
            return "Failed to create role!";
        }
    }

    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    public @ResponseBody List<Role> getRoles()
    {
        try
        {
            List<Role> role_list = roleRepository.findAll();
            return role_list;
        }
        catch (Exception e)
        {
            System.out.println("Unable to get roles.");
            return null;
        }
    }

    @RequestMapping(value = "/getRole/{role_id}", method = RequestMethod.GET )
    public @ResponseBody
    Optional<Role> getOneRole(@PathVariable("role_id") String role_id)
    {
        try
        {
            return roleRepository.findById(role_id);
        }
        catch (Exception e)
        {
            System.out.println("Unable to get role.");
            return Optional.empty();
        }
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.PUT )
    public @ResponseBody String updateRole(@RequestBody Role role)
    {
        try
        {
            roleRepository.save(role);
            return "Updated Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to update role.");
            return "Failed to update role!";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteRole", method = RequestMethod.DELETE )
    public @ResponseBody String deleteRole(@RequestBody Role role)
    {
        try
        {
            long deleteCount = userRoleRepository.deleteByRoleId(role.getRoleId());
            System.out.println("Deleted count : " + deleteCount);
            deleteCount = roleMenuRepository.deleteByRoleId(role.getRoleId());
            System.out.println("Deleted count : " + deleteCount);
            roleRepository.deleteById(role.getRoleId());
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete role.");
            return "Failed to delete role!";
        }
    }
}

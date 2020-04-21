package org.login_security_ws.controller;

import org.login_security_ws.model.Role_Menu;
import org.login_security_ws.model.User_Role;
import org.login_security_ws.repositories.RoleMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rolemenu")
public class RoleMenuController {

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @RequestMapping(value = "/addRoleMenu", method = RequestMethod.POST )
    public @ResponseBody String addRoleMenu(@RequestBody Role_Menu role_menu)
    {
        try
        {
            if(role_menu.getRoleMenuId() == null)
            {
                roleMenuRepository.save(role_menu);
                return "Success";
            }
            else
            {
                return "'roleMenuId' key is not expected to add role_menu.";
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to create role_menu.");
            return "Failed";
        }
    }

    @RequestMapping(value = "/getRoleMenu", method = RequestMethod.GET)
    public @ResponseBody List<Role_Menu> getRoleMenu()
    {
        try
        {
            List<Role_Menu> role_menu_list = roleMenuRepository.findAll();
            return role_menu_list;
        }
        catch (Exception e)
        {
            System.out.println("Unable to get role_menu.");
            return null;
        }
    }

    @RequestMapping(value = "/getRoles/{menu_id}", method = RequestMethod.GET )
    public @ResponseBody List<Role_Menu> getRolesOfMenu(@PathVariable("menu_id") String menuId)
    {
        try
        {
            System.out.println(roleMenuRepository.findByMenuId(menuId));
            return roleMenuRepository.findByMenuId(menuId);
        }
        catch (Exception e)
        {
            System.out.println("Unable to get roles.");
            return null;
        }
    }

    @RequestMapping(value = "/getMenus/{role_id}", method = RequestMethod.GET )
    public @ResponseBody List<Role_Menu> getMenusOfRole(@PathVariable("role_id") String role_id)
    {
        try
        {
            System.out.println(roleMenuRepository.findByRoleId(role_id));
            return roleMenuRepository.findByRoleId(role_id);
        }
        catch (Exception e)
        {
            System.out.println("Unable to get menus.");
            return null;
        }
    }

    @RequestMapping(value = "/deleteRoleMenu", method = RequestMethod.DELETE )
    public @ResponseBody String deleteRoleMenu(@RequestBody Role_Menu role_menu)
    {
        try
        {
            roleMenuRepository.deleteById(role_menu.getRoleMenuId());
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete role_menu.");
            return "Failed to Delete!";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteByRoleId", method = RequestMethod.DELETE )
    public @ResponseBody String deleteByRoleId(@RequestBody Role_Menu role_menu)
    {
        try
        {
            long deleteCount = roleMenuRepository.deleteByRoleId(role_menu.getRoleId());
            System.out.println("Deleted count : " + deleteCount);
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete role_menu.");
            return "Failed to Delete!";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteByMenuId", method = RequestMethod.DELETE )
    public @ResponseBody String deleteByMenuId(@RequestBody Role_Menu role_menu)
    {
        try
        {
            long deleteCount = roleMenuRepository.deleteByMenuId(role_menu.getMenuId());
            System.out.println("Deleted count : " + deleteCount);
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete role_menu.");
            return "Failed to Delete!";
        }
    }
}

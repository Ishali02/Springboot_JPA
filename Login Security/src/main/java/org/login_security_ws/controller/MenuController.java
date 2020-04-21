package org.login_security_ws.controller;

import org.login_security_ws.model.Menu;
import org.login_security_ws.repositories.MenuRepository;
import org.login_security_ws.repositories.RoleMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RoleMenuRepository roleMenuRepository;

    @RequestMapping(value = "/addMenu", method = RequestMethod.POST )
    public @ResponseBody
    String addMenu(@RequestBody Menu menu)
    {
        try
        {
            if(menu.getMenuId() == null)
            {
                menuRepository.save(menu);
                return "Menu created successfully.";
            }
            else
            {
                return "'menuId' key is not expected to add Menu.";
            }
        }
        catch (Exception e)
        {
            System.out.println("Unable to create menu.");
            return "Failed to create menu!";
        }
    }

    @RequestMapping(value = "/getMenu", method = RequestMethod.GET)
    public @ResponseBody List<Menu> getMenu()
    {
        try
        {
            return menuRepository.findAll();
        }
        catch (Exception e)
        {
            System.out.println("Unable to get menus.");
            return null;
        }
    }

    @RequestMapping(value = "/getMenu/{menu_id}", method = RequestMethod.GET )
    public @ResponseBody
    Optional<Menu> getOneMenu(@PathVariable("menu_id") String menu_id)
    {
        try
        {
            return menuRepository.findById(menu_id);
        }
        catch (Exception e)
        {
            System.out.println("Unable to get menu.");
            return Optional.empty();
        }
    }

    @RequestMapping(value = "/updateMenu", method = RequestMethod.PUT )
    public @ResponseBody String updateMenu(@RequestBody Menu menu)
    {
        try
        {
            menuRepository.save(menu);
            return "Updated Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to update menu.");
            return "Failed to update menu!";
        }
    }

    @Transactional
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.DELETE )
    public @ResponseBody String deleteMenu(@RequestBody Menu menu)
    {
        try
        {
            long deleteCount = roleMenuRepository.deleteByMenuId(menu.getMenuId());
            System.out.println("Deleted count : " + deleteCount);
            menuRepository.deleteById(menu.getMenuId());
            return "Deleted Successfully.";
        }
        catch (Exception e)
        {
            System.out.println("Unable to delete menu.");
            return "Failed to delete menu!";
        }
    }
}

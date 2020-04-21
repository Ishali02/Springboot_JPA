package org.login_security_ws.repositories;

import org.login_security_ws.model.Role_Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleMenuRepository extends JpaRepository<Role_Menu, String> {
    List<Role_Menu> findByRoleId(String roleId);
    List<Role_Menu> findByMenuId(String menuId);
    long deleteByMenuId(String menuId);
    long deleteByRoleId(String roleId);

//    @Query(value = "SELECT * FROM mydb.role_menu as rm WHERE rm.Role_Id IN (SELECT ur.Role_Id FROM mydb.user_role as ur WHERE ur.User_Id = ?)", nativeQuery = true)
    @Query(value = "SELECT rm.Menu_Id FROM mydb.role_menu as rm INNER JOIN mydb.user_role as ur WHERE ur.User_Id = :userId", nativeQuery = true)
    List<String> getMenuList(@Param("userId") String userId);
}

package org.login_security_ws.repositories;

import org.login_security_ws.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,String> {

    @Query(value = " SELECT DISTINCT(rm.menuId) FROM Role_Menu AS rm INNER JOIN User_Role AS ur " +
           "ON rm.roleId = ur.roleId  WHERE ur.userId = ?1")
    List<String> findByUser(String userId);

}

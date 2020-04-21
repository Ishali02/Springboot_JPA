package org.login_security_ws.repositories;

import org.login_security_ws.model.Menu;
import org.login_security_ws.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.login_security_ws.model.User;

import java.util.List;
import java.util.Set;


@Repository
public interface LoginRepository extends JpaRepository<User, String> {
    User findByUserNameAndUserPwd(String userName, String userPwd);

//   User findByUserId(String userId);

//    @Query("SELECT u FROM User as u WHERE  u.id IN"
//            + " (SELECT r.userId FROM User_Role as r WHERE r.roleId = ?1)")
//    public List<User> findByRole(String roleID);

//    @Query(value = "SELECT * FROM user as u WHERE  u.User_id IN"
//            + " (SELECT r.User_Id FROM user_role as r WHERE r.Role_Id = ?)", nativeQuery = true)
//    public List<User> findByRole(String roleID);
//
//   @Query(value = "select u.roles from User u where u.userId =:userId")
//    Set<Role> getRolesList(@Param( "userId") String userId);
}

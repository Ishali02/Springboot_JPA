package org.login_security_ws.repositories;

import org.login_security_ws.model.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<User_Role, String> {
    List<User_Role> findByUserId(String userId);
    List<User_Role> findByRoleId(String roleId);
    long deleteByUserId(String userId);
    long deleteByRoleId(String roleId);

    long deleteByUserIdAndRoleId(String userId,String roleId);
}

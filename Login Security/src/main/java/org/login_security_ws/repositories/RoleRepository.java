package org.login_security_ws.repositories;

import org.login_security_ws.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}

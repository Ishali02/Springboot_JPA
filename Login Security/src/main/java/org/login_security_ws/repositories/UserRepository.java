package org.login_security_ws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.login_security_ws.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}

package org.login_security_ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="user_role")
public class User_Role {
    @Id
    @GenericGenerator(name="CustomIdGenerator", strategy="org.login_security_ws.IdGenerator",
            parameters = { @Parameter(name = "tableName", value = "user_role") })
    @GeneratedValue(generator="CustomIdGenerator")
    @Column(name = "user_role_id")
    private String userRoleId;

    @JoinColumn (name = "user_id", referencedColumnName = "user_id")
    @Column(name = "User_Id")
    private String userId;

    @JoinColumn (name = "role_id", referencedColumnName = "role_id")
    @Column(name = "role_id")
    private String roleId;


}

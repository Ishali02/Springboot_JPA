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
@Table(name="role_menu")
public class Role_Menu {
    @Id
    @GenericGenerator(name="CustomIdGenerator", strategy="org.login_security_ws.IdGenerator",
            parameters = { @Parameter(name = "tableName", value = "role_menu") })
    @GeneratedValue(generator="CustomIdGenerator")
    @Column(name = "Role_Menu_Id")
    private String roleMenuId;
    @Column(name = "Role_Id")
    private String roleId;
    @Column(name = "Menu_Id")
    private String menuId;


}

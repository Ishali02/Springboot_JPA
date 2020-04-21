package org.login_security_ws.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="role")
public class Role {

    @Id
    @GenericGenerator(name="CustomIdGenerator", strategy="org.login_security_ws.IdGenerator",
            parameters = { @Parameter(name = "tableName", value = "role") })
    @GeneratedValue(generator="CustomIdGenerator")
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "role_name")
    private String roleName;
//
    @ManyToMany(mappedBy="role")
    @JsonManagedReference
    Collection<User> user = new ArrayList<>();
//
//    @ManyToMany
//    @JoinTable(name = "role_menu")
//    Collection<Menu> menu = new ArrayList<>();
}

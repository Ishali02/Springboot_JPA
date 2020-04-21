package org.login_security_ws.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="user")
public class User {


    @Id
    @GenericGenerator(name="CustomIdGenerator", strategy="org.login_security_ws.IdGenerator",
            parameters = { @Parameter(name = "tableName", value = "user") })
    @GeneratedValue(generator="CustomIdGenerator")
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_pwd")
    private String userPwd;

    @ManyToMany(cascade=CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
    joinColumns = { @JoinColumn(name ="user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @JsonBackReference
    Collection<Role> role = new ArrayList<>();

}

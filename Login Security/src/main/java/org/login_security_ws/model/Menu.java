package org.login_security_ws.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="menu")
public class Menu {
    @Id
    @Column(name = "Menu_Id")
    private String menuId;
    @Column(name = "Menu_Name")
    private String menuName;
    @Column(name = "Menu_Level")
    private int menuLevel;
    @Column(name = "Parent")
    private String parent;


//    @ManyToMany(mappedBy = "menu")
//    Collection<Role> role = new ArrayList<>();
}

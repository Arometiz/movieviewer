package com.rico.movieviewer.restservice.tables;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "role")
public class Role {

    @Getter @Setter
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @OneToMany(mappedBy = "role")
    private Set<User> user;
}

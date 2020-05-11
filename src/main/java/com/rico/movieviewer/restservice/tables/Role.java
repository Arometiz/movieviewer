package com.rico.movieviewer.restservice.tables;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "role")
public class Role{

    @Id
    @Getter @Setter
    @Column(name = "role_id")
    private Integer roleId;

    @Getter @Setter
    @Column(name = "name")
    private String name;

    @Getter @Setter
    @OneToMany(mappedBy = "role")
    @JsonBackReference
    private Set<User> users;
}

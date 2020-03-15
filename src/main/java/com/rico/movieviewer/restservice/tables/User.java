package com.rico.movieviewer.restservice.tables;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Getter @Setter
    @Column(name = "user_id")
    private String userId;

    @Getter @Setter
    @Column(name = "username")
    private String username;

    @Getter @Setter
    @Column(name = "password")
    private String password;

    @Getter @Setter
    @Column(name = "firstname")
    private String firstname;

    @Getter @Setter
    @Column(name = "lastname")
    private String lastname;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "role")
    @JsonManagedReference
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews;

}

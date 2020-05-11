package com.rico.movieviewer.restservice.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @JsonIgnore
    @Getter @Setter
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",
            strategy = "uuid")
    @Column(name = "user_id")
    private String userId;

    @Getter @Setter
    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Getter @Setter
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @Getter @Setter
    @Column(name = "firstname")
    private String firstname;

    @JsonIgnore
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

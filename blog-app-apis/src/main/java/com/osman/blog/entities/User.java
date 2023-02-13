package com.osman.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//defines a JPA entity class named User,
// which maps to a database table named "Users"
// using the @Entity and @Table annotations.
@Entity
@Table(name = "Users")
//The @NoArgsConstructor, @Getter, and @Setter annotations are provided
// by the Lombok library, which generates a constructor
// without any arguments and getters and setters
// for all non-static fields in the class at compile time.
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;
    private String email;
    private String password;
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();
}

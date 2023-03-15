package com.example.username.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;


    private String userFullNameEnglish;


    private String userFullNameNepali;


    private String userName;


    private String userEmail;


    private String userPhone;

    @Column(name = "is_active")
    private Boolean isActive = true;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "users_roles",
//            foreignKey = @ForeignKey(name = "FK_users_roles_userid"),
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
//            inverseForeignKey = @ForeignKey(name = "FK_users_roles_roleid"),
//            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
//            uniqueConstraints = @UniqueConstraint(name = "UNIQUE_users_roles_usridroleid", columnNames = {"user_id", "role_id"})
//    )
//    private Set<Role> roles;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Role.class)
    @JoinColumn(name = "user_role_id",foreignKey = @ForeignKey(name = "Fk_user_role"))
    private Role roles;


}
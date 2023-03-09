package com.example.username.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
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
    @Column(name = "u_id")
    private Short uId;


    private String userFullNameEnglish;


    private String userFullNameNepali;


    private String userName;


    private String userEmail;


    private String userPhone;

    @Column(name="is_active")
    private Boolean isActive=true;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {
            @JoinColumn(name = "user_id",referencedColumnName = "u_id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "role_id",referencedColumnName = "r_id")
            },
            uniqueConstraints = @UniqueConstraint(name = "UNIQUE_user_role", columnNames = {"user_id", "role_id"})
    )
    @JsonIgnore
    private Set<Role> roles;


}
package com.example.username.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = User.class)
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "fk_user_role_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = Role.class)
    @JoinColumn(name = "role_id",foreignKey = @ForeignKey(name = "fk_user_role_role"))
    private Role role;
}

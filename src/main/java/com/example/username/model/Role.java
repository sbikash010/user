package com.example.username.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;


    private String roleNameEnglish;

    private String roleNameNepali;

    @Column(name="is_active")
    private Boolean isActive=true;


}

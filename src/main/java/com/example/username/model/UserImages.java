package com.example.username.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "image")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Short id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "userimage_id", foreignKey = @ForeignKey(name = "fk_user_image_user"))
    private User user;

    @Column(name = "order_no")
    private String orderNo;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_current")
    private Boolean isCurrent = true;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "orignal_file_name", nullable = false)
    private String originalFileName;

    @Column(name = "size")
    @Min(value = 1)
    private Short maxImageSize;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "parent_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "fk_user_images_parent_id"
//            )
//    )
//    private UserImages parent;
}

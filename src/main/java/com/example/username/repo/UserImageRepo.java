package com.example.username.repo;

import com.example.username.model.UserImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserImageRepo extends JpaRepository<UserImages,Short> {
    @Query(nativeQuery = true, value = "select image_path from image where id=?1")
    Optional<String> getImagePathByUserId(Short id);
}

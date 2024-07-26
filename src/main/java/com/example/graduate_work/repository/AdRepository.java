package com.example.graduate_work.repository;

import com.example.graduate_work.entity.AdEntity;
import com.example.graduate_work.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdEntity, Integer> {

    List<AdEntity> findAdEntitiesByAuthor(UserEntity user);
}
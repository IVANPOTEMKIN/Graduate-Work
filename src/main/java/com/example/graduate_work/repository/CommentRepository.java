package com.example.graduate_work.repository;

import com.example.graduate_work.entity.AdEntity;
import com.example.graduate_work.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    Optional<CommentEntity> findCommentEntityByIdAndAd_Id(int idComment, int idAd);

    List<CommentEntity> findCommentEntitiesByAd(AdEntity ad);
}
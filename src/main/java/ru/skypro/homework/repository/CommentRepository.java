package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    Optional<CommentEntity> findCommentEntityByIdAndAd_Id(int idComment, int idAd);

    List<CommentEntity> findCommentEntitiesByAd(AdEntity ad);
}
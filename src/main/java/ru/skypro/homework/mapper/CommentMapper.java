package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.comment.CommentDTO;
import ru.skypro.homework.dto.comment.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.CommentEntity;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    /*
    Entity -> DTO
     */

    @Mapping(target = "author", source = "entity.author.id")
    @Mapping(target = "authorImage", source = "entity.author.avatar.filePath")
    @Mapping(target = "authorFirstName", source = "entity.author.firstName")
    @Mapping(target = "pk", source = "entity.id")
    CommentDTO toCommentDTO(CommentEntity entity);

    CreateOrUpdateCommentDTO toCreateOrUpdateCommentDTO(CommentEntity entity);

    /*
    DTO -> Entity
     */

    @Mapping(target = "author.id", source = "dto.author")
    @Mapping(target = "author.avatar.filePath", source = "dto.authorImage")
    @Mapping(target = "author.firstName", source = "dto.authorFirstName")
    @Mapping(target = "id", source = "dto.pk")
    CommentEntity toCommentEntity(CommentDTO dto);

    CommentEntity toCommentEntity(CreateOrUpdateCommentDTO dto);
}
package ru.skypro.homework.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <code> <b> id </b> </code> <i> ID комментария </i> <br>
 * <code> <b> createdAt </b> </code> <i> Дата и время создания комментария </i> <br>
 * <code> <b> text </b> </code> <i> Текст комментария </i> <br>
 * <code> <b> ad </b> </code> {@link AdEntity} <br>
 * <code> <b> author </b> </code> {@link UserEntity} <br>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdEntity ad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(text, that.text)
                && Objects.equals(ad, that.ad)
                && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                createdAt,
                text,
                ad,
                author);
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", text='" + text + '\'' +
                ", ad=" + ad +
                ", author=" + author +
                '}';
    }
}
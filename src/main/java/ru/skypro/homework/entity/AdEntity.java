package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * <code> <b> id </b> </code> <i> ID объявления </i> <br>
 * <code> <b> title </b> </code> <i> Заголовок объявления </i> <br>
 * <code> <b> price </b> </code> <i> Цена объявления </i> <br>
 * <code> <b> description </b> </code> <i> Описание объявления </i> <br>
 * <code> <b> image </b> </code> {@link ImageEntity} <br>
 * <code> <b> author </b> </code> {@link UserEntity} <br>
 * <code> <b> comments </b> </code> <code> List<{@link CommentEntity}> </code> <br>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ads")
public class AdEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @OneToMany(mappedBy = "ad",
            cascade = ALL)
    @JsonIgnore
    private List<CommentEntity> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdEntity adEntity = (AdEntity) o;
        return Objects.equals(id, adEntity.id)
                && Objects.equals(title, adEntity.title)
                && Objects.equals(price, adEntity.price)
                && Objects.equals(description, adEntity.description)
                && Objects.equals(image, adEntity.image)
                && Objects.equals(author, adEntity.author)
                && Objects.equals(comments, adEntity.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                title,
                price,
                description,
                image,
                author,
                comments);
    }

    @Override
    public String toString() {
        return "AdEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", author=" + author +
                ", comments=" + comments +
                '}';
    }
}
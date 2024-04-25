package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * <code> <b> id </b> </code> <i> ID пользователя </i> <br>
 * <code> <b> username </b> </code> <i> Логин пользователя </i> <br>
 * <code> <b> password </b> </code> <i> Пароль пользователя </i> <br>
 * <code> <b> firstName </b> </code> <i> Имя пользователя </i> <br>
 * <code> <b> lastName </b> </code> <i> Фамилия пользователя </i> <br>
 * <code> <b> phoneNumber </b> </code> <i> Телефон пользователя </i> <br>
 * <code> <b> role </b> </code> <i> Роль пользователя </i> <br>
 * <code> <b> avatar </b> </code> {@link ImageEntity} <br>
 * <code> <b> ads </b> </code> <code> List<{@link AdEntity}> </code> <br>
 * <code> <b> comments </b> </code> <code> List<{@link CommentEntity}> </code> <br>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private ImageEntity avatar;

    @OneToMany(mappedBy = "author",
            cascade = ALL)
    @JsonIgnore
    private List<AdEntity> ads;

    @OneToMany(mappedBy = "author",
            cascade = ALL)
    @JsonIgnore
    private List<CommentEntity> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && role == that.role
                && Objects.equals(avatar, that.avatar)
                && Objects.equals(ads, that.ads)
                && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                username,
                password,
                firstName,
                lastName,
                phoneNumber,
                role,
                avatar,
                ads,
                comments);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", avatar=" + avatar +
                ", ads=" + ads +
                ", comments=" + comments +
                '}';
    }
}
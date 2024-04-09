package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", nullable = false)
    private ImageEntity avatar;

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    @JsonIgnore
    private List<AdEntity> ads;

    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    @JsonIgnore
    private List<CommentEntity> comments;
}
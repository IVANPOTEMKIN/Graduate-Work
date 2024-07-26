package com.example.graduate_work.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String path;

    private Long size;

    private String type;

    @Transient
    @JsonIgnore
    private byte[] data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageEntity image = (ImageEntity) o;
        return Objects.equals(id, image.id)
                && Objects.equals(path, image.path)
                && Objects.equals(size, image.size)
                && Objects.equals(type, image.type)
                && Arrays.equals(data, image.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, path, size, type);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
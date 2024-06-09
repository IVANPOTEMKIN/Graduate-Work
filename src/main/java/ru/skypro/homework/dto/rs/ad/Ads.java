package ru.skypro.homework.dto.rs.ad;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.skypro.homework.dto.rs.ad.Ad;

import java.util.List;

@Data
@AllArgsConstructor
public class Ads {
    private Integer count;
    private List<Ad> results;
}

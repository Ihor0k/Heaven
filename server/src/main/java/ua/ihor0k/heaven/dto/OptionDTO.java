package ua.ihor0k.heaven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionDTO {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private String image;
}

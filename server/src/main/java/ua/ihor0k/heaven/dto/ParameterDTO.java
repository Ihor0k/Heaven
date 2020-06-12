package ua.ihor0k.heaven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParameterDTO {
    private Long id;
    private String name;
    private String description;
    private List<OptionDTO> options;
}

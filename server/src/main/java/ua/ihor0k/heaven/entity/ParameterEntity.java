package ua.ihor0k.heaven.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "parameters")
@NoArgsConstructor
public class ParameterEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Integer order;
    @OneToMany(mappedBy = "parameter", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OptionEntity> options;

    public ParameterEntity(String name, String description, Integer order, List<OptionEntity> options) {
        this.name = name;
        this.description = description;
        this.order = order;
        this.options = options;
    }
}

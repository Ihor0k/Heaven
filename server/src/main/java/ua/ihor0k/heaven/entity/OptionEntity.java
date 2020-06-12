package ua.ihor0k.heaven.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Entity
@Table(name = "options")
@NoArgsConstructor
public class OptionEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Integer order;
    private String image;
    @ManyToOne(optional = false)
    @JoinColumn(name = "parameter_id")
    private ParameterEntity parameter;

    public OptionEntity(String name, String description, Integer price, Integer order, String image, ParameterEntity parameter) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.order = order;
        this.image = image;
        this.parameter = parameter;
    }
}

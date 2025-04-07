package example.day04._과제4.model.entity;

import example.day04._과제4.model.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int quantity;

    // dto 변환
    public ProductDto toDto() {
        return ProductDto.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .quantity(this.quantity)
                .createDateTime(this.getCreateDateTime())
                .build();
    }
}

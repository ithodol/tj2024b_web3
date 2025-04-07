package example.day04._과제4.model.dto;

import example.day04._과제4.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private int quantity;
    // + 등록날짜
    private LocalDateTime createDateTime;

    // entity 변환
    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .quantity(this.quantity)
                .build();
    }
}

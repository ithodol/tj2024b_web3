package example.day03._과제3.model.dto;

import example.day03._과제3.model.entity.CourseEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseDto {
    private int cno;
    private String cname;

    public CourseEntity toEntity() {
        return CourseEntity.builder()
                .cno(this.cno)
                .cname(this.cname)
                .build();
    }

}

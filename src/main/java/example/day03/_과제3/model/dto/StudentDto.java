package example.day03._과제3.model.dto;

import example.day03._과제3.model.entity.CourseEntity;
import example.day03._과제3.model.entity.StudentEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {
    private int sno;
    private String sname;

    // + 과정에 학생 등록시 특정한 과정의 PK
    private int cno;

    public StudentEntity toEntity() {
        return StudentEntity.builder()
                .sno(this.sno)
                .sname(this.sname)
                .build();
    }
}

package example.day03._과제3;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDto {
    private int sno;
    private String sname;

    public StudentEntity toEntity(){
        return StudentEntity.builder()
               .sno(this.sno)
               .sname(this.sname)
               .build();
    }
}

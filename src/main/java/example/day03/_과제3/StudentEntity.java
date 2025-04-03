package example.day03._과제3;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
@Builder
public class StudentEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;
    private String sname;

    public StudentDto toDto(){
        return StudentDto.builder()
                .sno(this.sno)
                .sname(this.sname)
                .build();
    }
}

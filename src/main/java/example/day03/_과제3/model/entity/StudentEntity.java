package example.day03._과제3.model.entity;

import example.day03._과제3.BaseTime;
import example.day03._과제3.model.dto.CourseDto;
import example.day03._과제3.model.dto.StudentDto;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student")
public class StudentEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;
    @Column
    private String sname;

    // FK 설정, 단방향
    @ManyToOne
    private CourseEntity courseEntity;

    public StudentDto toDto() {
        return StudentDto.builder()
                .sno(this.sno)
                .sname(this.sname)
                .cno(this.courseEntity.getCno())
                .build();
    }
}

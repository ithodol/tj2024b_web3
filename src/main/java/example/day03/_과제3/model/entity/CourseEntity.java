package example.day03._과제3.model.entity;


import example.day03._과제3.BaseTime;
import example.day03._과제3.model.dto.CourseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "course")
public class CourseEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    @Column
    private String cname;

    // 양방향
    @ToString.Exclude // 순환참조 방지
    @Builder.Default // 해당 엔티티를 빌더 패턴으로 생성할 경우 초기값 사용
    @OneToMany(mappedBy = "courseEntity", cascade = CascadeType.ALL)
    private List<StudentEntity> studentEntityList = new ArrayList<>();

    public CourseDto toDto() {
        return CourseDto.builder()
                .cno(this.cno)
                .cname(this.cname)
                .build();
    }
}

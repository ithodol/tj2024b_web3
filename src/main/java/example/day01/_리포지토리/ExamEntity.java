package example.day01._리포지토리;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "student1")
public class ExamEntity {
    // 학번
    @Id
    private String id;


    // 이름
    @Column(nullable = false)
    private String name;



    // 국어점수
    @Column
    private int kor;



    // 영어점수
    @Column
    private int eng;
}

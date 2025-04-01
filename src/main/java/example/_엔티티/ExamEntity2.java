package example._엔티티;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // 해당 클래스를 DB 테이블과 매핑 관계 주입 (ORM 영속성 컨텍스트에 저장)
@Table(name = "exam2") // 테이블명 정의. 생략시 클래스명으로 정의됨
public class ExamEntity2 {
    @Id // PK 제약조건 정의
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성된 값
    private long id;

    @Column(nullable = true, unique = false)
    private String col1;

    @Column(nullable = false, unique = true)
    private String col2;

    @Column(columnDefinition = "longtext")
    private String col3;

    @Column(name = "제품명", length = 30, insertable = true, updatable = true)
    private String col4;

    @Column private LocalDate col5;
    @Column private LocalDateTime col6;
}

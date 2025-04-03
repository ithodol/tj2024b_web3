package example.day03._JPA연관관계;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity // 해당 클래스는 데이터베이스와 영속 관계로 사용할 예정
@Table(name = "day03category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno; // 카테고리번호
    private String cname; // 카테고리명

    // + 양방향, 게시물 여러개 참조
    // @OneToMany(mappedBy = "단방향의멤버변수명")// 양방향 참조테이블은 자바에서만 참조한다
    @ToString.Exclude // 순환참조 방지
    @Builder.Default // 빌더패턴 사용시 초기값 대입
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 제약조건 옵션 : 만약 PK가 삭제되면 FK는?
    private List<Board> boardList = new ArrayList<>();
}

/*
    - 영속성 제약조건 옵션 (1. cascade 2. fetch)
    [1] @OneToMany(cascade = CascadeType.XXX)
        1. cascade = CascadeType.ALL
            부모(PK)가 삭제/수정/저장되면 자식(FK)도 같이 삭제/수정/저장 (REMOVE/MERGE/PERSIST)

        2. cascade = CascadeType.REMOVE
            부모(PK)가 삭제되면 자식(FK)도 같이 삭제

        3. cascade = CascadeType.MERGE
            부모(PK)가 수정되면 자식(FK)도 같이 수정

        4. cascade = CascadeType.DETACH
            부모(PK)가 영속성 해제하면 자식(FK)도 같이 해제

        5. cascade = CascadeType.PERSIST
            부모(PK)가 저장되면 자식(FK)도 같이 저장

        6. cascade = CascadeType.REFRESH
            부모(PK)가 새로 불러올 때 자식(FK)도 같이 새로 불러온다(새로고침)

    [2] @OneToMany(fetch = FetchType.XXX), @ManyToOne(fetch = FetchType.XXX)
        1. fetch = FetchType.EAGER (기본값)
            해당 엔티티를 조회( .findXXX() )할 때 참조되는 모든 객체를 즉시 불러온다
            단점 : 불필요한 엔티티가 모두 가져오기 때문에 (메모리)로드 기능 저하

        2. fetch = FetchType.LAZY
            지연로딩 : 해당 엔티티를 조회할 때 참조되는 객체를 불러오지 않고 .get()를 참조할 때 참조되는 객체를 불러온다
            특징 : 초기 로딩이 빠르다 메모리 사용량을 적절하게 사용하기 때문에 성능 최적화


*/

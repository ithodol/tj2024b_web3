package example.day02._toDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

// entity 목적 : DB 테이블의 영속/연결된 객체
// * 사용되는 계층 : 서비스레이어(비즈니스로직)
@Entity
@Table(name = "book1")
@Data // 롬복
@Builder // 필터패턴
public class ExamEntity1 {
    @Id
    private String id; // 도서 식별번호
    private String title; // 도서명
    private int price; // 도서 가격

    // (1) entity -> dto 변환 함수
    public ExamDto toDto(){
        // this 키워드 : 해당 메소드를 호출한 인스턴스(객체) 내 프로젝트 this

        // 1. 일반 생성자
        // return new ExamDto(this.id, this.title, this.price);

        // 2. 빌더패턴
        // 규칙적인 생성자의 유연성을 보장하는 메소드를 제공하는 패턴
        // 클래스명.builder().필드명(값).필드명(값).build();
        return ExamDto.builder()
                .id(this.id)
                .title(this.title)
                .price(this.price)
                .build();
    } // class toDto end
} // class ExamEntity1 end

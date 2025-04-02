package example.day02._toDto;


import lombok.Data;
import lombok.Builder;

// DTO 목적 : 서로 다른 계층/레이어 간의 이동 객체
// VO 목적 : 수정 불가능한 객체(읽기모드 / setter 없음 / 불변)
// * 사용되는 계층 : 컨트롤러 레이어(view <-> controller, controller <-> service)
@Data // 롬복
@Builder // 빌더패턴
public class ExamDto {
    private String id;
    private String title;
    private int price;

    // (2) dto -> entity 객체 변환 함수
    public ExamEntity1 toEntity() {
        return ExamEntity1.builder()
                .id(this.id)
                .title(this.title)
                .price(this.price)
                .build();
    } // class toEntity end
} // class ExamDto end
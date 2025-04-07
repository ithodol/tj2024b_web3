package example.day04.model.dto;

import example.day04.model.entity.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
    private int id; //식별번호
    private String title; // 제목
    private String content; // 내용
    private boolean done; // 상태
    // + 등록날짜
    private LocalDateTime createDateTime;

    // dto -> entity 변환 함수
    public TodoEntity toEntity(){
        return TodoEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .done(this.done)
                .build();
    }


}

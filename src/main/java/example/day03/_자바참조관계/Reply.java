package example.day03._자바참조관계;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Reply {
    private int 댓글번호;
    private String 댓글내용;

    // Board 타입 멤버변수 선언 가능?
    private Board board; // FK, 단방향 참조
    // 양방향 참조 / 하나의 게시물이 여러개의 댓글 참조
    private List<Reply> replyList;
}

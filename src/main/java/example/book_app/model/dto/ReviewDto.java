package example.book_app.model.dto;

import example.book_app.model.entity.BookEntity;
import example.book_app.model.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private int rid;
    private String rcontent;
    private String rpassword;

    private int id; // book id
    private LocalDateTime createDateTime;

    public ReviewEntity toEntity(BookEntity book){
        return ReviewEntity.builder()
                .rid(this.rid)
                .rcontent(this.rcontent)
                .rpassword(this.rpassword)
                .book(book)
                .build();
    }
}

package example.book_app.model.dto;

import example.book_app.model.entity.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private int id;
    private String title;
    private String writer;
    private String memo;

    private String password;

    private LocalDateTime createDateTime;

    public BookEntity toEntity(){
        return BookEntity.builder()
                .id(this.id)
                .title(this.title)
                .writer(this.writer)
                .memo(this.memo)
                .password(this.password)
                .build();
    }
}

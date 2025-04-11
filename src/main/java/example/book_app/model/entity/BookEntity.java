package example.book_app.model.entity;

import example.book_app.model.dto.BookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String writer;
    private String memo;

    private String password;

    public BookDto toDto(){
        return BookDto.builder()
                .id(this.id)
                .title(this.title)
                .writer(this.writer)
                .memo(this.memo)
                .password(this.password)
                .createDateTime(this.getCreateDateTime())
                .build();
    }
}

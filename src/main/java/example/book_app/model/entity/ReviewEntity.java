package example.book_app.model.entity;

import example.book_app.model.dto.BookDto;
import example.book_app.model.dto.ReviewDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private String rcontent;
    private String rpassword;

    public ReviewDto toDto(){
        return ReviewDto.builder()
                .rid(this.rid)
                .rcontent(this.rcontent)
                .rpassword(this.rpassword)
                .createDateTime(this.getCreateDateTime())
                .build();
    }

}

package example._과제;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "taskbook")
public class TaskEntity {
    // 도서 ID
    @Id
    private int id;

    // 도서명
    @Column
    private String title;


    // 저자
    @Column
    private String author;


    // 출판사
    @Column
    private String publisher;



    // 출판연도
    @Column(nullable = false)
    private LocalDate date;


}

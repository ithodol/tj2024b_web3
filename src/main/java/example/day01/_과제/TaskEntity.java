package example.day01._과제;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "taskbook")
public class TaskEntity {
    // 도서 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 도서명
    @Column(nullable = false, length = 100)
    private String title;


    // 저자
    @Column(nullable = false, length = 30)
    private String author;


    // 출판사
    @Column(nullable = false, length = 50)
    private String publisher;



    // 출판연도
    @Column
    private int date;


}

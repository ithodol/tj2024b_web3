package example.day01._엔티티;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "task1todo")
public class ExamTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private boolean state;

    @Column(nullable = false)
    private LocalDate createat;

    @Column(nullable = false)
    private LocalDateTime updateat;

}

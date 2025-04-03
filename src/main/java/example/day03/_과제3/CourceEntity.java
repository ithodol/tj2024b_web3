package example.day03._과제3;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "cource")
public class CourceEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private String cname;

    public CourceDto toDto(){
        return CourceDto.builder()
               .cno(this.cno)
               .cname(this.cname)
               .build();
    }
}

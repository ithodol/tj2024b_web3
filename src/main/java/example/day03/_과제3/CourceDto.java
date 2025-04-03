package example.day03._과제3;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourceDto {
    private int cno;
    private String cname;

    public CourceEntity toEntity(){
        return CourceEntity.builder()
               .cno(this.cno)
               .cname(this.cname)
               .build();
    }
}

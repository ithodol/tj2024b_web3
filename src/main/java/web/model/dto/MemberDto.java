package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import web.model.entity.MemberEntity;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
    private int mno;
    private String memail;
    private String mpwd;
    private String mname;

    private LocalDateTime createDateTime;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpwd(this.mpwd)
                .mname(this.mname)
                .build();

    }
}

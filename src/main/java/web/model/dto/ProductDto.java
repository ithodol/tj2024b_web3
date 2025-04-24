package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.entity.ImgEntity;
import web.model.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString@Getter@Setter@Builder
@NoArgsConstructor@AllArgsConstructor
public class ProductDto {

    // * 제픔 등록할때 필요한 필드
    private String pname;           // - 제품명
    private String pcontent;        // - 제품설명
    private int pprice;             // - 제품가격
    private List<MultipartFile> files = new ArrayList<>(); // - 업로드할 제품이미지들
    private long cno; // - 제품카테고리 번호



    // * toEntity : 제품 등록시 사용
    public ProductEntity toEntity(){
        return ProductEntity.builder()
                .pname( this.pname )
                .pcontent( this.pcontent )
                .pprice( this.pprice )
                // cno 대신 CategoryEntity 넣기
                // mno 대신 MemberEntity 넣기
                .build();

    }

    // * 제품 조회할 때 필요한 필드
    private long pno; // 제품 번호
    private int pview; // 제품 조회수
    private String memail; // 제품 등록한 회원 아이디
    private String cname; // 제품 카테고리명
    private List<String> imges = new ArrayList<>(); // 이미지들

    public static  ProductDto toDto(ProductEntity productEntity){
        return ProductDto.builder()
                .pname(productEntity.getPname())
                .pcontent(productEntity.getPcontent())
                .pprice(productEntity.getPprice())
                .pno(productEntity.getPno())
                .pview(productEntity.getPview())
                .memail(productEntity.getMemberEntity().getMemail()) // 작성자 회원 아이디
                .cno(productEntity.getCategoryEntity().getCno()) // 카테고리 번호
                .cname(productEntity.getCategoryEntity().getCname()) // 카테고리 이름
                .imges( // 제품의 이미지들
                        productEntity.getImgEntityList().stream()
                                .map(ImgEntity :: getIname)
                                .collect(Collectors.toList())
                )
                .build();
    }

}









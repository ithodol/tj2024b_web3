package web.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import web.model.entity.ImgEntity;
import web.model.entity.ProductEntity;
import java.util.List;

@Repository
public interface ProductRepository
        extends JpaRepository<ProductEntity,Long> {

    // 1. JPA 기본적인 함수 제공
    // save, findAll, findById, delete ...


    // 2. 쿼리메소드 (규칙 : 카멜)
        // findByXXX : select
            // findByCno(필드명) [X] : ProductEntity에는 cno가 존재하지 않음
            // findByPname [O] : ProductEntity에는 pname이 존재해서 가능
    List<ProductEntity> findByCategoryEntityCno(Long cno);

    // 3. 네이티브 쿼리 (규칙 : mysql 코드)
        // query 문에서 매개변수 사용시 앞에 :(콜론)
    @Query(value = "select * from product where cno = :cno", nativeQuery = true)
    List<ProductEntity> nativeQuery1(Long cno);

    // (*) 카테고리별(cno) 와 제품키워드(keyword) 검색 + 페이징처리
    // @Query( value = "SQL 작성" , nativeQuery = true )
    // SQL 문에서 매개변수 사용시 매개변수앞에 :(콜론)
    @Query( value = "SELECT * FROM product " +
            " WHERE ( :cno IS NULL OR :cno = 0 OR cno = :cno ) " + // java == null vs sql : IS NULL
            " AND ( :keyword IS NULL OR pname LIKE %:keyword% )" , nativeQuery = true)
    Page<ProductEntity> findBySearch(Long cno , String keyword , Pageable pageable );

    // 방법4* JPQL , 규칙 : 자바만든 sql 코드/메소드
}
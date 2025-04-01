package example._리포지토리;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// 엔티티(Table)를 조작(DML) 하는 인터페이스
// DML = select/insert/update/delete
// 해당 인터페이스에 JPA 엔티티 상속
// JpaRepository<조작할엔티티클래스명, 해당엔티티의ID타입 >
// < > : 제네릭
@Repository // 스프링 컨테이너에 빈 등록
public interface ExamEntityRepository
        extends JpaRepository<ExamEntity, String> {
}


// CRUD 메소드
// 1. .save(저장할엔티티객체); : insert 자동
//      존재하지 않음 pk면 insert,  존재하는 pk면 update
//      반환값 : insert / update 이후 영속된 객체

// 2. .findAll();
//      모든 엔티티를 select 한다
//      반환값 : 리스트 타입으로 반환


// 3. .findById(조회할 pk값);
//      pk값과 일치하는 엔티티를 select 한다
//      반환값 : Optional<엔티티>

// Optional 클래스 : null 관련된 메소드를 제공하는 클래스
// -> nullPointerException 방지하고자 객체를 포장하는 클래스
// 주요 메소드
// 1. .isPresent() : null 이면 false, 객체 있으면 true
// 2. .get() : 객체 반환
// 3. .orElse(null일 때 값) : 객체를 반환하는데 null이면 지정된 값 반환
// 4. .orElseThrow(예외객체) : 객체를 반환하는데 null이면 예외 발생
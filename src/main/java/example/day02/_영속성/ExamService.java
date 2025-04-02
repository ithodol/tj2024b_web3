package example.day02._영속성;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamService {
    // private final ExamRepository examRepository;

    // 엔티티 매니저를 이용한 영속성 조작
    @PersistenceContext // 영속성 컨텍스트(영속성들이 저장된 메모리 공간) 의 객체 주입
    private EntityManager entityManager;

    public void get(){
        // 1. 비영속 상태 (Transient) : (그냥) 자바 객체
        ExamEntity examEntity1 = new ExamEntity();
        examEntity1.setName("유재석");
        System.out.println("** 비영속상태 **" + examEntity1);

        // 2. 영속 상태 (Persistent), .persist(비영속객체); : 영속성 부여
        entityManager.persist(examEntity1); // 영속성 부여 // 자바객체 <-- 영속성 --> DB 매핑
        System.out.println("** 영속상태 **" + examEntity1);
            // * 영속 상태에서 수정
            examEntity1.setName("강호동");
            entityManager.flush(); // 트랜잭션 중간에 commit
        System.out.println("** 영속상태 **" + examEntity1);

        // 3. 준영속 상태 (Detached) .detach(영속된객체); : 영속성 해제
//        entityManager.detach(examEntity1);
//        System.out.println("** 비영속상태 **" + examEntity1);
//            // * 준영속 상태에서 수정
//            examEntity1.setName("신동엽");
//            entityManager.flush();
//        System.out.println("** 비영속상태 **" + examEntity1); // DB 수정 안 됨
        // 3-1. 준영속 -> 영속
        entityManager.merge(examEntity1);


        // 4. 삭제 상태 (Remove) .remove(영속된객체); : 영속성 삭제
            // 준영속 상태에서는 삭제 불가
        entityManager.remove(examEntity1);
    }
}

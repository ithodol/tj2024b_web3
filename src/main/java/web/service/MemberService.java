package web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.dto.MemberDto;
import web.model.entity.MemberEntity;
import web.model.repository.MemberRepository;

@Service // Spring MVC2 service
@RequiredArgsConstructor
@Transactional // 트랜잭션 : 여러개의 SQL을 하나의 논리 단위
// 트랜잭션은 성공 or 실패 / 부분 성공 X
// 메소드 안에서 여러가지 SQL 실행할 경우 하나라도 오류가 발생하면 롤백(취소) * JPA 엔티티 수정 필수 *
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입
    public boolean signup(MemberDto memberDto){
        // 1. 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // 암호화 비크립트 객체 생성
        String hashedPwd = passwordEncoder.encode(memberDto.getMpwd()); // .encode(암호화할자료) : 암호화를 지원하는 함수
        memberDto.setMpwd(hashedPwd);
        // 2. dto -> entity 변환
        MemberEntity memberEntity = memberDto.toEntity();
        // 3. 리포지토리를 이용한 entity 영속화 하기, 영속된 결과 반환
        MemberEntity saveEntity = memberRepository.save(memberEntity);
        // 4. 영속된 엔티티의 pk 확인
        if(saveEntity.getMno() >= 1){
            return true;
        }
        return false;
    }
}

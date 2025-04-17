package web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.dto.MemberDto;
import web.model.entity.MemberEntity;
import web.model.repository.MemberRepository;
import web.util.JwtUtil;

@Service // Spring MVC2 service
@RequiredArgsConstructor
@Transactional // 트랜잭션 : 여러개의 SQL을 하나의 논리 단위
// 트랜잭션은 성공 or 실패 / 부분 성공 X
// 메소드 안에서 여러가지 SQL 실행할 경우 하나라도 오류가 발생하면 롤백(취소) * JPA 엔티티 수정 필수 *
public class MemberService {
    private final MemberRepository memberRepository;

    // [1] 회원가입
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

    // * JWT 객체 주입 받기
    private final JwtUtil jwtUtil;

    // [2] 로그인 / 성공시 token, 실패시 null
    public String login(MemberDto memberDto){
        // 1. 이메일(아이디)를 DB에서 조회하여 엔티티 찾기
        MemberEntity memberEntity = memberRepository.findByMemail(memberDto.getMemail());

        // 2. 조회된 엔티티가 없으면
        if(memberEntity == null){
            return null;
        }
        // 3. 조회된 엔티티의 비밀번호 검증
            // 비크립트 객체 생성
            // 비교하기 / .matches(입력받은패스워드, 암호화된패스워드)
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean inMatch = passwordEncoder.matches(memberDto.getMpwd(), memberEntity.getMpwd());
        // 4. 비밀번호 검증 실패하면
        if(inMatch == false) {
            return null; // 로그인 실패
        }
        // 5. 비밀번호 검증 성공하면 / 세션 할당 or 토큰 할당
        String token = jwtUtil.createToken(memberEntity.getMemail());
        System.out.println("발급된 토큰 : " + token);
        return token;
    }

    // [3] 전달받은 token으로 검증하여 유효한 token = 회원정보(dto) 반환, 유효하지 않은 token = null 반환
    public MemberDto info(String token){
        // 1. 전달받은 token으로 검증하기 or 세션 호출/검증
        String memail = jwtUtil.validateToken(token);

        // 2. 검증 실패하면 '비로그인' 이거나 유효기간 만료, 실패
        if(memail == null){
            return null;
        }
        // 3. 검증 성공하면 token에 저장된 이메일로 entity 조회
        MemberEntity memberEntity = memberRepository.findByMemail(memail);
        // 4. 조회된 entity 없으면 실패
        if(memberEntity == null){
            return null;
        }
        // 5. 조회 성공시 조회된 entity를 dto로 변환하여 반환
        return memberEntity.toDto();
    }












}

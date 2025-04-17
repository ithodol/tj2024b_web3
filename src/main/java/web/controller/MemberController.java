package web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController // Spring MVC2 controller
@RequestMapping("/member")
@RequiredArgsConstructor // final(수정불가능한 최종) 필드 생성자 자동 생성
@CrossOrigin("*")
// final 사용하는 이유 : 관례적으로 클래스 내부에서 사용하는 모든 필드들을 수정 불가능 상태로 사용하기 위함
public class MemberController {
    // 다른 곳에서 해당하는 필드를 수정하지 못하도록 final 사용
    // @RequiredArgsConstructor 사용했기 때문에 @Autowired 하지 않아도 됨
    private final MemberService memberService;


    // 회원가입
    @PostMapping("/signup")
    public boolean signup(@RequestBody MemberDto memberDto){
        return memberService.signup(memberDto);
    }


    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody MemberDto memberDto){
        return memberService.login(memberDto);
    }


    // 로그인된 회원 검증 / 내 정보 조회
    @GetMapping("/info")
    // @RequestHeader : HTTP 헤더 정보를 매핑하는 어노테이션 JWT 정보는 HTTP 헤더에 담을 수 있다
    // Authorization : 인증 속성, {Authorization : token값}
    // @RequestParam : HTTP 헤더의 경로 쿼리스트링 매핑하는 어노테이션
    // @RequestBody : HTTP 본문의 객체를 매핑하는 어노테이션
    // @PathVariable : HTTP 헤더의 경로 값 매핑하는 어노테이션
    // header : {'Authorization : 'yJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3NDQ3NzI5NTksImV4cCI6MzE3NDQzMTA4NjQ5OTU4N30.EmbdY71TV66hRLr6yP5VMaFBCDjDEI9JcFtLtH59LjY'}
    public MemberDto info(@RequestHeader("Authorization") String token){
        System.out.println("token : " + token);
        return memberService.info(token);
    }

}

package web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    // [1] 회원가입
    @PostMapping("/signup")
//    public boolean signup(@RequestBody MemberDto memberDto){
//        return memberService.signup(memberDto);
//    } (수정 전)
    public ResponseEntity<Boolean> signup(@RequestBody MemberDto memberDto){
        boolean result = memberService.signup(memberDto);
        if(result) {
            // * 개발자가 원하는 응답 코드 반환 가능
            return ResponseEntity.status(201).body(true); // 201(create ok 라는 뜻) true(반환값)
        }else {
            return ResponseEntity.status(400).body(false); // 400(bad request : 잘못된 요청)
        }
    }


    // [2] 로그인
    @PostMapping("/login")
//    public String login(@RequestBody MemberDto memberDto){
//        return memberService.login(memberDto);
//    }
    public ResponseEntity<String> login(@RequestBody MemberDto memberDto){
        String token = memberService.login(memberDto);
        if(token != null){ // 만약 토큰이 존재하면(로그인 성공이면)
            return ResponseEntity.status(200).body(token); // 200 코드 전송
        }else {
            return ResponseEntity.status(401).body("로그인 성공"); // 401(인증실패/권한없음)
        }
    }


    // [3] 로그인된 회원 검증 / 내정보 조회
    // @RequestHeader : HTTP 헤더 정보를 매핑하는 어노테이션 JWT 정보는 HTTP 헤더에 담을 수 있다
    // Authorization : 인증 속성, {Authorization : token값}
    // @RequestParam : HTTP 헤더의 경로 쿼리스트링 매핑하는 어노테이션
    // @RequestBody : HTTP 본문의 객체를 매핑하는 어노테이션
    // @PathVariable : HTTP 헤더의 경로 값 매핑하는 어노테이션
    // header : {'Authorization : 'yJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3NDQ3NzI5NTksImV4cCI6MzE3NDQzMTA4NjQ5OTU4N30.EmbdY71TV66hRLr6yP5VMaFBCDjDEI9JcFtLtH59LjY'}
    @GetMapping("/info")
//    public MemberDto info(@RequestHeader("Authorization") String token){
//        System.out.println("token : " + token);
//        return memberService.info(token);
//    }
    public ResponseEntity<MemberDto> info(@RequestHeader("Authorization") String token){
        MemberDto memberDto = memberService.info(token);
        if(memberDto != null){
            return ResponseEntity.status(200).body(memberDto); // 200 ok, 조회된 회원정보 반환
        }else {
            return ResponseEntity.status(403).build(); // 403 : noContent(자료없음)
        }
    }

    // [4] 로그아웃 , 로그아웃 할 토큰 가져오기.
    @GetMapping("/logout")
//    public void logout(
//            @RequestHeader("Authorization") String token ) {
//        memberService.logout( token );
//    }
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token ) {
        memberService.logout( token );
        return ResponseEntity.status(204).build(); // 204 : 요청 성공했지만 반환값이 없다
    }

    // [5] 실시간 최근 24시간내 로그인 한 접속자 수
    @GetMapping("/login/count")
//    public int loginCount(){
//        return memberService.loginCount();
//    }
    public ResponseEntity<Integer> loginCount(){
        int result = memberService.loginCount();
        return ResponseEntity.status(200).body(result); // 200 : 요청 성공, 응답값 반환
    }

}

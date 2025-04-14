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

    @PostMapping("/signup")
    public boolean signup(@RequestBody MemberDto memberDto){
        return memberService.signup(memberDto);
    }
}

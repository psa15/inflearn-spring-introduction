package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    //생성자 주입이라고 함 (생성자 주입 외에도 세터주입, 필드 주입 등이 있음)
    @Autowired
    public MemberController(MemberService memberService) { //MemberService클래스에 @Service 작성 안하면 memberService 에러남
        this.memberService = memberService;
    }


}

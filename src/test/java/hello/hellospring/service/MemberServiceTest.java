package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
/*
give : 이 데이터를
when : 이때 주면
then : 이 결과가 나와야 해

 */
class MemberServiceTest {

    //테스트는 한글로도 적어도 됨

/*
//MemberService 클래스에 constructor를 생성함
    MemberService memberService = new MemberService();
    //db클리어 위해
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
*/
    //Dependency Injection(DI) 사용
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //각 테스트를 실행 하기 전에 new MemoryMemberRepository();로 memberRepository를 생성하여 위에 넣고
    //memberService를 MembserService 클래스에 생성한 MemberService의 파라미터인 memberRepository에 넣어줌
    //MemberService 입장에서 내가 직접생성하는 것이 아니라 외부에서 memberRepository를 넣어줌 == DI
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {

        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
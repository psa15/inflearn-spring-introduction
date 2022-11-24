package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {


    /*
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    로 하면 테스트 작성 시 또 새로운 메모리가 생성되는 것,,,,?
     */
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
        회원가입
         */
    public long join(Member member) {
        //같은 이름이 있는 중복 회원은 X
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());

        //옵셔널이기 때문에 값이 있으면 이미 존재하는 회원입니다 라고 뜨게 하기
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
        위의 내용을 validateDuplicateMember란 이름의 메소드로 따로 뺌
        */
        validateDuplicateMember(member); //회원 중복 검증

        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID){
        return memberRepository.findById(memberID);
    }
}

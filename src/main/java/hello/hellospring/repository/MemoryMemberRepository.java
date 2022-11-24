package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{


    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        //람다식 문법
        //member에서 받아온 이름이 파라미터의 이름이 같으면 하나라도 반환하라는 의미
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());
    }

    //테스트할 때 한 메소드가 끝나면 임의로 저장된 값을 깨끗하게 비우기 위한 메소드
    public void clearStore() {
        
        //.clear 하면 저장된 데이터가 클리어됨
        store.clear();
    }
}
//위의 코드들이 잘 돌아가는지 확인하기 위해 테스트 케이스를 작성

package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
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

    //테스트할 때 한 메소드가 끝나면 깨끗하게 비우기 위해
    public void clearStore() {
        store.clear();
    }
}

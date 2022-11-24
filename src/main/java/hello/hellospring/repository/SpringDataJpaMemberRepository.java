package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
스프링 데이터 JPA
- 리포지토리에 구현 클래스 없이 인터페이스 만으로 개발을 완료
- 반복 개발해온 기본 CRUD 기능도 스프링 데이터 JPA가 모두 제공
 */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>,MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}

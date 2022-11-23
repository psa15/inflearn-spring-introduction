package hello.hellospring.repository;

        import hello.hellospring.domain.Member;
        import org.assertj.core.api.Assertions;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.Test;

        import java.util.List;
        import java.util.Optional;

        import static org.assertj.core.api.Assertions.*;

//회원 도메인과 회원 리포지토리가 잘 작성되었는지 확인하기 위한 테스트 케이스
//테스트는 각각 독립적으로 실행
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있음
    //다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있다
    //메소드 실행된 후 끝날 때마다 어떤 동작을 하게 함
    //MemoryMemberRepository에 clearStore라는 메소드 추가함
    @AfterEach
    public void afterEach() {

        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);
        //then
        Member result = repository.findById(member.getId()).get();
        //findById(member.getId())의 타입이 Optional이기 때문에 get으로 값을 가져올 수 있음
        assertThat(result).isEqualTo(member);
        //Assertions.assertThat(result).isEqualTo(member);
        //에서 Assertions에 커서 두고 alt + enter 치면 옵션들이 나와서 Assertions를 생략할 수 있음
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");

        repository.save(member1); //리포지토리에 member를 저장

        //Member result = repository.findById(member.getId()).get();
        //System.out.println("result=" + (result == member.getId()));
        //Assertions.assertEquals(member, null);

        Member member2 = new Member();
        member2.setName("spring2");

        repository.save(member2);

        //Optional<Member> result = Optional.of(repository.findByName("spring1").get());
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        //result에 저장된 결과가 2쌍이 맞는지 확인
    }
}


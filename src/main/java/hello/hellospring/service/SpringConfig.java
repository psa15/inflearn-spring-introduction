package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//@Service나 @Autowired 등을 사용하지 않고(컴포넌트 스캔을 사용하지 않고) 빈 설정하기
//자바 코드로 직접 스프링 빈 등록하기
@Configuration
public class SpringConfig {
/*
    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */
/*

    //jpa
    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/

    //스프링 데이터 JPA
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean

    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

/*    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);

    }*/

}

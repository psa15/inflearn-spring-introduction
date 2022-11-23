package hello.hellospring.domain;

//데이터 : 회원 ID, 이름
//기능 : 회원 등록, 조회
//아직 데이터 저자오가 선정되지 않음(가상의 시나리오) -> 회원리포지토리를 인터페이스로 구현(가벼운 메모리 기반의 저장소에 데이터 저장)
public class Member {
    public String name;
    private Long id;

    public Long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}

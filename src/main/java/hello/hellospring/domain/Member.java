package hello.hellospring.domain;

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

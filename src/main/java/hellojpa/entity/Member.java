package hellojpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // JPA의 객체이다!
public class Member {

    @Id // PK값이다!
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

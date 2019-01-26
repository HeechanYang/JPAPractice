package hellojpa.entity;

import javax.persistence.*;
import java.util.Date;

@Entity // JPA의 객체이다!
public class Member {

    @Id // PK값이다!
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20)
    private String name;

    private int age;

    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    // 현업에선 무조건 STRING으로 써야 함
    // ORDINAL 썼을 경우 인덱스가 들어감 (0, 1, ..)
    // 기본값은 ORDINAL
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

//    @ManyToOne
//    @ManyToOne(fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID") // Default로 되긴 하지만 웬만하면 적어주자
    private Team team;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}

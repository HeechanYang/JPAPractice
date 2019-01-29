package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.MemberType;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // 1. EntityManagerFactory 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 2. EntityManagerFactory를 통해 EntityManager 생성
        EntityManager em = emf.createEntityManager();
        // 3. EntityManager를 통해 트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        // 4. 트랜잭션 시작
        tx.begin();

        // 에러 났을 경우를 대비해 try-catch로 처리
        try {
            // 5. 새로 삽입할 Team 객체 생성
            Team team = new Team();
            team.setName("EndlessCreation");


            // 5. 새로 삽입할 Member 객체 생성
            Member member = new Member();
//            member.setId(100L);
            member.setName("양희찬");
            member.setAge(26);
            member.setTeam(team);
            member.setRegDate(new Date());
            member.setMemberType(MemberType.ADMIN);

            // 역방향(주인이 아닌 방향, team->member)만 연관관계 설정
            // 반영 안됨!
            // team.getMembers().add(member);

            // 6.EntityManager를 통해, 생성한 Team 객체 저장
            em.persist(team);
            // 6.EntityManager를 통해, 생성한 Member 객체 저장
            em.persist(member);

            // 7. 조회

            Member member1 = em.find(Member.class, member.getId());
            Team team1 = member1.getTeam();

            System.out.printf("\n\nzname : %s, team : %s\n\n", member1.getName(), team1.getName());

            // 8. 트랜잭션 커밋
            tx.commit();

        } catch (Exception e) {
            // 9. 실패하면 롤백!
            System.out.println(e);
            tx.rollback();
        } finally {
            // 10. EntityManagerFactory 및 EntityManager 종료
            em.close();
            emf.close();
        }
    }
}

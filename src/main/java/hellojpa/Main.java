package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            // 5. 새로 삽입할 Member 객체 생성
            Member member = new Member();
            member.setId(100L);
            member.setName("양희찬");

            // 6.EntityManager를 통해 생성한 Member 객체 저장
            em.persist(member);

            // 7. 트랜잭션 커밋
            tx.commit();
        } catch (Exception e) {
            // 8. 실패하면 롤백!
            tx.rollback();
        } finally {
            // 9. EntityManagerFactory 및 EntityManager 종료
            em.close();
            emf.close();
        }
    }
}

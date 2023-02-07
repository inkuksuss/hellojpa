package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("TeamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("TeamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("user1");
//            member1.setAge(20);
            member1.changeTeam(teamA);

            Member member2 = new Member();
            member2.setUsername("user2");
//            member2.setAge(20);
            member2.changeTeam(teamA);

            Member member3 = new Member();
            member3.setUsername("user3");
//            member3.setAge(20);
            member3.changeTeam(teamB);

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

//            em.flush();
//            em.clear();

            int resultList = em.createQuery("update Member m set m.age = 30")
                    .executeUpdate();

            System.out.println("resultList = " + resultList);

            em.clear();

            Member findMember1 = em.find(Member.class, member1.getId());
            Member findMember2 = em.find(Member.class, member2.getId());

            System.out.println("findMember1 = " + findMember1);
            System.out.println("findMember2 = " + findMember2);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}

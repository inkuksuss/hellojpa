package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("user1");
            member.setAge(10);
            member.changeTeam(team);

            Member member2 = new Member();
            member2.setUsername("user2");
            member2.setAge(10);
//            member.changeTeam(team);

            em.persist(member);
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select function('group_concat', m.username) from Member m";
            String singleResult = em.createQuery(query, String.class).getSingleResult();

            System.out.println("singleResult = " + singleResult);


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

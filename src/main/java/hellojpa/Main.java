package hellojpa;

import ch.qos.logback.core.net.SyslogOutputStream;
import hellojpa.entity.Member;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setName("안녕하");
            member.setAge(30);
            member.setTeam(team);
            em.persist(member);

//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//
//            findTeam.getName();

//            List<Member> members = findTeam.getMembers();
//            for (Member member1 : members){
//                System.out.println("member1 = "+member1);
//            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}

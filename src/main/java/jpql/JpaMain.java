package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);
            /** 기본 문법과 쿼리 API */
            /**
             * TypeQuery: 반환 타입이 명확할 때 사용
             * Query: 반환 타입이 명확하지 않을 때 사용
             */
//            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
//            Query query3 = em.createQuery("select m.username, m.age from Member m");

            /**
             * getResultList(): 결과가 하나 이상일 때, 리스트 반환하고 결과가 없으면 빈 리스트 반환, NullPointerException 걱정 x
             * getSingleResult(): 결과가 정확히 하나, 단일 객체 반환, 결과가 없으면: NoResultException, 둘 이상이면: NonUniqueResultException
             */
//            TypedQuery<Member> query4 = em.createQuery("select m from Member m", Member.class);
//            List<Member> resultList = query4.getResultList();
//            Member result = query4.getSingleResult();
//            System.out.println("result = " + result);
            /**
             * 파라미터 바인딩 - 이름 기준, 위치 기준
             * 위치 기준은 웬만하면 사용 x
             */
            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("result = " + result.getUsername());

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
}

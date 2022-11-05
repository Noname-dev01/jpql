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
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("관리자");
//            member.setAge(10);
//            member.setType(MemberType.ADMIN);
//
//            member.setTeam(team);
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
            /** 경로 표현식
             * 상태 필드(static field): 경로 탐색의 끝, 탐색x (ex:m.username)
             * 단일 값 연관 경로: 묵시적 내부 조인(inner join) 발생, 탐색 O (ex: m.team)
             * 컬렉션 값 연관 경로: 묵시적 내부 조인 발생 탐색 X
             * -FROM 절에서 명시적 조인을 통해 별칭을 얻으면 별칭을 통해 탐색 가능
             * 실무에서는 묵시적 조인을 쓰지 않고 명시적 조인을 써야 쿼리 튜닝하기도 쉽고 묵시적 조인을 쓰면 나중에 골치 아파진다.
             * */
            Member member1 = new Member();
            member1.setUsername("관리자1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            em.persist(member2);

            em.flush();
            em.clear();

//            String query = "select m.username from Member m";
//            String query = "select m.team from Member m";
            String query = "select t.members from Team t";
            List<String> result = em.createQuery(query, String.class)
                    .getResultList();
            for (String s : result) {
                System.out.println("s = " + s);
            }

            /**JPQL 함수
             * CONCAT,SUBSTRING,TRIM,LOWER,UPPER,LENGTH,LOCATE,ABS,SQRT,MOD,SIZE,INDEX(JPA 용도)
             * */

//            String query = "select concat('a', 'b') from Member ";
//            String query1 = "select substring(m.username, 2, 3) from Member m";
//            String query2 = "select locate('de','abcdefg') from Member m";
//            List<Integer> result1 = em.createQuery(query2, Integer.class)
//                    .getResultList();
//            String query3 = "select size(t.members) from Team t";
//            String query4 = "select index(t.members) from Team t"; //index는 잘 안쓰는게 좋다
//            String query5 = "select t from Team t";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            /** 조건식(CASE 등등)
             * COALESCE(): 하나씩 조회해서 null이 아니면 반환
             * NULLIF(): 두 값이 같으면 null 반환, 다르면 첫번째 값 반환
             * */
//            String query = "select " +
//                    "case when m.age <= 10 then '학생요금' " +
//                    "     when m.age >= 60 then '경로요금' " +
//                    "     else '일반요금' " +
//                    "end " +
//                    "from Member m";
//            String query = "select coalesce(m.username, '이름 없는 회원') as username " +
//                    "from Member m ";
//            String query = "select nullif(m.username, '관리자') as username " +
//                    "from Member m ";
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//            for (String s : result) {
//                System.out.println("s = " + s);
//            }

            /** JPQL 타입 표현과 기타식 */
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            member.setType(MemberType.ADMIN);
//
//            member.setTeam(team);
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            String query = "select m.username,'HELLO', true from Member m " +
//                    "where m.type = :userType";
//            List<Object[]> result = em.createQuery(query)
//                    .setParameter("userType", MemberType.ADMIN)
//                    .getResultList();
//
//            for (Object[] objects : result) {
//                System.out.println("objects = " + objects[0]);
//                System.out.println("objects = " + objects[1]);
//                System.out.println("objects = " + objects[2]);
//            }

            /** 조인 */
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//
//            member.setTeam(team);
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            String query = "select m from Member m inner join m.team t";
//            List<Member> result = em.createQuery(query, Member.class)
//                    .getResultList();

            /** 페이징 */
//            for (int i = 0; i < 100; i++) {
//                Member member = new Member();
//                member.setUsername("member"+i);
//                member.setAge(i);
//                em.persist(member);
//            }
//            em.flush();
//            em.clear();
//
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            System.out.println("result.size = " + result.size());
//            for (Member member1 : result) {
//                System.out.println("member1 = " + member1);
//            }

            /** 프로젝션(SELECT)
             * SELECT 절에 조회할 대상을 지정하는 것
             */
//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
//            em.flush();
//            em.clear();
//            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username,m.age) from Member m", MemberDTO.class)
//                    .getResultList();
//            MemberDTO memberDTO = result.get(0);
//            System.out.println("memberDTO.getUsername() = " + memberDTO.getUsername());
//            System.out.println("memberDTO.getAge() = " + memberDTO.getAge());

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
//            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", "member1")
//                    .getSingleResult();
//            System.out.println("result = " + result.getUsername());

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

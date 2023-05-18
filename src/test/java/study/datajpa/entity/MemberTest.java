package study.datajpa.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("기본 member와 team 생성 테스트")
    void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // 초기화
        em.flush();
        em.clear();

        // 확인
        List<Member> memberList = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        for (Member member : memberList) {
            System.out.println("member = " + member);
        }
    }

    @Test
    @DisplayName("기본 CRUD 테스트")
    void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        // 단건 조회 검증
        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        findMember1.setUsername("member3");

        // 리스트 조회 검증
//        List<Member> all = memberJpaRepository.findAll();
//        assertThat(all.size()).isEqualTo(2);
//
//        // 카운트 검증
//        long count = memberJpaRepository.count();
//        assertThat(count).isEqualTo(2);
//
//        // 삭제 검증
//        memberJpaRepository.delete(member1);
//        memberJpaRepository.delete(member2);
//        long delectedcount = memberJpaRepository.count();
//        assertThat(delectedcount).isEqualTo(0);
    }


}
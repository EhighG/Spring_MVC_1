package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest { // JUnit 5부터는 public 안붙여도 된다.
    // 여러 메소드 동시에 테스트시 순서는 보장 X!

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given : 이런 상황에서
        Member member = new Member("hello", 20);

        // when : 이런 것이 벌어졌을 때
        Member savedMember = memberRepository.save(member);

        // then : 이런 결과가 나와야 한다.
        Member memberInDB = memberRepository.findById(savedMember.getId());
        assertThat(memberInDB).isEqualTo(savedMember); // assertThat(Real).isEqualTo(Expected)
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member("test1", 1);
        Member member2 = new Member("test2", 2);

        // when
        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}
package hello.servlet.web.frontcontrollerpattern.v2.implementation;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontrollerpattern.MyViewV2;
import hello.servlet.web.frontcontrollerpattern.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyViewV2 process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 비즈니스 로직 실행
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // Model에 데이터 담기
        request.setAttribute("member", member);

        return new MyViewV2("/save-result.jsp");
    }
}

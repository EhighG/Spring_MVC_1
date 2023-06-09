package hello.servlet.web.frontcontrollerpattern.v2.implementations;

import hello.servlet.web.frontcontrollerpattern.MyView;
import hello.servlet.web.frontcontrollerpattern.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/new-form.jsp");
    }
}

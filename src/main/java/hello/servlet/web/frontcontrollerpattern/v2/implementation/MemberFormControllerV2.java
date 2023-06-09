package hello.servlet.web.frontcontrollerpattern.v2.implementation;

import hello.servlet.web.frontcontrollerpattern.MyViewV2;
import hello.servlet.web.frontcontrollerpattern.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyViewV2 process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyViewV2("/new-form.jsp");
    }
}

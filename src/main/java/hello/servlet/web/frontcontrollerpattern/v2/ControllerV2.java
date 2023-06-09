package hello.servlet.web.frontcontrollerpattern.v2;

import hello.servlet.web.frontcontrollerpattern.MyViewV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

//    final String VIEW_PATH_BASE = "/WEB-INF/views"; // View와 같이 분리해서 단일 책임을 유지하는 것이 낫다.

    // view 관련 로직을 메인 컨트롤러들 밖으로
    MyViewV2 process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        /*
        application/json은 utf-8을 사용하도록 정의돼 있어서, 추가 charset 파라미터가 필요하지 않음
        그러나 response.getWriter() 를 사용 시, charset 파라미터가 자동 추가됨(setCharacterEncoding이 있는 경우 해당 값으로, 없으면 defalut로
        따라서 response.getOutputStream()을 사용하거나, writer를 쓰는 경우 charset값을 줘야 함.
         */

        HelloData helloData = new HelloData();
        helloData.setUsername("인코딩테스트");
        helloData.setAge(26);

        // object -> json text
        String result = objectMapper.writeValueAsString(helloData);

        response.getWriter().write(result);
    }
}

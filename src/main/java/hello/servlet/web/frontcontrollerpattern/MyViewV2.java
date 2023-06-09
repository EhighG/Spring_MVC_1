package hello.servlet.web.frontcontrollerpattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyViewV2 {
    private String viewPath;
    private final String BASE_PATH = "/WEB-INF/views";

    public MyViewV2(String subPath) {
        this.viewPath = BASE_PATH + subPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

}

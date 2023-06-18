package hello.servlet.web.frontcontrollerpattern.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param paramMap
     * @param model
     * @return String viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}

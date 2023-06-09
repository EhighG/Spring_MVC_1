package hello.servlet.web.frontcontrollerpattern.v3;

import hello.servlet.web.frontcontrollerpattern.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}

package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData {

    private String username; // input 데이터의 key 이름과 일치해야 한다.
    private int age;

}

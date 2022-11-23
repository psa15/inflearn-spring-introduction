package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        //model.addAttribute : hello.html파일에 값을 넘겨주기 위해 키 이름인 data와 그 값인 hello!!를 설정
        return "hello";
        //template의 hello.html파일을 찾아 실행시키게 됨
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        //@RequestParam : 파라미터를 name이라는 이름으로 받을 거야
        model.addAttribute("name", name);
        return "hello-template";

    }

    //API방식 : 따로 view파일인 html파일이 없어도 결과 그대로 웹 화면에 보여짐
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody //기본적으로 JSON 방식으로 반환함.
    public Hello helloApi(@RequestParam("name") String name) {

        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //주소에서 ?name=sua 붙여서 치면 JSON방식으로 결과 나옴 {"name" : "sua"}

    }

    //Hello 객체를 생성 -> 위의 api방식에서 사용하기 위해
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

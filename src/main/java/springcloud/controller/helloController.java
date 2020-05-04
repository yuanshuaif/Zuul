package springcloud.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 10326 on 2020/1/14.
 */
@RestController
public class helloController {

    @RequestMapping(value = "/local/{name}")
    public String sayHello(@PathVariable String name){
        return "localhost" + name;
    }

}

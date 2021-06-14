package cc.mrbird.web.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }

    @DeleteMapping("products/{id}")
    public String deleteProducts(@PathVariable String id){
        return "delete by id:"+ id;
    }


}

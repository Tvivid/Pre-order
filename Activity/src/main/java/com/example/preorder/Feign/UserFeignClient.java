package com.example.preorder.Feign;

import com.example.preorder.Entity.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user", url = "http://localhost:8081/v1")
public interface UserFeignClient {

    @GetMapping("/member")
    Member getMember(@RequestParam("token") String token);


}
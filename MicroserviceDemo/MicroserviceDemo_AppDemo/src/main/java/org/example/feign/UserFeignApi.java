package org.example.feign;

import org.example.feign.fallback.UserFeignFallback;
import org.example.web.SimpleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user",fallback = UserFeignFallback.class)
public interface UserFeignApi {

    @GetMapping("/shop/{id}")
    SimpleResponse select(@PathVariable(name = "id") String id);

}

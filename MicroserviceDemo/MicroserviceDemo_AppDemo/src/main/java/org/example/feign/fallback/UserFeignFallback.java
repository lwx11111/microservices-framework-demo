package org.example.feign.fallback;

import org.example.feign.UserFeignApi;
import org.example.web.SimpleResponse;
import org.springframework.stereotype.Component;


@Component
public class UserFeignFallback implements UserFeignApi {

    @Override
    public SimpleResponse select(String id) {
        SimpleResponse response = new SimpleResponse();
        response.setCode(505);
        response.setMessage("服务降级");
        return response;
    }

}

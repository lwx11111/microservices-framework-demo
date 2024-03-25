package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.User;
import org.example.service.IUserService;
import org.example.web.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ljc
 * @since 2024-02-22
 */
@RestController
@Tag(name = "服务")
@RequestMapping("/test")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public void test(){
        System.out.println("test");
    }
}


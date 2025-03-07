package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.annotation.Logs;
import org.example.rabbitMQ.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.example.web.SimpleResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.ShopItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.IShopItemService;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 店铺物品表（只有官方一家店） 前端控制器
 * </p>
 *
 * @author ljc
 * @since 2024-03-13
 */
@RestController
@Tag(name = "店铺物品表（只有官方一家店）服务")
@RequestMapping("/shopitem")
public class ShopItemController {
    @Autowired
    private IShopItemService service;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    @ResponseBody
    @Operation(description = "创建店铺物品表（只有官方一家店）")
    @Logs
    public SimpleResponse save(@RequestBody ShopItem obj){
        SimpleResponse response = new SimpleResponse();
        try {
            rabbitTemplate.convertAndSend(RabbitmqConfig.ORDER_PEDDING_TOPIC, obj);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @PutMapping("/{id}")
    @ResponseBody
    @Operation(description = "更新店铺物品表（只有官方一家店）")
    public SimpleResponse update(@PathVariable(name = "id") String id,@RequestBody ShopItem obj){
        SimpleResponse response = new SimpleResponse();
        try {
            service.updateByParam(obj,obj.getParams());
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "按ID删除店铺物品表（只有官方一家店）")
    public SimpleResponse remove(@PathVariable(name = "id") String id){
        SimpleResponse response = new SimpleResponse();
        try {
        service.removeById(id);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping("/{id}")
    @Operation(description = "按ID查询店铺物品表（只有官方一家店）")
    @ResponseBody
    public SimpleResponse select(@PathVariable(name = "id") String id) {
        SimpleResponse response = new SimpleResponse();
        try {
            response.setData(service.getById(id));
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/dels")
    @ResponseBody
    @Operation(description = "按ID删除多个店铺物品表（只有官方一家店）")
    public SimpleResponse removes(@RequestBody List<String> ids){
        SimpleResponse response = new SimpleResponse();
        try {
            service.removeByIds(ids);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }


    @PostMapping("/delby")
    @Operation(description = "条件删除店铺物品表（只有官方一家店）")
    public void deleteBy(@RequestBody(required = false) Map<String, String> params) {
        service.deleteBy(params);
    }

    @PostMapping("/selby")
    @ResponseBody
    public List<ShopItem> selectBy(@RequestBody(required = false) Map<String, String> params) {
        return  service.selectBy(params);
    }

    @PostMapping("/selpage")
    @Operation(description = "分页查询店铺物品表（只有官方一家店）")
    @ResponseBody
    public SimpleResponse selectPage(@RequestBody Map<String, String> params) {
        SimpleResponse response = new SimpleResponse();
        try {
            IPage<ShopItem> page = service.selectPage(params);
            response.setData(page);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/selpageCustomSqlByWrapper")
    @ResponseBody
    public IPage<ShopItem> selpageCustomSqlByWrapper(@RequestBody Map<String, String> params) {
        return service.selpageCustomSqlByWrapper(params);
    }

    @PostMapping("/selpageCustomSqlByMap")
    @Operation(description = "分页查询-自定义sql-Map店铺物品表（只有官方一家店）")
    @ResponseBody
    public IPage<ShopItem> selpageCustomSqlByMap(@RequestBody Map<String, String> params) {
        return service.selpageCustomSqlByMap(params);
    }

    /**
     * 下载excel模板
     * @param response
     * @param request
     * @throws Exception
     */
    @PostMapping("/downloadExcelTemplate")
    @ResponseBody
    public void downloadExcelTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception {
        service.downloadExcelTemplate(response, request);
    }

    /**
     * excel导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/uploadExcel")
    @ResponseBody
    public void uploadExcel(@RequestParam MultipartFile file) throws Exception {
        service.uploadExcel(file);
    }

    @PostMapping("/excel")
    @ResponseBody
    public void excel(HttpServletResponse response, HttpServletRequest request, @RequestBody Map<String, String> params) throws Exception {
        service.excel(response, request, params);
    }
}


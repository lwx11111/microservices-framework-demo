package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.domain.User;
import org.example.dao.UserMapper;
import org.example.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import org.apache.poi.ss.usermodel.Workbook;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Lists;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.example.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwx20
 * @since 2024-02-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void register(User obj) throws Exception {
        this.save(obj);
    }

    @Override
    public User login(User obj) throws Exception {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<User>()
                .eq(User::getName, obj.getName())
                .eq(User::getPassword, obj.getPassword());
        User user = this.getOne(query);
        if(user == null){
            throw new Exception("用户名或密码错误");
        }
        return user;
    }

    @Override
    public void saveByParam(User obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(User obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<User> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<User> selectBy(Map<String, String> params) {
        QueryWrapper<User> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<User> selectPage(Map<String, String> params) {
        Page<User> page = PageUtils.pageHandler(params);
        QueryWrapper<User> query = getQuery(params);
        IPage<User> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<User> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<User> page = PageUtils.pageHandler(params);
        QueryWrapper<User> query = getQuery(params);
        IPage<User> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<User> selpageCustomSqlByMap(Map<String, String> params) {
        Page<User> page = PageUtils.pageHandler(params);
        IPage<User> result = this.baseMapper.selpageCustomSqlByMap(page, params);
        return result;
    }

    /**
     * 下载excel模板
     *
     * @param response HttpServletResponse
     * @param request  HttpServletRequest
     * @return: void
    */
    @Override
    public void downloadExcelTemplate(HttpServletResponse response, HttpServletRequest request) throws Exception{
        List<User> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "User"), User.class, data);
        String fileName = String.format("User_%d.xls", System.currentTimeMillis());
        response.setHeader("Content-Disposition", "attachment;Filename="+ fileName);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * 导入数据
     * @param file
     * @throws Exception
     */
    @Override
    public void uploadExcel(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ImportParams params = new ImportParams();
        // bean 导入
        List<User> dataList = new ExcelImportService().importExcelByIs(inputStream, User.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<User> query = new QueryWrapper<>();
        List<User> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "User"),
        User.class, data);
        String fileName = String.format("User_%d.xls", System.currentTimeMillis());
        response.setHeader("Content-Disposition", "attachment;Filename="+ fileName);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     * 定义数据查询条件
     * @param params
     * @return
     */
    private  QueryWrapper<User> getQuery(Map<String, String> params){
        QueryWrapper<User> query  = new QueryWrapper<>();
        if(params==null||params.size()<1) {
            return  query;
        }
        for (Map.Entry<String, String> entry:params.entrySet()){
            if(StringUtils.isBlank(entry.getValue())){
                continue;
            }
            if("id".equals(entry.getKey())){
                query.eq("id",entry.getValue());
            }
            if("name".equals(entry.getKey())){
                query.eq("name",entry.getValue());
            }
            if("password".equals(entry.getKey())){
                query.eq("password",entry.getValue());
            }
            if("nickname".equals(entry.getKey())){
                query.eq("nickname",entry.getValue());
            }
            if("avatar".equals(entry.getKey())){
                query.eq("avatar",entry.getValue());
            }
        }
        return  query;
    }
}

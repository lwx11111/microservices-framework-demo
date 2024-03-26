package org.example.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.imports.ExcelImportService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.dao.LogMapper;
import org.example.domain.Log;
import org.example.service.ILogService;
import org.example.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ljc
 * @since 2024-03-25
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Override
    public void saveByParam(Log obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(Log obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<Log> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<Log> selectBy(Map<String, String> params) {
        QueryWrapper<Log> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<Log> selectPage(Map<String, String> params) {
        Page<Log> page = PageUtils.pageHandler(params);
        QueryWrapper<Log> query = getQuery(params);
        IPage<Log> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<Log> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<Log> page = PageUtils.pageHandler(params);
        QueryWrapper<Log> query = getQuery(params);
        IPage<Log> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<Log> selpageCustomSqlByMap(Map<String, String> params) {
        Page<Log> page = PageUtils.pageHandler(params);
        IPage<Log> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<Log> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "Log"), Log.class, data);
        String fileName = String.format("Log_%d.xls", System.currentTimeMillis());
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
        List<Log> dataList = new ExcelImportService().importExcelByIs(inputStream, Log.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<Log> query = new QueryWrapper<>();
        List<Log> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "Log"),
        Log.class, data);
        String fileName = String.format("Log_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<Log> getQuery(Map<String, String> params){
        QueryWrapper<Log> query  = new QueryWrapper<>();
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
            if("time".equals(entry.getKey())){
                query.eq("time",entry.getValue());
            }
            if("inParameter".equals(entry.getKey())){
                query.eq("in_parameter",entry.getValue());
            }
            if("outParameter".equals(entry.getKey())){
                query.eq("out_parameter",entry.getValue());
            }
            if("remark".equals(entry.getKey())){
                query.eq("remark",entry.getValue());
            }
        }
        return  query;
    }
}

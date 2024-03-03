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
import org.example.dao.CommentMapper;
import org.example.domain.Comment;
import org.example.service.ICommentService;
import org.example.utils.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lwx20
 * @since 2023-12-18
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public Boolean saveComments(Map<String, String> comments) throws Exception {
        String userId = comments.get("userId");
        String orderId = comments.get("orderId");
        String shopComment = comments.get("shopComment");
        String shopId = comments.get("shopId");
        String riderId = comments.get("riderId");
        String riderComment = comments.get("riderComment");
        //
        if (userId == null){
            throw new Exception("userId不能为空");
        }
        if (orderId == null){
            throw new Exception("orderId不能为空");
        }
        if (shopComment == null){
            throw new Exception("shopComment不能为空");
        }
        if (shopId == null){
            throw new Exception("shopId不能为空");
        }
        // 保存
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setOrderId(orderId);
        comment.setContent(shopComment);
        comment.setShopId(shopId);
        LocalDateTime dateTime = LocalDateTime.now();
        comment.setReleaseTime(dateTime);
        // 保存
        if (riderComment != null && riderId != null){
            Comment comment2 = new Comment();
            comment2.setUserId(userId);
            comment2.setOrderId(orderId);
            comment2.setContent(riderComment);
            comment2.setRiderId(riderId);
            comment2.setReleaseTime(dateTime);
            return this.save(comment2) && this.save(comment);
        } else {
            return this.save(comment);
        }

    }

    @Override
    public void saveByParam(Comment obj,Map<String, String> params){
        this.save(obj);
    }

    @Override
    public void updateByParam(Comment obj,Map<String, String> params){
        this.updateById(obj);
    }

    @Override
    public void deleteBy(Map<String, String> params) {
        QueryWrapper<Comment> query = new QueryWrapper<>();
        if(!query.isEmptyOfWhere()) {
            remove(query);
        }
    }

    @Override
    public List<Comment> selectBy(Map<String, String> params) {
        QueryWrapper<Comment> query = new QueryWrapper<>();
        return list(query);
    }

    @Override
    public IPage<Comment> selectPage(Map<String, String> params) {
        Page<Comment> page = PageUtils.pageHandler(params);
        QueryWrapper<Comment> query = getQuery(params);
        IPage<Comment> result = this.page(page, query);
        return result;
    }

    @Override
    public IPage<Comment> selpageCustomSqlByWrapper(Map<String, String> params) {
        Page<Comment> page = PageUtils.pageHandler(params);
        QueryWrapper<Comment> query = getQuery(params);
        IPage<Comment> result = this.baseMapper.selpageCustomSqlByWrapper(page, query);
        return result;
    }

    @Override
    public IPage<Comment> selpageCustomSqlByMap(Map<String, String> params) {
        Page<Comment> page = PageUtils.pageHandler(params);
        IPage<Comment> result = this.baseMapper.selpageCustomSqlByMap(page, params);
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
        List<Comment> data = Lists.newArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "Comment"), Comment.class, data);
        String fileName = String.format("Comment_%d.xls", System.currentTimeMillis());
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
        List<Comment> dataList = new ExcelImportService().importExcelByIs(inputStream, Comment.class, params, false).getList();
        this.saveBatch(dataList);
        // map 导入
        // List<Map<String, Object>> maps = ExcelImportUtil.importExcel(inputStream, Map.class, params);
        // System.out.println("maps = " + maps);
    }

    @Override
    public void excel(HttpServletResponse response, HttpServletRequest request, Map<String, String> params) throws Exception{
        QueryWrapper<Comment> query = new QueryWrapper<>();
        List<Comment> data = list(query);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(null, "Comment"),
        Comment.class, data);
        String fileName = String.format("Comment_%d.xls", System.currentTimeMillis());
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
    private  QueryWrapper<Comment> getQuery(Map<String, String> params){
        QueryWrapper<Comment> query  = new QueryWrapper<>();
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
            if("shopId".equals(entry.getKey())){
                query.eq("shop_id",entry.getValue());
            }
            if("userName".equals(entry.getKey())){
                query.eq("user_name",entry.getValue());
            }
            if("content".equals(entry.getKey())){
                query.eq("content",entry.getValue());
            }
            if("releaseTime".equals(entry.getKey())){
                query.eq("release_time",entry.getValue());
            }
            if("parentId".equals(entry.getKey())){
                query.eq("parent_id",entry.getValue());
            }
        }
        return  query;
    }
}

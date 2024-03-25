package org.example.dao;

import org.example.domain.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.Map;

/**
 * <p>
 * 商铺分类 Mapper 接口
 * </p>
 *
 * @author ljc
 * @since 2024-03-13
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<Category>
    */
    IPage<Category> selpageCustomSqlByWrapper(Page<Category> page, @Param(Constants.WRAPPER)QueryWrapper<Category> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<Category>
    */
    IPage<Category> selpageCustomSqlByMap(Page<Category> page, @Param("params") Map<String, String> params);
}

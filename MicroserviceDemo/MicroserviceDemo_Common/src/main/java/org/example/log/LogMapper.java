package org.example.log;

import org.example.domain.Log;
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
 *  Mapper 接口
 * </p>
 *
 * @author lwx20
 * @since 2024-03-25
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<Log>
    */
    IPage<Log> selpageCustomSqlByWrapper(Page<Log> page, @Param(Constants.WRAPPER)QueryWrapper<Log> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<Log>
    */
    IPage<Log> selpageCustomSqlByMap(Page<Log> page, @Param("params") Map<String, String> params);
}

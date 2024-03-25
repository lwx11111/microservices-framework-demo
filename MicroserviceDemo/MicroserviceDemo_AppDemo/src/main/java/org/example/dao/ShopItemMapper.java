package org.example.dao;

import org.example.domain.ShopItem;
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
 * 店铺物品表（只有官方一家店） Mapper 接口
 * </p>
 *
 * @author ljc
 * @since 2024-03-13
 */
@Mapper
public interface ShopItemMapper extends BaseMapper<ShopItem> {

    /**
     * 分页查询-自定义sql-Wrapper
     *
     * @param page
     * @param query
     * @return: IPage<ShopItem>
    */
    IPage<ShopItem> selpageCustomSqlByWrapper(Page<ShopItem> page, @Param(Constants.WRAPPER)QueryWrapper<ShopItem> query);

    /**
     * 分页查询-自定义sql-Map
     *
     * @param page
     * @param params
     * @return: IPage<ShopItem>
    */
    IPage<ShopItem> selpageCustomSqlByMap(Page<ShopItem> page, @Param("params") Map<String, String> params);
}

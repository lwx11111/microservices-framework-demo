package org.example.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 店铺物品表（只有官方一家店）
 * </p>
 *
 * @author lwx20
 * @since 2024-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_item")
@Schema(name="店铺物品表（只有官方一家店）_ShopItem对象", description="店铺物品表（只有官方一家店）")
public class ShopItem extends Model<ShopItem> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
        @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 物品名
    */
    @Schema(description = "物品名")
    @Excel(name = "物品名")
    @TableField("name")
    private String name;

    /**
    * 物品图片
    */
    @Schema(description = "物品图片")
    @Excel(name = "物品图片")
    @TableField("picture")
    private String picture;

    /**
    * 物品介绍
    */
    @Schema(description = "物品介绍")
    @Excel(name = "物品介绍")
    @TableField("description")
    private String description;

    /**
    * 物品分类
    */
    @Schema(description = "物品分类")
    @Excel(name = "物品分类")
    @TableField("category_id")
    private String categoryId;

    /**
    * 物品价格
    */
    @Schema(description = "物品价格")
    @Excel(name = "物品价格")
    @TableField("price")
    private BigDecimal price;

    @TableField(exist = false)
    private Map<String,String> params;


}

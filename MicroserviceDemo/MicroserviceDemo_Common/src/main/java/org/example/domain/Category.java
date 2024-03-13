package org.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.util.Map;
/**
 * <p>
 * 商铺分类
 * </p>
 *
 * @author lwx20
 * @since 2024-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("category")
@Schema(name="商铺分类_Category对象", description="商铺分类")
public class Category extends Model<Category> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
        @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 分类名
    */
    @Schema(description = "分类名")
    @Excel(name = "分类名")
    @TableField("name")
    private String name;

    /**
    * 图片
    */
    @Schema(description = "图片")
    @Excel(name = "图片")
    @TableField("picture")
    private String picture;

    @TableField(exist = false)
    private Map<String,String> params;


}

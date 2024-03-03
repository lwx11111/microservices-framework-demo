package org.example.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author lwx20
 * @since 2023-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("comment")
@Schema(name="_Comment对象", description="")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
        @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
    * 用户ID
    */
    @Schema(description = "用户ID")
    @Excel(name = "用户ID")
    @TableField("user_id")
    private String userId;

    /**
    * 商品ID
    */
    @Schema(description = "商品ID")
    @Excel(name = "商品ID")
    @TableField("shop_id")
    private String shopId;

    /**
    * 订单ID
    */
    @Schema(description = "订单ID")
    @Excel(name = "订单ID")
    @TableField("order_id")
    private String orderId;

    /**
    * 骑手ID
    */
    @Schema(description = "骑手ID")
    @Excel(name = "骑手ID")
    @TableField("rider_id")
    private String riderId;

    /**
    * 用户名
    */
    @Schema(description = "用户名")
    @Excel(name = "用户名")
    @TableField("user_name")
    private String userName;

    /**
    * 内容
    */
    @Schema(description = "内容")
    @Excel(name = "内容")
    @TableField("content")
    private String content;

    /**
    * 时间
    */
    @Schema(description = "时间")
    @Excel(name = "时间", exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("release_time")
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime releaseTime;

    /**
    * 回复id
    */
    @Schema(description = "回复id")
    @Excel(name = "回复id")
    @TableField("parent_id")
    private String parentId;

    @TableField(exist = false)
    private Map<String,String> params;


}

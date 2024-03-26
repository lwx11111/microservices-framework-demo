package org.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
 * 
 * </p>
 *
 * @author ；ljc
 * @since 2024-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("log")
@Schema(name="_Log对象", description="")
public class Log extends Model<Log> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
        @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Excel(name = "name")
    @TableField("name")
    private String name;

    @Excel(name = "time")
    @TableField("time")
        @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @Excel(name = "inParameter")
    @TableField("in_parameter")
    private String inParameter;

    @Excel(name = "outParameter")
    @TableField("out_parameter")
    private String outParameter;

    @Excel(name = "remark")
    @TableField("remark")
    private String remark;

    @TableField(exist = false)
    private Map<String,String> params;


}

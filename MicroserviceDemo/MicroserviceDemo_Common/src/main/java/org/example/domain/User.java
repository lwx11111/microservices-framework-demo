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

import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author lwx20
 * @since 2024-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@Schema(name="_User对象", description="")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    @Excel(name = "id")
        @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    /**
    * 用户名
    */
    @Schema(description = "用户名")
    @Excel(name = "用户名")
    @TableField("name")
    private String name;

    /**
    * 密码
    */
    @Schema(description = "密码")
    @Excel(name = "密码")
    @TableField("password")
    private String password;

    /**
    * 昵称
    */
    @Schema(description = "昵称")
    @Excel(name = "昵称")
    @TableField("nickname")
    private String nickname;

    /**
    * 头像
    */
    @Schema(description = "头像")
    @Excel(name = "头像")
    @TableField("avatar")
    private String avatar;

    @TableField(exist = false)
    private Map<String,String> params;


}

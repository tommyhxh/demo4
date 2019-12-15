package com.hisense.hitest2.pojo;
/**
 * @cmptId 1320538766611316984
 * @dmId 1320636827157135608
 * @author saasp-de
 * @date today
 */
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import com.hisense.orm.sql.Page;
import com.hisense.orm.sql.BaseTable;
import com.hisense.orm.annotation.TbColumn;
import com.hisense.orm.annotation.TbName;
import com.hisense.orm.annotation.RefColumn;
import com.hisense.orm.annotation.Relation;
import com.hisense.orm.annotation.RelationType;
import java.util.List;

@Getter
@Setter
@ApiModel(value="plus",description="plus")
@TbName(name = "plus")
public class Plus extends Page {

    @ApiModelProperty(value="pluId",name="商品ID")
    @TbColumn(column = "plu_id"  , isId = true )
    private String pluId;

    @ApiModelProperty(value="pluCode",name="商品名称")
    @TbColumn(column = "plu_code" )
    @NotBlank(message = "商品名称不可为空")
    @Length(max = 30,message = "商品名称最大长度30")
    private String pluCode;

    @ApiModelProperty(value="pluPrice",name="商品价格")
    @TbColumn(column = "plu_price" )
    @NotBlank(message = "商品价格不可为空")
    @Length(max = 20,message = "商品价格最大长度20")
    private String pluPrice;

    @ApiModelProperty(value="createBy",name="创建人")
    @TbColumn(column = "create_by" )
    @NotBlank(message = "创建人不可为空")
    @Length(max = 50,message = "创建人最大长度50")
    private String createBy;

    @ApiModelProperty(value="modifiedBy",name="修改人")
    @TbColumn(column = "modified_by" )
    @NotBlank(message = "修改人不可为空")
    @Length(max = 50,message = "修改人最大长度50")
    private String modifiedBy;

    @ApiModelProperty(value="gmtCreate",name="创建时间")
    @TbColumn(column = "gmt_create" )
    @NotBlank(message = "创建时间不可为空")
    @Length(max = 50,message = "创建时间最大长度50")
    private String gmtCreate;

    @ApiModelProperty(value="gmtModified",name="修改时间")
    @TbColumn(column = "gmt_modified" )
    @NotBlank(message = "修改时间不可为空")
    @Length(max = 50,message = "修改时间最大长度50")
    private String gmtModified;
}

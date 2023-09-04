package com.chenggongdu.demo.pojo.bo;

import com.chenggongdu.validate.EditGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 语料内容表 Bo
 *
 * @author chenggongdu
 */
@Data
@Accessors(chain = true)
@Schema(name = "语料内容表入参对象", description = "语料内容表入参对象")
public class CorpusBo {

    /**
     * 语料ID
     */
    @NotBlank(message = "语料ID不可为空", groups = {EditGroup.class})
    @Schema(description = "语料ID")
    private String corpusId;
    /**
     * 向量ID
     */
    @Schema(description = "向量ID")
    private String embedId;
    /**
     * 语料问题
     */
    @Schema(description = "语料问题")
    private String corpusQuestion;
    /**
     * 语料答案
     */
    @Schema(description = "语料答案")
    private String corpusAnswer;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;
    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date updateTime;

}

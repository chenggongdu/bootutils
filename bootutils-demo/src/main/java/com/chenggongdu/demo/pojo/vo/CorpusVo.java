package com.chenggongdu.demo.pojo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 语料内容表 Bo
 *
 * @author chenggongdu
 */
@Data
@Schema(name = "语料内容表出参对象", description = "语料内容表出参对象")
public class CorpusVo {

    /**
     * 语料ID
     */
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

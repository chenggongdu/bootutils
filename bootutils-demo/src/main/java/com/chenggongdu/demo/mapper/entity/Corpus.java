package com.chenggongdu.demo.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 语料内容表 实体对象
 *
 * @author chenggongdu
 */
@Data
@TableName("corpus")
@Accessors(chain = true)
public class Corpus {

    /**
     * 语料ID
     */
    @TableId("corpus_id")
    private String corpusId;
    /**
     * 向量ID
     */
    @TableField("embed_id")
    private String embedId;
    /**
     * 语料问题
     */
    @TableField("corpus_question")
    private String corpusQuestion;
    /**
     * 语料答案
     */
    @TableField("corpus_answer")
    private String corpusAnswer;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 创建时间
     */
    @TableField("update_time")
    private Date updateTime;

}

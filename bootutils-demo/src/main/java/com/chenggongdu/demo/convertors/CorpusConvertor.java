package com.chenggongdu.demo.convertors;

import com.chenggongdu.demo.mapper.entity.Corpus;
import com.chenggongdu.demo.pojo.bo.CorpusBo;
import com.chenggongdu.demo.pojo.vo.CorpusVo;
import org.springframework.beans.BeanUtils;

/**
 * 语料内容表 转换器
 */
public interface CorpusConvertor {

    static Corpus to(CorpusBo corpusBo) {
        Corpus entity = new Corpus();
        BeanUtils.copyProperties(corpusBo, entity);
        return entity;
    }

    static CorpusVo toVo(Corpus corpus) {
        CorpusVo vo = new CorpusVo();
        BeanUtils.copyProperties(corpus, vo);
        return vo;
    }

}

package com.chenggongdu.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chenggongdu.demo.base.PageQuery;
import com.chenggongdu.demo.mapper.entity.Corpus;
import com.chenggongdu.demo.pojo.bo.CorpusBo;
import com.chenggongdu.demo.pojo.vo.CorpusVo;

/**
 * 语料内容表 Service
 *
 * @author chenggongdu
 */
public interface CorpusService extends IService<Corpus> {

    IPage<CorpusVo> pageByBo(PageQuery pageQuery, CorpusBo corpusBo);

    CorpusVo getVoById(String corpusId);

    void updateByBo(CorpusBo corpusBo);

    void saveByBo(CorpusBo corpusBo);
}


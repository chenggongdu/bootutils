package com.chenggongdu.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenggongdu.demo.base.PageQuery;
import com.chenggongdu.demo.convertors.CorpusConvertor;
import com.chenggongdu.demo.mapper.CorpusMapper;
import com.chenggongdu.demo.mapper.entity.Corpus;
import com.chenggongdu.demo.pojo.bo.CorpusBo;
import com.chenggongdu.demo.pojo.vo.CorpusVo;
import com.chenggongdu.demo.service.CorpusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 语料内容表 Service实现类
 *
 * @author chenggongdu
 */
@Slf4j
@Service
public class CorpusServiceImpl extends ServiceImpl<CorpusMapper, Corpus> implements CorpusService {

    @Override
    public IPage<CorpusVo> pageByBo(PageQuery pageQuery, CorpusBo corpusBo) {
        Corpus corpus = CorpusConvertor.to(corpusBo);
        Page<Corpus> page = this.page(pageQuery.build(), Wrappers.query(corpus));
        return page.convert(CorpusConvertor::toVo);
    }

    @Override
    public CorpusVo getVoById(String corpusId) {
        Corpus corpus = this.getById(corpusId);
        if (corpus == null) {
            return null;
        }
        return CorpusConvertor.toVo(corpus);
    }

    @Override
    public void updateByBo(CorpusBo corpusBo) {
        Corpus corpus = CorpusConvertor.to(corpusBo);
        this.updateById(corpus);
    }

    @Override
    public void saveByBo(CorpusBo corpusBo) {
        Corpus corpus = CorpusConvertor.to(corpusBo);
        this.save(corpus);
        corpusBo.setCorpusId(corpus.getCorpusId());
    }
}


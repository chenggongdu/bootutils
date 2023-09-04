package com.chenggongdu.demo.controller;

import com.chenggongdu.demo.pojo.bo.CorpusBo;
import com.chenggongdu.demo.pojo.vo.CorpusVo;
import com.chenggongdu.demo.service.CorpusService;
import com.chenggongdu.demo.base.PageQuery;
import com.chenggongdu.demo.base.Result;
import com.chenggongdu.demo.base.ResultPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.chenggongdu.validate.EditGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 语料内容表 Controller
 *
 * @author chenggongdu
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/corpus")
@Tag(name = "语料内容表管理模块", description = "语料内容表管理模块")
public class CorpusController {

    @Resource
    private CorpusService corpusService;

    /**
     * 分页查询
     *
     * @param pageQuery  分页对象
     * @param corpusBo 语料内容表
     * @return {@link ResultPage<CorpusVo>
     */
    @Operation(summary = "分页查询", description = "分页查询")
    @PostMapping("/page")
    public ResultPage<CorpusVo> page(PageQuery pageQuery, @Validated CorpusBo corpusBo) {

        return ResultPage.build(corpusService.pageByBo(pageQuery, corpusBo));
    }


    /**
     * 通过语料ID查询语料内容表
     *
     * @param corpusId 语料ID
     * @return {@link CorpusVo}
     */
    @Operation(summary = "详情", description = "通过id查询")
    @GetMapping("/info")
    public Result<CorpusVo> info(@RequestParam("corpusId") @Schema(description = "语料ID") String corpusId) {
        return Result.ok(corpusService.getVoById(corpusId));
    }

    /**
     * 新增语料内容表
     *
     * @param corpusBo 语料内容表
     * @return {@link String} 语料ID
     */
    @Operation(summary = "新增语料内容表信息表", description = "新增语料内容表信息表")
    @PostMapping("/save")
    public Result<String> save(@RequestBody @Validated CorpusBo corpusBo) {
        corpusService.saveByBo(corpusBo);
        return  Result.ok(corpusBo.getCorpusId());
    }


    /**
     * 修改语料内容表
     *
     * @param corpusBo 语料内容表
     * @return {@link String} 语料ID
     */
    @Operation(summary = "修改语料内容表信息表", description = "修改语料内容表信息表")
    @PutMapping("/edit")
    public void edit(@RequestBody @Validated({EditGroup.class}) CorpusBo corpusBo) {
        corpusService.updateByBo(corpusBo);
    }


    /**
     * 通过语料ID数组删除语料内容表
     *
     * @param corpusIds 语料ID数组
     */
    @Operation(summary = "批量删除", description = "通过id删除语料内容表")
    @DeleteMapping("/delete")
    public void delete(@RequestParam("corpusIds") @Schema(description = "语料ID, 多个用英文逗号分割") String corpusIds) {
        List<String> idList = Arrays.asList(corpusIds.split(StringPool.COMMA));
        corpusService.removeByIds(idList);
    }
}


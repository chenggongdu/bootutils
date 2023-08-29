package com.chenggongdu.demo.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collection;
import java.util.List;

/**
 * 表格分页数据对象
 *
 * @author chenggongdu
 */
@Data
@NoArgsConstructor
@Schema(name = "ResultPage", description = "分页响应对象")
public class ResultPage<T> {

    /**
     * 总记录数
     */
    @Schema(name = "total", description = "总记录数")
    private long total;

    /**
     * 列表数据
     */
    @Schema(name = "rows", description = "列表数据")
    private Collection<T> rows;

    /**
     * 消息状态码
     */
    @Schema(name = "code", description = "消息状态码")
    private int code;

    /**
     * 消息内容
     */
    @Schema(name = "msg", description = "消息内容")
    private String msg;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public ResultPage(List<T> list, long total) {
        this.rows = list;
        this.total = total;
    }

    public static <T> ResultPage<T> build(IPage<T> page) {
        ResultPage<T> rspData = new ResultPage<>();
        rspData.setCode(HttpStatus.OK.value());
        rspData.setMsg("查询成功");
        rspData.setRows(page.getRecords());
        rspData.setTotal(page.getTotal());
        return rspData;
    }

    public static <T> ResultPage<T> build(List<T> list) {
        ResultPage<T> rspData = new ResultPage<>();
        rspData.setCode(HttpStatus.OK.value());
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(list.size());
        return rspData;
    }

    public static <T> ResultPage<T> build() {
        ResultPage<T> rspData = new ResultPage<>();
        rspData.setCode(HttpStatus.OK.value());
        rspData.setMsg("查询成功");
        return rspData;
    }

}

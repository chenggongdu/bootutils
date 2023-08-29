package com.chenggongdu.demo.base;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenggongdu.utils.StringUtil;
import com.chenggongdu.utils.sql.SqlUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;


/**
 * 分页查询实体类
 *
 * @author chenggongdu
 */
@Data
public class PageQuery {


    /**
     * 分页大小
     */
    @Min(1)
    @Schema(name = "pageSize", description = "分页大小")
    private Integer pageSize;

    /**
     * 当前页数
     */
    @Min(1)
    @Schema(name = "pageIndex", description = "当前页数")
    private Integer pageIndex;

    /**
     * 排序列
     */
    @Schema(name = "orderByColumn", description = "排序列")
    private String orderByColumn;

    /**
     * 排序的方向desc或者asc
     */
    @Schema(name = "isAsc", description = "排序的方向 asc,desc")
    private String isAsc;

    /**
     * 当前记录起始索引 默认值
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 每页显示记录数 默认值 默认查全部
     */
    public static final int DEFAULT_PAGE_SIZE = Integer.MAX_VALUE;

    public <T> Page<T> build() {
        Integer pageIndex = ObjectUtil.defaultIfNull(getPageIndex(), DEFAULT_PAGE_NUM);
        Integer pageSize = ObjectUtil.defaultIfNull(getPageSize(), DEFAULT_PAGE_SIZE);
        if (pageIndex <= 0) {
            pageIndex = DEFAULT_PAGE_NUM;
        }
        Page<T> page = new Page<>(pageIndex, pageSize);
        OrderItem orderItem = buildOrderItem();
        if (ObjectUtil.isNotNull(orderItem)) {
            page.addOrder(orderItem);
        }
        return page;
    }

    private OrderItem buildOrderItem() {
        if (StringUtil.isNotBlank(orderByColumn)) {
            String orderBy = SqlUtil.escapeOrderBySql(orderByColumn);
            orderBy = StringUtil.toUnderScoreCase(orderBy);
            if ("asc".equals(isAsc)) {
                return OrderItem.asc(orderBy);
            } else if ("desc".equals(isAsc)) {
                return OrderItem.desc(orderBy);
            }
        }
        return null;
    }

}

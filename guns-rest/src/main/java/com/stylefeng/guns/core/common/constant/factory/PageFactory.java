package com.stylefeng.guns.core.common.constant.factory;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.common.constant.state.Order;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.ToolUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage(String sortField, String order) {
        HttpServletRequest request = HttpKit.getRequest();
//        System.out.println(request.getParameter("limit"));
        
        int limit = request.getParameter("pageSize") == null ? 20 : ToolUtil.toInt(request.getParameter("pageSize"));     //每页多少条数据
        int pageIndex = request.getParameter("pageIndex") == null ? 0 : ToolUtil.toInt(request.getParameter("pageIndex"));   //每页的偏移量(本页当前有多少条)
        String sort = sortField;         //排序字段名称
        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>((pageIndex + 1), limit);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>((pageIndex + 1), limit, sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
    
    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
//        System.out.println(request.getParameter("limit"));
        
        int limit = request.getParameter("pageSize") == null ? 20 : ToolUtil.toInt(request.getParameter("pageSize"));     //每页多少条数据
        int pageIndex = request.getParameter("pageIndex") == null ? 0 : ToolUtil.toInt(request.getParameter("pageIndex"));   //每页的偏移量(本页当前有多少条)
        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");         //排序字段名称
        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>((pageIndex + 1), limit);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>((pageIndex + 1), limit, sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
}

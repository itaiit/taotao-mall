package io.itaiit.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 保存商品列表页面的返回结果值（对应于easyui的datagrid）
 */
public class EasyUIDataGridResult implements Serializable {
    private static final long serialVersionUID = 7703664272142191230L;

    private Long total;
    private List<?> rows;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}

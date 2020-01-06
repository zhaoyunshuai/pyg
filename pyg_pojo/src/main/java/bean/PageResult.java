package bean;

import java.io.Serializable;
import java.util.List;

/**
 * 分页专用pojo
 */
public class PageResult implements Serializable {
    //总条数
    private Long total;
    //每页显示的数据
    private List rows;

    public PageResult(Long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}

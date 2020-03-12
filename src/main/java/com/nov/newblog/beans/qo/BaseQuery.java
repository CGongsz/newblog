package com.nov.newblog.beans.qo;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

/**
 * @Author: Nov
 * @CreateDate: 2020-03-12 14:34
 * @Version: 1.0
 */
public class BaseQuery {
    @NotEmpty
    public Long pageNum;
    @NotEmpty
    public Long pageSize;

    public Map<String, Map<String, Object>> queryMap;

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Map<String, Object>> getQueryMap() {
        return queryMap;
    }

    public void setQueryMap(Map<String, Map<String, Object>> queryMap) {
        this.queryMap = queryMap;
    }
}

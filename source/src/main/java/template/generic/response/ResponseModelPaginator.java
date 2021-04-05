package template.generic.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseModelPaginator<T> {
    private Integer pageCount;
    private Integer pageIndex;
    private Integer pageSize;
    private List<T> rows;
    private String responseCode;
    private String responseMessage;

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<T> getRows() {
        if (rows == null) {
            rows = new ArrayList<>();
        }

        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}

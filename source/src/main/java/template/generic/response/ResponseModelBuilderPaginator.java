package template.generic.response;

import java.util.ArrayList;
import java.util.List;

public class ResponseModelBuilderPaginator<T> {
    private Integer pageCount;
    private Integer pageIndex;
    private Integer pageSize;
    private List<T> rows = new ArrayList<>();
    private String responseCode ;
    private String responseMessage ;


    public ResponseModelBuilderPaginator<T> setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public ResponseModelBuilderPaginator<T> setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    public ResponseModelBuilderPaginator<T> setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ResponseModelBuilderPaginator<T> addRow(T row) {
        this.rows.add(row);
        return this;
    }

    public ResponseModelBuilderPaginator<T> setResponseCode(String responseCode) {
        this.responseCode = responseCode;
        return this;
    }

    public ResponseModelBuilderPaginator<T> setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
        return this;
    }

    public ResponseModelPaginator<T> buildPaginator() {
        ResponseModelPaginator<T> newInstance = new ResponseModelPaginator<T>();
        newInstance.setPageCount(pageCount);
        newInstance.setPageIndex(pageIndex);
        newInstance.setPageSize(pageSize);
        newInstance.setRows(rows);
        newInstance.setResponseCode(responseCode);
        newInstance.setResponseMessage(responseMessage);
        return newInstance;
    }
}

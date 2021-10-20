package service.rest.dto.page;

import lombok.Data;

import java.util.List;

@Data
public class ResponsePaginator<D> extends FilterDto {
    private Integer totalPage;
    private Long totalRecords;
    private List<D> content;
}

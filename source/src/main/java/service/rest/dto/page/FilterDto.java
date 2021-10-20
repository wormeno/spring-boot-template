package service.rest.dto.page;

import lombok.Data;

@Data
public class FilterDto {

    Integer pageNumber=0;

    Integer pageSize=20;
}

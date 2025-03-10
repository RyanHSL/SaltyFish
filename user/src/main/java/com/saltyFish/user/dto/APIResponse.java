package com.saltyFish.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Data
public class APIResponse<T> {

    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response"
    )
    private String statusMsg;

    private T content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElements;
    private Integer totalPages;
    private boolean lastPage;

    public APIResponse(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public APIResponse(String statusCode, String statusMsg, T content) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.content = content;
    }

    public APIResponse(String statusCode, String statusMsg, T content, Integer pageNumber, Integer pageSize, Integer totalElements, Integer totalPages, boolean lastPage) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.lastPage = lastPage;
    }
}

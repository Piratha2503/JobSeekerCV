package com.cvmaker.Jobscorecv.Common.APIResponse;

import lombok.*;

@Getter
@Setter
public class PaginatedResponse<T> extends APIContentResponse<T> {

    public PaginatedResponse(String validation_Code, String validation_status, String validation_message, String title, T content) {
        super(validation_Code, validation_status, validation_message, title, content);
    }

    private Pagination pagination;

    public PaginatedResponse(String validation_Code, String validation_status, String validation_message, String title, T content, Pagination pagination){
        super(validation_Code, validation_status, validation_message, title, content);
        this.pagination = pagination;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Pagination{
        private Integer pageNumber;
        private Integer pageSize;
        private Integer totalPages;
        private Long totalRecords;
        private String sortedBy;
        private String orderedBy;
    }

}

package dev.festus.blog.blogpost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public  class BaseResponse<T> {
    private String status;
    private String description;
    private T data;
}

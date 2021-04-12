package com.flower.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListResponse<T> {
    private List<T> data;
    private long count;
    private Integer code=0;

    public ListResponse(List<T> data, long count) {
        this.data = data;
        this.count = count;
    }
}
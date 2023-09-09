package com.hxd.fsystemback.entity;

import lombok.Data;

@Data
public class Params {
    private String keyword;
    private Integer pageNum;
    private Integer pageSize;
    private Integer id;
    private Integer countNum;

}

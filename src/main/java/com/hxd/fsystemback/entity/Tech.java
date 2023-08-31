package com.hxd.fsystemback.entity;

import lombok.Data;

@Data
public class Tech {


    private String productName;
    private String productStandard;
    private String productNote;
    private String partsStandard;
    private String partsName;
    private Integer num;
    private String partsGroup;
    private int preWarn;


    private Integer id;
    private Integer partsId;
    private Integer productId;

}

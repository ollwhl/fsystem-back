package com.hxd.fsystemback.entity;

import lombok.Data;

@Data
public class Tech {

    private String productName;
    private String productStandard;
    private String partsStandard;
    private String partsName;
    private Integer num;
    private String partsGroup;


    private Integer id;
    private Integer partsId;
    private Integer productId;

}

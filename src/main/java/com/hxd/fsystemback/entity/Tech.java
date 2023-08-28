package com.hxd.fsystemback.entity;

import lombok.Data;

@Data
public class Tech {
    private Integer id;
    private Integer productId;
    private String productName;
    private String productStandard;
    private Integer partsId;
    private String partsName;
    private String partsStandard;
    private Integer num;

}

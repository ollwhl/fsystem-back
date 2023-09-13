package com.hxd.fsystemback.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Tech {


    private String productName;
    private String productStandard;
    private String productNote;
    private String partsName;
    private String partsStandard;
    private Integer num;
    private String partsGroup;
    private int preWarn;
    private int planNum;
    private int produced;
    private Date planDate;


    private Integer id;
    private Integer partsId;
    private Integer productId;

}

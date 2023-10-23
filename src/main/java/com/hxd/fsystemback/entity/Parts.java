package com.hxd.fsystemback.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Parts {
    private Integer id;
    private String name;
    private int num;
    private String standard;
    private String note;
    private Integer min;
    private Integer confirm;
    private String group;
    private int preWarn;
    private int lost;
    private Date partsDate;
    private Date halfDate;

}

package com.hxd.fsystemback.entity;

import lombok.Data;

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
}

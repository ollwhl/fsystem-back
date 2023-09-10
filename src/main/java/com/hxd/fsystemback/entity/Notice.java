package com.hxd.fsystemback.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    String title;
    String msg;
    Date time;
    Integer id;
}

package com.hxd.fsystemback.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Product {
    int id;
    String name;
    int num;
    String standard;
    String note;
    int planNum;
    Date planDate;
    int produced;
    int productConfirm;
}

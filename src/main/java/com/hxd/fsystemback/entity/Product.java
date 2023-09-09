package com.hxd.fsystemback.entity;

import lombok.Data;

@Data
public class Product {
    int id;
    String name;
    int num;
    String standard;
    String note;
    int planNum;
    String planDate;
    int produce;
}

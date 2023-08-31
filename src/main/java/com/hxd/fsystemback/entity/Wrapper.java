package com.hxd.fsystemback.entity;

import lombok.Data;

import java.util.List;

@Data
public class Wrapper {
    private List<Tech> techList;
    private List<String> stringList;
}

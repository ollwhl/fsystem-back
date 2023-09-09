package com.hxd.fsystemback.entity;
import lombok.Data;

import java.util.Date;
@Data
public class Log {

    private int id;
    private String logMsg;
    private Date time;
    private String userName;
    private String ip;
    private String json;
}

package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface LogMapper {

    @Insert("INSERT INTO log (``,`name`, `standard`, `note`) VALUES (#{name},#{standard},#{note})")
    void addLog(String username, String userMsg, String ip, Date time,String json);

    @Select("SELECT * FROM log")
    List<Log> getLog();
}

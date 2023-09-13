package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
@Mapper
public interface LogMapper {

    @Insert("INSERT INTO log (`username`,`logmsg`, `time`, `ip`) VALUES (#{userName},#{logMsg},#{time},#{ip}) ")
    void addLog(String userName, String logMsg, String ip, Date time);

    @Select("SELECT * FROM log")
    List<Log> getLog();

    @Select("SELECT * FROM log WHERE username LIKE CONCAT('%', #{keyword}, '%') OR logmsg LIKE CONCAT('%', #{keyword}, '%')")
    List<Log> searchLog(String keyword);
}

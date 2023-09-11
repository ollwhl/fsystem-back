package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
@Mapper
public interface NoticeMapper {
    @Select("SELECT * FROM notice")
    List<Notice> getNotice();
    @Insert("INSERT INTO notice (`title`,`msg`,`time`) VALUES (#{title},#{msg},#{time})")
    void addNotice(String title,String msg, Date time);

    @Delete("DELETE FROM notice WHERE title = #{title}")
    void delNotice(String title);
}

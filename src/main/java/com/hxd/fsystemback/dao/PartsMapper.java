package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Parts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PartsMapper {
    @Select("SELECT * FROM parts where `group` = '2'")
    public List<Parts> getPart();

    @Select("SELECT * FROM parts WHERE name LIKE #{keyword} AND 'group' = '2'")
    List<Parts>  searchPartsByName(@Param("keyword")String keyword);


}

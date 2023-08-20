package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Parts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PartsMapper {
    @Select("SELECT * FROM parts")
    public List<Parts> getAllParts();

    @Select("SELECT * FROM parts WHERE name LIKE #{keyword}")
    List<Parts> getPartsByName(@Param("name")String name);
}

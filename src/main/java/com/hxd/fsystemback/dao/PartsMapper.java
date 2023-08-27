package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Parts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface PartsMapper {
    @Select("SELECT * FROM parts where `group` = #{group}")
    public List<Parts> getPart(@Param("group") String group);

    @Select("SELECT * FROM parts WHERE name LIKE #{keyword} AND 'group' = #{group}")
    List<Parts>  searchPartsByName(@Param("keyword")String keyword,@Param("group") String group);

    @Select("SELECT * FROM parts WHERE id = #{id}")
    Parts searchPartByID(@Param("id")Integer id);

    @Update("UPDATE parts SET num = #{num} WHERE (id = #{id})")
    void updatePartNum(Integer id, Integer num);

    @Update("UPDATE parts SET confirm = #{confirmNum} WHERE (id = #{id})")
    void updatePartConfirmNum(Integer id, Integer confirmNum);
}

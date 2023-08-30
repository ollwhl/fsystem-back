package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Product;
import com.hxd.fsystemback.entity.Tech;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PartsMapper {
    @Select("SELECT * FROM parts where `group` = #{group}")
    public List<Parts> getPart(@Param("group") String group);

    @Select("SELECT * FROM parts WHERE name LIKE #{keyword} AND 'group' = #{group}")
    List<Parts>  searchPartsByName(@Param("keyword")String keyword,@Param("group") String group);

    @Select("SELECT * FROM parts WHERE id = #{id}")
    Parts findPartByID(@Param("id")Integer id);
    @Select("SELECT * FROM parts WHERE name = #{name}")
    Parts findPartsByName(String name);
    @Update("UPDATE parts SET num = #{num} WHERE (id = #{id})")
    void updatePartNum(Integer id, Integer num);

    @Update("UPDATE parts SET confirm = #{confirmNum} WHERE (id = #{id})")
    void updatePartConfirmNum(Integer id, Integer confirmNum);

    @Insert("INSERT INTO parts (`name`, `standard`, `group`, `note`) VALUES (#{name},#{standard},#{group},#{note})")
    void addPart(String name, String standard, String group, String note);

    @Select("SELECT * FROM parts")
    List<Parts> getAllPart();

    @Select("SELECT * FROM parts WHERE confirm <> 0")
    List<Parts> getConfirmParts();
    @Update("UPDATE parts SET `min` = #{num} WHERE (`id` = #{id});")
    void setMin(int id, int num);


}

package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Parts;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PartsMapper {
    @Select("SELECT * FROM parts where `group` = #{group}")
    public List<Parts> getPart(@Param("group") String group);
    @Select("SELECT * FROM product WHERE name LIKE CONCAT('%', #{keyword}, '%') AND `group` = #{group}")
    List<Parts>  searchPartsByName(@Param("keyword")String keyword,@Param("group") String group);

    @Select("SELECT * FROM parts WHERE id = #{id}")
    Parts findPartByID(@Param("id")Integer id);
    @Select("SELECT * FROM parts WHERE name = #{name}")
    Parts findPartsByName(String name);
    @Update("UPDATE parts SET confirm = #{confirmNum} WHERE (name = #{name})")
    void editPartConfirmNum(String name, Integer confirmNum);

    @Insert("INSERT INTO parts (`id`,`name`, `standard`, `group`, `note` , `preWarn`) VALUES (#{id},#{name},#{standard},#{group},#{note},#{preWarn})")
    void addPart(int id,String name, String standard, String group, String note,int preWarn);

    @Select("SELECT * FROM parts")
    List<Parts> getAllPart();

    @Select("SELECT * FROM parts WHERE confirm <> 0")
    List<Parts> getConfirmParts();

    @Update("UPDATE parts SET num = #{num} WHERE (id = #{id})")
    void editPartNum(Integer id, Integer num);
    @Update("UPDATE parts SET `min` = #{num} WHERE (`id` = #{id});")
    void editMin(int id, int num);

    @Update("UPDATE parts SET `lost` = #{lost} WHERE (`name` = #{name});")
    void editLost(String name, int lost);

    @Update("UPDATE parts SET `prewarn` = #{preWarn} WHERE (`name` = #{name});")
    void editPreWarn(String name, int preWarn);

    @Select("SELECT * FROM parts WHERE num < min")
    List<Parts> getBuyList();
}

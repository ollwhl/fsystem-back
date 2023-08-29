package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Tech;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TechMapper {

    @Select("SELECT t.*, p.name AS productname, p.standard AS productstandard, pt.name AS partsname, pt.standard AS partsstandard FROM tech AS t LEFT JOIN product p ON t.productid = p.id LEFT JOIN parts pt ON t.partsid = pt.id")
    public List<Tech> getProductTech();
    @Select("SELECT * FROM tech WHERE productid = #{productId} AND partsid = #{partsId}")
    public Tech findTechByProductIdAndPartsId(int productId,int partsId);
    @Insert("INSERT INTO tech (`productId`, `partsId`, `num`) VALUES (#{productId}, #{partsId}, #{num})")
    void addTech(int productId, int partsId, int num);
    @Update("UPDATE tech SET num = #{num} WHERE (id = #{id})")
    void editTechParts(Integer id, Integer num);

    @Delete("DELETE FROM tech WHERE (id = #{id})")
    void delTechParts(Integer id);
}

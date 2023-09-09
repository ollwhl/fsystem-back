package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Tech;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TechMapper {

    @Select("SELECT t.*, p.name AS productname, p.standard AS productstandard,p.note AS productnote, pt.name AS partsname, pt.standard AS partsstandard,pt.`group` As partsGroup FROM tech AS t LEFT JOIN product p ON t.productid = p.id LEFT JOIN parts pt ON t.partsid = pt.id ORDER BY p.name ASC")
    public List<Tech> getProductTech();
    @Select("SELECT * FROM tech WHERE productid = #{productId} AND partsid = #{partsId}")
    public Tech findTechByProductIdAndPartsId(int productId,int partsId);
    @Select("SELECT * FROM tech WHERE productId = #{productId}")
    public List<Tech> findTechByProductId(int productId);
    @Insert("INSERT INTO tech (`productId`, `partsId`, `num`) VALUES (#{productId}, #{partsId}, #{num})")
    void addTech(int productId, int partsId, int num);
    @Update("UPDATE tech SET num = #{num} WHERE (id = #{id})")
    void editTechParts(Integer id, Integer num);

    @Delete("DELETE FROM tech WHERE (id = #{id})")
    void delTechParts(Integer id);

    @Select("SELECT t.*,p.id AS productId,p.name AS productName,p.num AS productNum,p.standard AS productStandard,pt.id AS partsId,pt.name AS partsName,pt.num AS partsNum,pt.standard AS partStandard,pt.`group` As partsGroup FROM tech t LEFT JOIN product p ON t.productid = p.id LEFT JOIN parts pt ON t.partsid = pt.id WHERE p.name LIKE #{keyword};")
    List<Tech> searchTechByProductName(String keyword);

    @Select("SELECT t.*,p.id AS productId,p.name AS productName,p.num AS productNum,p.standard AS productStandard,pt.id AS partsId,pt.name AS partsName,pt.prewarn,pt.num AS partsNum,pt.standard AS partStandard,pt.`group` As partsGroup FROM tech t LEFT JOIN product p ON t.productid = p.id LEFT JOIN parts pt ON t.partsid = pt.id WHERE p.name = #{productName};")
    List<Tech> findTechByProductName(String productName);

    @Select("SELECT t.*,p.id AS productId,p.name AS productName,p.num AS productNum,p.standard AS productStandard,pt.id AS partsId,pt.name AS partsName,pt.prewarn,pt.num AS partsNum,pt.standard AS partStandard,pt.`group` As partsGroup FROM tech t LEFT JOIN product p ON t.productid = p.id LEFT JOIN parts pt ON t.partsid = pt.id WHERE t.id = #{id};")
    Tech findTechByID(int id);
}

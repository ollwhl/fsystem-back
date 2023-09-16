package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Tech;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TechMapper {

    @Select("SELECT t.*,\n" +
            "    p.plandate,\n" +
            "    p.plannum,\n" +
            "    p.produced,\n" +
            "    p.name AS productname,\n" +
            "    p.standard AS productstandard,\n" +
            "    p.num AS productnum,\n" +
            "    p.productconfirm,\n" +
            "    p.note AS productnote,\n" +
            "    pt.prewarn,\n" +
            "    pt.name AS partsname,\n" +
            "    pt.standard AS partsstandard,\n" +
            "    pt.`group` As partsGroup,\n" +
            "    pt.num AS partsnum,\n" +
            "    pt.confirm AS partconfirm,\n" +
            "    pt.lost,\n" +
            "    pt.min\n" +
            "FROM tech AS t \n" +
            "LEFT JOIN product p ON t.productid = p.id \n" +
            "LEFT JOIN parts pt ON t.partsid = pt.id \n" +
            "ORDER BY p.name ASC")
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

    @Select("SELECT t.*,\n" +
            "    p.plandate,\n" +
            "    p.plannum,\n" +
            "    p.produced,\n" +
            "    p.name AS productname,\n" +
            "    p.standard AS productstandard,\n" +
            "    p.num AS productnum,\n" +
            "    p.productconfirm,\n" +
            "    p.note AS productnote,\n" +
            "    pt.prewarn,\n" +
            "    pt.name AS partsname,\n" +
            "    pt.standard AS partsstandard,\n" +
            "    pt.`group` As partsGroup,\n" +
            "    pt.num AS partsnum,\n" +
            "    pt.confirm AS partconfirm,\n" +
            "    pt.lost,\n" +
            "    pt.min \n" +
            "FROM tech AS t \n" +
            "LEFT JOIN product p ON t.productid = p.id \n" +
            "LEFT JOIN parts pt ON t.partsid = pt.id \n" +
            "WHERE pt.name LIKE CONCAT('%', #{keyword}, '%') \n"+
            "OR p.name LIKE CONCAT('%', #{keyword}, '%') \n" +
            "ORDER BY p.name ASC")
    List<Tech> searchTechByProductName(String keyword);

    @Select("SELECT t.*,\n" +
            "    p.plandate,\n" +
            "    p.plannum,\n" +
            "    p.produced,\n" +
            "    p.name AS productname,\n" +
            "    p.standard AS productstandard,\n" +
            "    p.num AS productnum,\n" +
            "    p.productconfirm,\n" +
            "    p.note AS productnote,\n" +
            "    pt.prewarn,\n" +
            "    pt.name AS partsname,\n" +
            "    pt.standard AS partsstandard,\n" +
            "    pt.`group` As partsGroup,\n" +
            "    pt.num AS partsnum,\n" +
            "    pt.confirm AS partconfirm,\n" +
            "    pt.lost,\n" +
            "    pt.min\n" +
            "FROM tech AS t \n" +
            "LEFT JOIN product p ON t.productid = p.id \n" +
            "LEFT JOIN parts pt ON t.partsid = pt.id \n" +
            "WHERE t.id <> 0 AND p.name =#{productname} \n")
    List<Tech> findTechByProductName(String productName);

    @Select("SELECT t.*,\n" +
            "    p.plandate,\n" +
            "    p.plannum,\n" +
            "    p.produced,\n" +
            "    p.name AS productname,\n" +
            "    p.standard AS productstandard,\n" +
            "    p.num AS productnum,\n" +
            "    p.productconfirm,\n" +
            "    p.note AS productnote,\n" +
            "    pt.prewarn,\n" +
            "    pt.name AS partsname,\n" +
            "    pt.standard AS partsstandard,\n" +
            "    pt.`group` As partsGroup,\n" +
            "    pt.num AS partsnum,\n" +
            "    pt.confirm AS partconfirm,\n" +
            "    pt.lost,\n" +
            "    pt.min\n" +
            "FROM tech AS t \n" +
            "LEFT JOIN product p ON t.productid = p.id \n" +
            "LEFT JOIN parts pt ON t.partsid = pt.id \n" +
            "WHERE t.id =  #{id}\n")
    Tech findTechByID(int id);

    @Select("SELECT t.*,\n" +
            "    p.plandate,\n" +
            "    p.plannum,\n" +
            "    p.produced,\n" +
            "    p.name AS productname,\n" +
            "    p.standard AS productstandard,\n" +
            "    p.num AS productnum,\n" +
            "    p.productconfirm,\n" +
            "    p.note AS productnote,\n" +
            "    pt.prewarn,\n" +
            "    pt.name AS partsname,\n" +
            "    pt.standard AS partsstandard,\n" +
            "    pt.`group` As partsGroup,\n" +
            "    pt.num AS partsnum,\n" +
            "    pt.confirm AS partconfirm,\n" +
            "    pt.lost,\n" +
            "    pt.min\n" +
            "FROM tech AS t \n" +
            "LEFT JOIN product p ON t.productid = p.id \n" +
            "LEFT JOIN parts pt ON t.partsid = pt.id \n" +
            "WHERE p.plannum <> 0 \n" +
            "ORDER BY p.name ASC")
    List<Tech> getTechWithPlan();

}

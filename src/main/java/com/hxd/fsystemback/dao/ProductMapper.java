package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product WHERE name = #{name}")
    Product findProductByName(String name);
    @Insert("INSERT INTO product (`id`,`name`, `standard`, `note`) VALUES (#{id},#{name},#{standard},#{note})")
    void addProduct(int id,String name, String standard, String note);

    @Select("SELECT * FROM product WHERE planNum <> 0")
    List<Product> getPlane();
    @Update("UPDATE product SET plannum = #{planeNum}, plandate = #{planeDate} WHERE (`name` = #{productName});")
    void editPlane(String productName, int planeNum, String planeDate);
    @Update("UPDATE product SET `produce` = #{produce} WHERE (`id` = #{id});")
    void dailyCheck(int id, int produce);


}

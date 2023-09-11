package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product")
    List<Product> getProduct();
    @Select("SELECT * FROM product WHERE name = #{name}")
    Product findProductByName(String name);

    @Select("SELECT * FROM product WHERE planNum <> 0")
    List<Product> getPlane();

    @Select("SELECT * FROM parts WHERE name LIKE CONCAT('%', #{keyword}, '%') AND 'group' = #{group}")
    List<Parts>  searchPartsByName(@Param("keyword")String keyword, @Param("group") String group);

    @Update("UPDATE product SET plannum = #{planeNum}, plandate = #{planeDate} WHERE (`name` = #{productName});")
    void editPlane(String productName, int planeNum, Date planeDate);

    @Update("UPDATE product SET `produce` = #{produce} WHERE (`name` = #{name});")
    void dailyCheck(String name, int produce);

    @Insert("INSERT INTO product (`id`,`name`, `standard`, `note`) VALUES (#{id},#{name},#{standard},#{note})")
    void addProduct(int id,String name, String standard, String note);


}

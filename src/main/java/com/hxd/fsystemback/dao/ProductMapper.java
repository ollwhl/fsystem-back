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

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findProductById(Integer id);
    @Select("SELECT * FROM product WHERE planNum <> 0")
    List<Product> getPlane();

    @Select("SELECT * FROM parts WHERE name LIKE CONCAT('%', #{keyword}, '%') AND 'group' = #{group}")
    List<Parts>  searchPartsByName(@Param("keyword")String keyword, @Param("group") String group);

    @Update("UPDATE product " +
            "SET plannum = #{planeNum}, " +
            "planeDate = #{planeDate}, " +
            "partsDate = #{partsDate}, " +
            "halfDate = #{halfDate}, " +
            "producerDate = #{producerDate}, " +
            "exportDate = #{exportDate}" +
            "WHERE (`name` = #{productName});")
    void editPlane(String productName, int planeNum, Date planeDate ,Date partsDate,Date halfDate,Date producerDate,Date exportDate);

    @Update("UPDATE product SET `produced` = #{produced} WHERE (`name` = #{name});")
    void editProduced(String name, int produced);

    @Insert("INSERT INTO product (`id`,`name`, `standard`, `note`) VALUES (#{id},#{name},#{standard},#{note})")
    void addProduct(int id,String name, String standard, String note);

    @Select("SELECT * FROM product WHERE name LIKE CONCAT('%', #{keyword}, '%')")
    List<Product> searchProductByName(String keyword);

    @Update("UPDATE product SET `productconfirm` = #{productConfirm} WHERE (`name` = #{name});")
    void editProductConfirmNum(String name, int productConfirm);

    @Update("UPDATE product SET `num` = #{num} WHERE (`name` = #{name});")
    void editProductNum(String name, int num);


}

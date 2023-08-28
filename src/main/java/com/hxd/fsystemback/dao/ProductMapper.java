package com.hxd.fsystemback.dao;

import com.hxd.fsystemback.entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product WHERE name = #{name}")
    Product findProductByName(String name);
    @Insert("INSERT INTO product (`name`, `standard`, `note`) VALUES (#{name},#{standard},#{note})")
    void addProduct(String name, String standard, String note);
}

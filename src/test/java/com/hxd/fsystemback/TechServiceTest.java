package com.hxd.fsystemback;

import com.github.pagehelper.PageInfo;
import com.hxd.fsystemback.entity.Params;
import com.hxd.fsystemback.entity.Parts;
import com.hxd.fsystemback.entity.Tech;
import com.hxd.fsystemback.exception.CustomException;
import com.hxd.fsystemback.service.PartsService;
import com.hxd.fsystemback.service.TechService;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TechServiceTest {


    @Autowired
    private TechService techService;
    @Autowired
    private PartsService partsService;


    @Test
    public void testGetProductTech() throws IllegalAccessException, CustomException {
        //getTechTest();
        //addPartsTest();
        addTechTest();
        //System.out.println("0信1息2信息3");

    }
    private void getTechTest() throws IllegalAccessException {
        Params params = new Params();
        params.setPageNum(1);
        params.setPageSize(10);
        PageInfo<Tech> pageInfo = techService.getProductTech(params);
        List<Tech> techList = pageInfo.getList();
        ListPrintUtil.printList(techList);
    }
    private void addTechTest() throws CustomException {
        List<Tech> techList = new ArrayList<>();
        Tech tech1 = new Tech();
        tech1.setProductName("test-name1");
        tech1.setPartsName("test-name1");
        tech1.setNum(10);
        Tech tech2 = new Tech();
        tech2.setProductName("test-name1");
        tech2.setPartsName("test-name2");
        tech2.setNum(20);
        techList.add(tech1);
        techList.add(tech2);
        try {
            techService.addTech(techList);
            System.out.println("success!!!!");
        }catch (CustomException e){
            System.out.println(e.getMsg());
        }


    }
}
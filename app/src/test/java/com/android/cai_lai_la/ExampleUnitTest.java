package com.android.cai_lai_la;

import com.alibaba.fastjson.JSON;
import com.android.cai_lai_la.controller.ProductClassController;
import com.android.cai_lai_la.controller.ProductPicController;
import com.android.cai_lai_la.model.ProductClass;
import com.android.cai_lai_la.model.ProductPic;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test1() {
        System.out.println("获取商品分类");
        List<ProductClass> list = ProductClassController.list();
        for (ProductClass p :
                list) {
            System.out.println(p);
        }
    }

    @Test
    public void testProductPicList() {
        System.out.println("获取商品图片");
        System.out.println("获取所有图片");
        List<ProductPic> list = ProductPicController.list();
        System.out.println(JSON.toJSON(list).toString());
        System.out.println("获取pid=1的商品的图片");
        List<ProductPic> list1 = ProductPicController.list(3);
        System.out.println(list1);
        System.out.println(JSON.toJSON(list1).toString());
    }
}
package com;

import com.coree.ExcelUtil;
import org.junit.Before;


import java.io.IOException;

/**
 * Created by loovee1 on 2016/5/25.
 */
public class Test {



    @org.junit.Test
    public void testRead() {
        try {

            ExcelUtil eu = new ExcelUtil();
            eu.setExcelPath("d:\\2.xls");

            System.out.println("=======测试Excel 默认 读取========");
            eu.readExcel();

            System.out.println("\n=======测试Excel 从第四行读取，倒数第二行结束========");
            eu = eu.RestoreSettings();//还原设定
            eu.setStartReadPos(3);
            eu.setEndReadPos(-1);
            eu.readExcel();

            System.out.println("\n=======测试Excel 读取第二个sheet========");
            eu = eu.RestoreSettings();//还原设定
            eu.setSelectedSheetIdx(1);
            eu.readExcel();

            System.out.println("\n=======测试Excel 读取所有的sheet========");
            eu = eu.RestoreSettings();//还原设定
            eu.setOnlyReadOneSheet(false);
            eu.readExcel();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

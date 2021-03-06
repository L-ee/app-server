package com.core;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by loovee1 on 2016/5/25.
 */
public class ExportSetInfo {

    private LinkedHashMap<String, List> objsMap;

    private String[] titles;

    private List<String[]> headNames;

    private List<String[]> fieldNames;

    private OutputStream out;

    @SuppressWarnings("unchecked")
    public LinkedHashMap<String, List> getObjsMap() {
        return objsMap;
    }

    /**
     * @param objsMap 导出数据
     * String : 代表sheet名称
     * List : 代表单个sheet里的所有行数据
     */
    public void setObjsMap(LinkedHashMap<String, List> objsMap) {
        this.objsMap = objsMap;
    }

    public List<String[]> getFieldNames() {
        return fieldNames;
    }

    /**
     * @param fieldNames 对应每个sheet里的每行数据的对象的属性名称
     */
    public void setFieldNames(List<String[]> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String[] getTitles() {
        return titles;
    }

    /**
     * @param titles 对应每个sheet里的标题，即顶部大字
     */
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public List<String[]> getHeadNames() {
        return headNames;
    }

    /**
     * @param headNames 对应每个页签的表头的每一列的名称
     */
    public void setHeadNames(List<String[]> headNames) {
        this.headNames = headNames;
    }

    public OutputStream getOut() {
        return out;
    }

    /**
     * @param out Excel数据将输出到该输出流
     */
    public void setOut(OutputStream out) {
        this.out = out;
    }
}

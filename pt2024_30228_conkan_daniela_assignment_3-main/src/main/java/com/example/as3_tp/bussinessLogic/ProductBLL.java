package com.example.as3_tp.bussinessLogic;// ProductBLL.java

import com.example.as3_tp.Model.Product;
import com.example.as3_tp.dataAcess.AbstractDAO;

/**
 * this class uses AbstractDAO to implement find,insert,update and delete operations on products table using Product class
 * overrides unimplemented methods from AbstractDAO
 */
public class ProductBLL extends AbstractDAO<Product> {

    @Override
    protected String getTableName() {
        return "products";
    }

    @Override
    protected String getId() {
        return "id";
    }

    @Override
    protected Object[] getFields(Product entity) {
        Object[] values = new Object[4];
        values[0] = entity.getId();
        values[1] = entity.getName();
        values[2] = entity.getPrice();
        values[3] = entity.getStock();
        return values;
    }

}

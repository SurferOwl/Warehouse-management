package com.example.as3_tp.bussinessLogic;// ClientBLL.java

import com.example.as3_tp.Model.Client;
import com.example.as3_tp.dataAcess.AbstractDAO;

/**
 * this class uses AbstractDAO to implement find,insert,update and delete operations on clients table using Client class
 * overrides unimplemented methods from AbstractDAO
 */
public class ClientBLL extends AbstractDAO<Client> {

    @Override
    protected String getTableName() {
        return "clients";
    }

    @Override
    protected String getId() {
        return "id";
    }

    @Override
    protected Object[] getFields(Client entity) {
        Object[] values = new Object[5];
        values[0] = entity.getId();
        values[1] = entity.getName();
        values[2] = entity.getMail();
        values[3] = entity.getAddress();
        values[4] = entity.getAge();
        return values;
    }

}

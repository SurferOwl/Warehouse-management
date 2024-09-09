package com.example.as3_tp.bussinessLogic;

import com.example.as3_tp.Model.Recipe;
import com.example.as3_tp.connection.ConnectionFactory;
import com.example.as3_tp.dataAcess.AbstractDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * this class uses AbstractDAO to implement find,insert,update and delete operations on orders table using Recipe class
 * overrides unimplemented methods from AbstractDAO
 * it adds an additional method, for inserting bills into Log table
 */

public class OrderBLL extends AbstractDAO<Recipe> {
    @Override
    protected String getTableName() {
        return "orders";
    }

    @Override
    protected String getId() {
        return "id";
    }

    @Override
    protected Object[] getFields(Recipe entity) {
        Object[] values = new Object[4];
        values[0] = entity.getIdComanda();
        values[1] = entity.getCantita();
        values[2] = entity.getPret();
        values[3] = entity.getAdresa();
        return values;
    }

    /**
     * query for inserting bills into Log table
     * @throws SQLException exceptie SQL
     */
    public void insertBillRec() throws SQLException {

        try (Connection conn = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Log ( idComanda, cantita, pret, adresa) SELECT * FROM orders";


            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.executeUpdate();
            }
        }
    }


}

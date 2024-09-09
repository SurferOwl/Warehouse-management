package com.example.as3_tp.dataAcess;
import com.example.as3_tp.connection.ConnectionFactory;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * this abstract class uses reflection to access the database and do operations on it.
 * the operations can be done on every table from database because of the reflection
 * the business logic classes will inherit this class and will be more specific about the tables and classes used
 */
public abstract class AbstractDAO<T> {

    /**
     * will be overridden in the bll
     * @return the table's name, where the operation will be done
     */
    protected abstract String getTableName();

    /**
     * will be overridden in the bll
     * @return the id header's name
     */

    protected abstract String getId();

    /**
     * will be overridden in the bll
     * @param entity which can represent any class from Model (except IdUtil)
     * @return object's fields (values)
     */
    protected abstract Object[] getFields(T entity);

    /**
     * establishes a connection with the database, then creates a statement using the query string, then it sets the unknown parameters marked as "?"
     * @param id id-ul pe care se executa satementul
     * @return query's result described in its name
     */
    public ResultSet findById(int id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE " + getId() + " = ?";
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            return statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * establishes a connection with the database, then creates a statement using the query string, then it sets the unknown parameters marked as "?"
     * @return query's result described in its name
     */

    public ResultSet findAll() {
        String query = "SELECT * FROM " + getTableName();
        ResultSet resultSet = null;
        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return resultSet;
    }

    /**
     * establishes a connection with the database, then creates a statement using the query string, then it sets the unknown parameters marked as "?"
     * @param id id-ul pe care se executa satementul
     */
    public void delete(int id) {
        String query = "DELETE FROM " + getTableName() + " WHERE " + getId() + " = ?";

        try {
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    /**
     * establishes a connection with the database, then creates a statement using the query string, then it sets the unknown parameters marked as "?"
     * accesses the entity's field values (the entity is given by the user) and uses them in the query
     * @param entity which can be any class from Model
     */
    public void insert(T entity) {
        String query = "INSERT INTO " + getTableName() + " VALUES (";
        StringBuilder makeQuery = new StringBuilder(query);

        Field[] fields = entity.getClass().getDeclaredFields();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();

            for (Field field : fields) {
                field.setAccessible(true);
                makeQuery.append("?, ");
            }
            makeQuery.setLength(makeQuery.length() - 2);
            makeQuery.append(")");

            statement = connection.prepareStatement(makeQuery.toString());

            Object[] values = getFields(entity);
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Log the SQL exception
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * establishes a connection with the database, then creates a statement using the query string, then it sets the unknown parameters marked as "?"
     * accesses the entity's field values (the entity is given by the user) and uses them in the query
     * @param entity which can be any class from Model
     */
    public void update(T entity) {

        String query = "UPDATE " + getTableName() + " SET ";
        StringBuilder makeQuery = new StringBuilder(query);
        String idFieldName = getId();

        Field[] fields = entity.getClass().getDeclaredFields();
        try {

            Connection connection = ConnectionFactory.getConnection();

            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getName().equals(idFieldName)) {
                    makeQuery.append(field.getName()).append(" = ?, ");
                }
            }
            makeQuery.setLength(makeQuery.length() - 2);
            makeQuery.append(" WHERE ").append(idFieldName).append(" = ?");


            PreparedStatement statement = connection.prepareStatement(makeQuery.toString());

            int parameterIndex = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getName().equals(idFieldName)) {
                    Object value = field.get(entity);
                    statement.setObject(parameterIndex++, value);
                }
            }

            Field idField = entity.getClass().getDeclaredField(idFieldName);
            idField.setAccessible(true);
            Object idValue = idField.get(entity);
            statement.setObject(parameterIndex, idValue);
            statement.executeUpdate();

        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }


}

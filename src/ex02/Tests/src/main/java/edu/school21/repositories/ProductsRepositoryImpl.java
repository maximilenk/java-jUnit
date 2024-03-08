package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryImpl implements  ProductsRepository {
    DataSource dataSource;
    public ProductsRepositoryImpl(DataSource ds) {
        this.dataSource = ds;
    }
    @Override
    public Optional<Product> findById(Long id) {
        String query = "SELECT * FROM tests.product WHERE id = " + id;
        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(new Product(id,resultSet.getString(2), resultSet.getInt(3)));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
    @Override
    public List<Product> findAll() {
        List<Product> result = new ArrayList<>();
        String query = "SELECT * FROM tests.product";
        try(Connection con = dataSource.getConnection();
            Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.add(new Product(resultSet.getLong(1),resultSet.getString(2), resultSet.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void update(Product product) {
        String query = "UPDATE tests.product SET name =  '" + product.getName()
                + "', price = " + product.getPrice() + " WHERE id = "
                + product.getId();
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        String query = "INSERT INTO tests.product (id, name, price) VALUES ("
                + product.getId() + ", '" + product.getName()
                + "', " + product.getPrice() + ")";
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void delete(Long id) {
        String query = "DELETE FROM tests.product WHERE id = " + id;
        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

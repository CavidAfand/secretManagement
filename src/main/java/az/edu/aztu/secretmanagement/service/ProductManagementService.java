package az.edu.aztu.secretmanagement.service;

import az.edu.aztu.secretmanagement.DatabaseConnection;
import az.edu.aztu.secretmanagement.model.Product;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductManagementService {

    private DatabaseConnection databaseConnection;

    public ProductManagementService(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from product");
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("productId"));
                product.setName(resultSet.getString("productName"));
                product.setPrice(resultSet.getBigDecimal("productPrice"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product getProduct(int productId) {
        Connection connection = getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from product where productId = ?");
            stmt.setInt(1, productId);
            ResultSet resultSet = stmt.executeQuery();

            Product product = null;
            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("productId"));
                product.setName(resultSet.getString("productName"));
                product.setPrice(resultSet.getBigDecimal("productPrice"));;
            }
            return product;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    databaseConnection.getUrl(),
                    databaseConnection.getUsername(), databaseConnection.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void saveProductToCustomer(int productId, int customerId, int count) {
    }
}

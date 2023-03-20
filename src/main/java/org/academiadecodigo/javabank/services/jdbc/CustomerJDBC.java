package org.academiadecodigo.javabank.services.jdbc;

import org.academiadecodigo.JDBC.ConnectionMaster;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerJDBC implements CustomerService {

    private ConnectionMaster connectionMaster;

    public Customer buildCustomer(String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setName(firstName + " " + lastName);
        return customer;
    }

    @Override
    public Customer get(Integer id) {
        Customer rightCustomer = null;
        try {

            Connection dbConnection = connectionMaster.getConnection();
            Statement statement = dbConnection.createStatement();

            String query = "SELECT first_name, last_name FROM customer WHERE  customer.id =" + id;

            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String firstName = resultSet.getString(1);
                String lastName = resultSet.getString(2);

                rightCustomer = buildCustomer(firstName, lastName);

            }
            statement.close();
            return rightCustomer;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Customer> list() {
        List<Customer> customerList = new ArrayList<>();
        try {
            Connection dbConnection = connectionMaster.getConnection();
            Statement statement = dbConnection.createStatement();

            String query = "SELECT first_name, last_name FROM customer";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String firstName = resultSet.getString(1);
                String lastName = resultSet.getString(2);
                Customer customer = buildCustomer(firstName, lastName);
                customerList.add(customer);
            }
            statement.close();
            return customerList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {
        Set <Integer> idCustomer = new HashSet<>();
        try {
            Connection dbConnection = connectionMaster.getConnection();
            Statement statement = dbConnection.createStatement();

            String query = "SELECT id FROM customer";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                int playerId = resultSet.getInt(1);
                idCustomer.add(playerId);
            }
            statement.close();
            return idCustomer;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double getBalance(int id) {
        return 0;
    }

    @Override
    public void add(Customer customer) {

    }

    public void setConnectionMaster(ConnectionMaster connectionMaster) {
        this.connectionMaster = connectionMaster;
    }
}

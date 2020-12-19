package ru.tkachenko.ecare.dao;

import org.springframework.stereotype.Component;
import ru.tkachenko.ecare.models.Tariff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TariffDAO {
    private static int TARIFF_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/ecare";
    private static final String USERNAME = "tkach";
    private static final String PASSWORD = "mypassword";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Tariff> index() {
        List<Tariff> listOfTariffs = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM tariffs";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Tariff tariff = new Tariff();
                tariff.setId(resultSet.getInt("id"));
                tariff.setTariffName(resultSet.getString("tariffname"));
                tariff.setTariffCost(resultSet.getInt("tariffcost"));
                listOfTariffs.add(tariff);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfTariffs;
    }

    public Tariff show(int id) {
        Tariff tariff = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM tariffs WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            tariff = new Tariff();
            tariff.setId(resultSet.getInt("id"));
            tariff.setTariffName(resultSet.getString("tariffname"));
            tariff.setTariffCost(resultSet.getInt("tariffcost"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tariff;
    }

    public void save(Tariff tariff) {
        try {
            String SQL = "INSERT INTO tariffs (tariffname, tariffcost)  VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, tariff.getTariffName());
            preparedStatement.setInt(2, tariff.getTariffCost());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Tariff updatedTariff) {
        try {
            String SQL = "UPDATE tariffs SET id = ?, tariffname = ?, tariffcost = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, updatedTariff.getId());
            preparedStatement.setString(2, updatedTariff.getTariffName());
            preparedStatement.setInt(3, updatedTariff.getTariffCost());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            String SQL = "DELETE FROM tariffs WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

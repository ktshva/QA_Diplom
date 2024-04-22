package ru.netology.data;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.QueryRunner;

public class DbDataHelper {
    private static QueryRunner runner = new QueryRunner();

    @SneakyThrows
    public static Connection getConnect() {
        var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        return (conn);
    }

    @SneakyThrows
    public static String getCreditStatus() {
        var creditSelect = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var connect = getConnect();
        return runner.query(connect, creditSelect, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static String getBuyStatus() {
        var paymentSelect = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var connect = getConnect();
        return runner.query(connect, paymentSelect, new ScalarHandler<String>());
    }

    @SneakyThrows
    public static void clearTables() {
        var connection = getConnect();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
    }
}
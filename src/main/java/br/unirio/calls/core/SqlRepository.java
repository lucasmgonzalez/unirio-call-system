package br.unirio.calls.core;

import java.sql.Connection;

public abstract class SqlRepository {
    protected Connection connection;

    public SqlRepository() {
        this.connection = SqlConnectionManager.getConnection();
    }
}
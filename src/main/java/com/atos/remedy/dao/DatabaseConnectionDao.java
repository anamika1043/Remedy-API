package com.atos.remedy.dao;

import java.sql.Connection;

public interface DatabaseConnectionDao
{
    Connection getConnection();
}

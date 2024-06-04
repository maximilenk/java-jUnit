package edu.school21.repositories;

import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {
    private EmbeddedDatabase db;

    @BeforeEach
    public void init() {
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScripts("schema.sql")
                .build();
    }

    @Test
    public void testConnection() {
        Connection con;
        try {
            con = db.getConnection();
            Assertions.assertNotNull(con);
        } catch (SQLException e) {
            con = null;
        }
    }

    @AfterEach
    public void tearDown() {
        db.shutdown();
    }

}

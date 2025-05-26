package com.example.book_store.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
public class SQLImportRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLImportRunner.class);

    @Bean
    CommandLineRunner runSqlFromFile(DataSource dataSource) {
        return args -> {
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement()) {

                String sql = Files.readString(
                        Paths.get(getClass().getClassLoader().getResource("import.sql").toURI()),
                        StandardCharsets.UTF_8
                );

                // Divide las sentencias si hay múltiples separadas por ;
                long a = System.currentTimeMillis();
                for (String statement : sql.split(";")) {
                    String trimmed = statement.trim();
                    if (!trimmed.isEmpty()) {
                        stmt.execute(trimmed);
                    }
                }
                long b = System.currentTimeMillis();
                LOGGER.info("SQL Imported in {} s", (b-a)/1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}

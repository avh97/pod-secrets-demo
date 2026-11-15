package com.example.podsecrets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private final JdbcTemplate jdbcTemplate;
    private final String dbUrl;
    private final String dbUser;

    public HelloController(JdbcTemplate jdbcTemplate,
                           @Value("${DB_URL}") String dbUrl,
                           @Value("${DB_USER}") String dbUser) {
        this.jdbcTemplate = jdbcTemplate;
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("Endpoint /hello invoked");
        String dbTime = jdbcTemplate.queryForObject("select now()::text", String.class);
        return "DB url: " + dbUrl + ", DB user: " + dbUser + ", DB time: " + dbTime + "\n";
    }
}

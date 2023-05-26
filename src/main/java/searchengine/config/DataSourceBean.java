package searchengine.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceBean {
    private @Value("${search.engine.db.password}") String dbPassword;
    //SQLdb12345
    private @Value("${search.engine.db.username}") String dbUserName;
    //root
    private @Value("${search.engine.db.url}") String dbUrl;
    //jdbc:mysql://localhost:3306/search_engine?useSSL=false&requireSSL=false&allowPublicKeyRetrieval=true

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                .url(dbUrl)
                .username(dbUserName)
                .password(dbPassword)
                .build();
    }
}
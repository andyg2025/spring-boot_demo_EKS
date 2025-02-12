package order_service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.PostConstruct;

import java.util.Base64;

@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfigProperties {

    private String url;
    private String username;
    private String password;

    @Value("${SPRING_DATASOURCE_PASSWORD}")
    private String encodedPassword;

    @Value("${DB_ENDPOINT}")
    private String encodedDbEndpoint;

    @Value("${DB_NAME}")
    private String encodedDbName;

    @Value("${SPRING_DATASOURCE_USERNAME}")
    private String encodedUsername;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Decode the environment variables and update the values
    @PostConstruct
    public void decodeProperties() {
        if (encodedPassword != null) {
            this.password = new String(Base64.getDecoder().decode(encodedPassword));
        }
        if (encodedDbEndpoint != null && encodedDbName != null) {
            this.url = "jdbc:postgresql://" + new String(Base64.getDecoder().decode(encodedDbEndpoint)) +
                    "/" + new String(Base64.getDecoder().decode(encodedDbName));
        }
        if (encodedUsername != null) {
            this.username = new String(Base64.getDecoder().decode(encodedUsername));
        }
    }
}

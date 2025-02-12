package order_service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct;

import java.util.Base64;

@Component
public class ConfigDecoder {

    @Value("${SPRING_DATASOURCE_PASSWORD}")
    private String encodedPassword;

    @Value("${DB_ENDPOINT}")
    private String encodedDbEndpoint;

    @Value("${DB_NAME}")
    private String encodedDbName;

    @Value("${SPRING_DATASOURCE_USERNAME}")
    private String encodedUsername;

    private String decodedPassword;
    private String decodedDbEndpoint;
    private String decodedDbName;
    private String decodedUsername;

    @PostConstruct
    public void decodeParameters() {
        // Decode each environment variable if they are set and not empty
        if (encodedPassword != null && !encodedPassword.isEmpty()) {
            decodedPassword = new String(Base64.getDecoder().decode(encodedPassword));
            System.out.println("Decoded password: " + decodedPassword);
        }

        if (encodedDbEndpoint != null && !encodedDbEndpoint.isEmpty()) {
            decodedDbEndpoint = new String(Base64.getDecoder().decode(encodedDbEndpoint));
            System.out.println("Decoded DB endpoint: " + decodedDbEndpoint);
        }

        if (encodedDbName != null && !encodedDbName.isEmpty()) {
            decodedDbName = new String(Base64.getDecoder().decode(encodedDbName));
            System.out.println("Decoded DB name: " + decodedDbName);
        }

        if (encodedUsername != null && !encodedUsername.isEmpty()) {
            decodedUsername = new String(Base64.getDecoder().decode(encodedUsername));
            System.out.println("Decoded username: " + decodedUsername);
        }
    }

    // Getter methods for decoded values, if you need to access them elsewhere in your application
    public String getDecodedPassword() {
        return decodedPassword;
    }

    public String getDecodedDbEndpoint() {
        return decodedDbEndpoint;
    }

    public String getDecodedDbName() {
        return decodedDbName;
    }

    public String getDecodedUsername() {
        return decodedUsername;
    }
}
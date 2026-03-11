package developer.jota.config;

import external.dependency.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfiguration {

    @Bean(name = "connMySQL")
    public Connection connectionMySQL(){
        return new Connection("localhost", "MySQL", "javaejota");
    }

    @Bean(name = "connMongoDB")
    public Connection connectionMongo(){
        return new Connection("localhost", "MongoDB", "javaejota");
    }
}

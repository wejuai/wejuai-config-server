package com.wejuai.config.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ZM.Wang
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServer {

    private static final Logger logger = LoggerFactory.getLogger(ConfigServer.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ConfigServer.class);
        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        addDefaultProfile(app, source);
        Environment env = app.run(args).getEnvironment();
        String port = env.getProperty("server.port");
        logger.info("\nAccess URLs:\n----------------------------------------------------------\n"
                + "Local: \t\thttp://127.0.0.1:{}/actuator/health\n" +
                "----------------------------------------------------------", port);
    }

    /**
     * If no profile has been configured, set by default the "dev" profile.
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
        if (!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
            app.setAdditionalProfiles("dev");
        }
    }

    @GetMapping("/test")
    public void test(){
    }

}

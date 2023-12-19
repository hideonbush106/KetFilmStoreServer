package com.sukute1712.ketfilmstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;

import java.util.Properties;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class CommonConfig {

    @Value("${cloudinary.api-key}")
    private String cloudinaryAPIKey;
    @Value("${cloudinary.cloud-name}")
    private String cloudinaryCloudName;
    @Value("${cloudinary.api-secret}")
    private String cloudinaryAPISecret;

    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private int mailPort;
    @Value("${spring.mail.password}")
    private String mailPassword;
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean isMailSmtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean isMailStartTLSEnable;


    /**
     * Spring Boot Mail Sender
     */
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", isMailSmtpAuth);
        props.put("mail.smtp.starttls.enable", isMailStartTLSEnable);
        //TODO: remove when production
        props.put("mail.debug", "true");

        return mailSender;
    }

    /**
     * modelmapper - using in mapping entity with dto and vice versa
     * @see <a href="https://modelmapper.org/">modelmapper</a>
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * springdoc - API Documentation
     * @see <a href="https://springdoc.org/">springdoc</a>
     */
    @Bean
    public OpenAPI ketfilmstoreAPI() {
        return new OpenAPI()
                .info(new Info().title("Ket Film Store API")
                        .description("Ket Film Store sample application")
                        .version("v0.0.1"));
    }

    /**
     * Cloudinary - Streamline media management
     * @see <a href="https://springdoc.org/">Cloudinary</a>
     */
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryCloudName,
                "api_key", cloudinaryAPIKey,
                "api_secret", cloudinaryAPISecret,
                "secure", true));
    }

}

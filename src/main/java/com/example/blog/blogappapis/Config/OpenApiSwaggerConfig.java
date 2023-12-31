package com.example.blog.blogappapis.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name="Prometheus",
                        email="prometheus731@hmail.com",
                        url="Default UrL"),
                description = "OpenAPI documentation for blog application",
                title = "Blog Application",
                version="1.0",
                license = @License(
                        name="Licence name",
                        url="https://someURL.com"),
                termsOfService = "Terms of service"
                )
        )
@SecurityScheme(
        name="bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type=SecuritySchemeType.APIKEY,
        bearerFormat = "JWT",
        in=SecuritySchemeIn.HEADER
)
public class OpenApiSwaggerConfig {

    public static final String AUTHORIZATION_HEADER="Authorization";

    }


//    @Bean
//    public GroupedOpenApi api(){
//        return GroupedOpenApi.builder()
//                .
//                .build();
//    }


package com.UrlShortener;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;
/*
* @ConfigurationProperties is used to bind values from application.properties (or YAML) into a Java class.
        It automatically maps:
                    property keys → Java fields
                    without writing @Value again and again.
✅ 2. What does prefix = "app" mean?
        It means:
                “Bind all properties that start with app. into this Java class.”
        Example in application.properties:
                app.base-url=http://localhost:8080
                app.default-expiry-in-days=30
                app.validate-original-url=true
*/

@ConfigurationProperties(prefix = "app")
@Validated
public record ApplicationProperties(
        @NotBlank
        @DefaultValue("http://localhost:8080")
        String baseUrl,
        @DefaultValue("30")
        @Min(1)
        @Max(365)
        int defaultExpiryInDays,
        @DefaultValue("true")
        boolean validateOriginalUrl,
        @DefaultValue("7")
        int pageSize
) {
}
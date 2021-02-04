package com.gallery.photo.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.gallery.photo.repository")
//@EntityScan(basePackages = "com.travel_agency.model")
@EntityScan(basePackages= {"com.gallery.photo.model"})
public class SpringDataConfig {

}

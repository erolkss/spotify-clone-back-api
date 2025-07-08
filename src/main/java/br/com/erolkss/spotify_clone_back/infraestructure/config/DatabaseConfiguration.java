package br.com.erolkss.spotify_clone_back.infraestructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories({
        "br.com.erolkss.spotify_clone_back.usercontext.repository",
        "br.com.erolkss.spotify_clone_back.catalogcontext.repository"
})
@EnableTransactionManagement
@EnableJpaAuditing
public class DatabaseConfiguration {
}
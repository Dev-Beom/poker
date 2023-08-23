package com.play.poker.config

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JacksonConfig {
    @Bean
    fun objectMapperBuilder(): Jackson2ObjectMapperBuilder {
        val objectMapperBuilder = Jackson2ObjectMapperBuilder()
        objectMapperBuilder.modules(JavaTimeModule())
        return objectMapperBuilder
    }
}
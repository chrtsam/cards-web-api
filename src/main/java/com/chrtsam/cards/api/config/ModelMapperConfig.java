package com.chrtsam.cards.api.config;

import com.chrtsam.cards.api.model.external.TaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Chris
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TaskDTO.addMappings(modelMapper);
        return modelMapper;
    }
}

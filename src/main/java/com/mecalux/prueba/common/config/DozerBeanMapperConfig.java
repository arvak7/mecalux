package com.mecalux.prueba.common.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class DozerBeanMapperConfig {

    @Bean
    public DozerBeanMapper dozerBean() {
        List<String> mappingFiles = Collections.singletonList("dozerBeanMapping.xml");
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }

}

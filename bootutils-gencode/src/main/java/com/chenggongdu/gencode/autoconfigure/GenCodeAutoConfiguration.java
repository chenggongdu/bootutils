package com.chenggongdu.gencode.autoconfigure;

import com.chenggongdu.gencode.core.service.GenCodeService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * GenCodeAutoConfiguration
 *
 * @author chenggongdu
 */
@AutoConfiguration
@ConditionalOnClass(GenCodeService.class)
public class GenCodeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public GenCodeService myStarterService() {
        return new GenCodeService();
    }
}

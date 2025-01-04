package io.github.azirzsk.fcp.spring;

import io.github.azirzsk.fcp.FCP;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangshukun
 * @since 2025/1/4
 */
@Configuration
public class FCPAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public FCP fcp() {
        return FCP.create();
    }

    @Bean
    public FCPBeanPostProcessor fcpBeanPostProcessor(FCP fcp) {
        return new FCPBeanPostProcessor(fcp);
    }
}

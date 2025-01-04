package io.github.azirzsk.fcp.spring;

import io.github.azirzsk.fcp.FCP;
import io.github.azirzsk.fcp.annotation.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhangshukun
 * @since 2025/1/4
 */
@Slf4j
public class FCPBeanPostProcessor implements BeanPostProcessor {

    private final FCP fcp;

    public FCPBeanPostProcessor(FCP fcp) {
        Assert.notNull(fcp, "FunctionCall解析对象不能为空");
        this.fcp = fcp;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        boolean match = Arrays.stream(methods)
                .anyMatch(method -> AnnotationUtils.findAnnotation(method, Function.class) != null);
        if (!match) {
            return bean;
        }
        fcp.parse(bean);
        return bean;
    }
}

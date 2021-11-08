package com.bobocode.trimming.config;

import com.bobocode.trimming.anotation.Trimmed;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TrimmedAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Trimmed.class)) {
            return createTrimmedProxy(beanClass);
        }
        return bean;
    }

    private Object createTrimmedProxy(Class<?> beanClass) {
        var enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setInterfaces(beanClass.getInterfaces());
        MethodInterceptor methodInterceptor =
                (Object obj, Method method, Object[] args, MethodProxy proxy) -> {
                    args = Arrays.stream(args)
                            .map(arg -> arg.getClass() == String.class ?
                                    ((String) arg).trim() :
                                    arg)
                            .toArray();

                    return method.getReturnType() == String.class ?
                            ((String) proxy.invokeSuper(obj, args)).trim() :
                            proxy.invokeSuper(obj, args);
                };
        enhancer.setCallback(methodInterceptor);
        return beanClass.cast(enhancer.create());
    }
}

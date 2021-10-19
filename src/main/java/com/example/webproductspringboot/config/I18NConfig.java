package com.example.webproductspringboot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class I18NConfig implements WebMvcConfigurer {

    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("utf-8");
        ms.setBasenames(
                "classpath:i18n/lang",
                "classpath:i18n/banner",
                "classpath:i18n/brand",
                "classpath:i18n/category",
                "classpath:i18n/collection",
                "classpath:i18n/invoice",
                "classpath:i18n/order",
                "classpath:i18n/product",
                "classpath:i18n/return",
                "classpath:i18n/review",
                "classpath:i18n/transport",
                "classpath:i18n/user",
                "classpath:i18n/voucher"
        );
        return ms;
    }

    @Bean("localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(new Locale("vi_VN"));
        cookieLocaleResolver.setCookiePath("/");
        cookieLocaleResolver.setCookieMaxAge(30 * 24 * 60 * 60);
        return cookieLocaleResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor)
                .addPathPatterns("/**");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author Pandu
 */

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class PageConfig extends WebMvcConfigurerAdapter {

//     @Override
//     public void addResourceHandlers(ResourceHandlerRegistry registry) {
// //        super.addResourceHandlers(registry);
//         registry.addResourceHandler("/webjars/**")
//                 .addResourceLocations("classpath:/META-INF/resources/webjars/");
//         registry.addResourceHandler("/js/**")
//                 .addResourceLocations("classpath:/static/js/");
//         registry.addResourceHandler("/css/**")
//                 .addResourceLocations("classpath:/static/css/");
//     }

    @Bean
    LayoutDialect thymeleafLayoutDialect() {
        return new LayoutDialect();
    }
//
//    @Bean
//    GroupingStrategy thymeleafGroupingStrategy() {
//        return new GroupingStrategy();
//    }

//    @Bean
//    public JasperReportsViewResolver getJasperReportsViewResolver() {
//        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
//        resolver.setPrefix("classpath:/jasper/");
//        resolver.setSuffix(".jasper");
//        resolver.setViewNames("*report_*");
//        resolver.setReportDataKey("datasource");
//        resolver.setViewClass(JasperReportsMultiFormatView.class);
//        resolver.setOrder(0);
//        return resolver;
//    }

}
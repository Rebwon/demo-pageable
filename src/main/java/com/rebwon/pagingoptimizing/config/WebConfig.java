package com.rebwon.pagingoptimizing.config;

import com.rebwon.pagingoptimizing.util.CustomPagedResourceAssembler;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new PageableArgumentResolver());
  }

  @Bean
  public CustomPagedResourceAssembler<?> customPagedResourceAssembler() {
    return new CustomPagedResourceAssembler<>(new HateoasPageableHandlerMethodArgumentResolver(), 10);
  }
}

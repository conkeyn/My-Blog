package com.my.blog.website.interceptor;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.my.blog.website.utils.TaleUtils;

/**
 * 向mvc中添加自定义组件 Created by BlueT on 2017/3/9.
 */
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter {
  private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);

  @Resource
  private BaseInterceptor baseInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(baseInterceptor);
  }

  /**
   * 添加静态资源文件，外部可以直接访问地址
   * 
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String uploadPath = "file:" + TaleUtils.getWebSiteRootPath() + "/upload/";
    LOGGER.info("uploadPath: " + uploadPath);
    registry.addResourceHandler("/upload/**").addResourceLocations(uploadPath);
    super.addResourceHandlers(registry);
  }
}

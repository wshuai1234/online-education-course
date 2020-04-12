package net.xdclass.xdvideo.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * mybatis pageHelper configuration
 */
@Configuration
public class MyBatisConfig {
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();


        // when setting true, RowBounds first param offset will be pageNum
        p.setProperty("offsetAsPageNum","true");
        //when setting true, use RowBounds will incur count query

        p.setProperty("rowBoundsWithCount","true");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
package cc.mrbird.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @PackageName:cc.mrbird.security.browser
 * @ClassName:SecurityConfig
 * @Description:
 * @author: 悟空
 * @date: 2021/4/17 20:21
 * @email: 10947@163.com
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //定义用户信息Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    //定义用户认证的Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //通过内存方式定义下面两个用户
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123").roles("USER")
                .and()
                //superAdmin 则是一个超级管理员
                .withUser("superAdmin")
                .password("superPwd")
                .roles("USER","ADMIN");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //这里需要将csrf禁用，否则会报不能跨域访问的错误
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE,"/products/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }


}

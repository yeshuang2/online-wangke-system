package com.wzz.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserLoadSecurityServiceImpl userLoadSecurityService;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userLoadSecurityService);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/public/**").hasAnyAuthority("student", "teacher", "admin")
                .mvcMatchers("/student/**").hasAnyAuthority("student", "admin", "teacher")
                .mvcMatchers("/teacher/**").hasAnyAuthority("teacher", "admin")
                .mvcMatchers("/admin/**").hasAnyAuthority("student","teacher", "admin")
                .mvcMatchers("/wangke/**").hasAnyAuthority("admin","student", "teacher")
                .mvcMatchers("/studyduration/**").hasAnyAuthority("admin","student", "teacher")
                .mvcMatchers("/fepeiyewu/**").hasAnyAuthority("student", "teacher","admin")
                .mvcMatchers("/xunlinajiluinfo/**").hasAnyAuthority("student", "teacher","admin")
                .mvcMatchers("/echarts/**").hasAnyAuthority("student", "teacher","admin")
                // 配合下面的web.ignore 将下面所有的路径的校验都过滤掉了
                .mvcMatchers("/util/**", "/common/**", "/actuator/**", "/api/**/**").permitAll()
//                .antMatchers("/admin/policemen/**").permitAll()  //视频播放
                .antMatchers("/api/policemen/**").anonymous()
                .anyRequest().authenticated()

                .and()

                // token认证
                .addFilterBefore(new TokenAuthFilter(authenticationManager()), BasicAuthenticationFilter.class)

                // 认证异常处理器
                .exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .authenticationEntryPoint(new MyAuthenticationEntryPointHandler())

                .and().csrf().disable();// 默认csrf token 校验开启，针对 POST, PUT, PATCH

        http.formLogin().disable();
        // 前后端分离关闭配置登录
        http.logout().disable();

        // 所有的Rest服务一定要设置为无状态，以提升操作性能
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/util/**", "/common/**")
                .antMatchers("/actuator/**")
                .antMatchers("/api/**")
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/v2/**");
    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
}

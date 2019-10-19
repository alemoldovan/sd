package utcn.labs.sd.bankingservice.core.configuration;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Banking Service security configurations password is encoded with {@link BCryptPasswordEncoder}
 */
@Configuration
@EnableWebSecurity
public class EndpointsWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	
	@Autowired
	DataSource datasource;
	
    @Value("${credentials.employee.username}")
    private String employeeUsername;

    @Value("${credentials.employee.password}")
    private String employeePassword;

    @Value("${credentials.employee.role}")
    private String employeeRole;

    @Value("${credentials.admin.username}")
    private String adminUsername;

    @Value("${credentials.admin.password}")
    private String adminPassword;

    @Value("${credentials.admin.role}")
    private String adminRole;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*PasswordEncoder encoder = new BCryptPasswordEncoder();
        InMemoryUserDetailsManagerConfigurer authConfigurer = auth.inMemoryAuthentication()
                .passwordEncoder(encoder);
        authConfigurer.withUser(employeeUsername)
                .password(encoder.encode(this.employeePassword))
                .roles(employeeRole);
        authConfigurer.withUser(adminUsername)
                .password(encoder.encode(this.adminPassword))
                .roles(adminRole);*/
        
        auth.jdbcAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance()).dataSource(datasource)
        .usersByUsernameQuery("select username as principal, password as credentials, true from user_table where username = ?")
        .authoritiesByUsernameQuery("select u.username as principal, r.role as role from user_table u join role_table r on u.roleid = r.roleid where username = ?")
        .rolePrefix("ROLE_");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/bank/employee/**").hasRole(employeeRole)
                .antMatchers("/bank/admin/**").hasRole(adminRole).anyRequest().permitAll()
                .antMatchers("/bank/login").hasAnyRole(adminRole, employeeRole).anyRequest().permitAll()
                .and()
                .httpBasic();
    }
    
   
}

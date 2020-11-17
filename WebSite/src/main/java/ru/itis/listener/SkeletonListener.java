package ru.itis.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 26.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebListener
public class SkeletonListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("ишыщкщ");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        SizeRepository sizeRepository = new SizeRepositoryJdbcTemplateImpl(dataSource);
        ProductsRepository productsRepository = new ProductsRepositoryJdbcTemplateImpl(dataSource);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        CheckingService checkingService = new CheckingServiceImpl(usersRepository);
        ProductService productService = new ProductServiceImpl(productsRepository);
        AdminService adminService = new AdminServiceImpl(sizeRepository);
        CartService cartService = new CartServiceImpl(sizeRepository, usersRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("productService", productService);
        servletContext.setAttribute("checkingService",checkingService);
        servletContext.setAttribute("cartService", cartService);
        servletContext.setAttribute("adminService", adminService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

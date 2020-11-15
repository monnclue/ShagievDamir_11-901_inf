package ru.itis.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // преобразуем запросы к нужному виду
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // берем сессию у запроса
        // берем только существующую, если сессии не было - то вернет null
        HttpSession session = request.getSession(false);
        // флаг, аутентифицирован ли пользователь
        Boolean isAuthenticated = false;
        // флаг, пользователь == админ
        Boolean isAdmin = false;
        // существует ли сессия вообще?
        Boolean sessionExists = session != null;
        // идет ли запрос на страницу продукта?
        Boolean isRequestOnProductPage = request.getRequestURI().startsWith("/product");
        // это css?
        Boolean isCss = request.getRequestURI().endsWith(".css");
        // svg?
        Boolean isSVG = request.getRequestURI().endsWith(".svg");
        // mode?
        Boolean isRequestMode = request.getRequestURI().startsWith("/mode");
        // идет ли запрос на главную страницу?
        Boolean isRequestOnShopPage = request.getRequestURI().startsWith("/shop");
        // идет ли запрос на signIn signUp?
        Boolean isRequestOnAuthPage = request.getRequestURI().equals("/signIn") ||
                request.getRequestURI().equals("/signUp");
        // идет ли запрос на открытую страницу?
        Boolean isRequestOnOpenPage = isRequestOnAuthPage|| isRequestOnProductPage
                || isRequestOnShopPage|| isRequestMode || isCss || isSVG;
        // идет ли запрос на админ страницу?
        Boolean isRequestOnAdminPage = request.getRequestURI().equals("/admin");


        // если сессия есть
        if (sessionExists) {
            // проверим, есть ли атрибует user?
            isAuthenticated = session.getAttribute("user") != null;
            // проверим, является ли пользователь админом?
            isAdmin = session.getAttribute("admin") != null;
        }

        // если авторизован и запрашивает не страницу SignIn/SignUp
        // или если не авторизован и запрашивает открытую
        // либо админ и запрос на админпейдж

        if ((isAuthenticated && !isRequestOnAuthPage && !isRequestOnAdminPage)
                || (!isAuthenticated && isRequestOnOpenPage)
                || (isAdmin && isRequestOnAdminPage)) {
            // отдаем ему то, что он хочет
            filterChain.doFilter(request, response);
        } else if (isAuthenticated && isRequestOnAuthPage) {
            // пользователь аутенцифицирован и запрашивает страницу входа
            // - отдаем ему профиль
            response.sendRedirect("/profile");
        } else {
            // если пользователь не аутенцицицирован и запрашивает другие страницы
            response.sendRedirect("/shop");
        }


    }

    @Override
    public void destroy() {

    }
}

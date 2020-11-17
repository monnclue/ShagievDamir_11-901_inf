package ru.itis.jspFillers;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class JSPFiller {

    private String button;
    private String action;
    private String method;
    private HttpServletRequest request;

    public JSPFiller(HttpServletRequest request) {
        this.request = request;
    }

    public void insertButtonActionSignOrBasket() {
        HttpSession requestSession = request.getSession(false);
        this.button = "Вход/Регистрация";
        this.action = "/signIn";
        this.method = "get";
        if (requestSession != null) {
            if (requestSession.getAttribute("user") != null) {
                this.button = "Корзина";
                this.action = "/cart";
                this.method = "get";
            }
        }
    }

    public boolean isAuthenticated() {
        HttpSession requestSession = request.getSession(false);
        if (requestSession != null) {
            return requestSession.getAttribute("user") != null;
        }
        return false;
    }

    public String getButton() {
        return button;
    }

    public String getAction() {
        return action;
    }

    public String getMethod() {
        return method;
    }
}

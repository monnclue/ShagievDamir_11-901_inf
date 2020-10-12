package ru.itis.repositories;

import javax.servlet.http.Cookie;

public class CookieServletTemplate {
    private Cookie[] cookies;

    public CookieServletTemplate(Cookie[] cookies) {
        this.cookies = cookies;
    }

    public String getValueByCookieName(String name){
        String value = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                value = cookie.getValue();
            }
        }
        return value;
    }

}

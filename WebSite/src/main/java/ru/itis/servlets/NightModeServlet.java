package ru.itis.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.Mode;
import ru.itis.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/mode")
public class NightModeServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mode mode = objectMapper.readValue(req.getReader(), Mode.class);
        Cookie cookie = new Cookie("mode", "day");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookier: cookies) {
            if (cookier.getName().equals("mode")) {
                cookie = cookier;
            }
        }
        cookie.setValue(mode.getMode());
        resp.addCookie(cookie);

    }
}

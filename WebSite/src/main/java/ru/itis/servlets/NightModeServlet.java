package ru.itis.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.Mode;
import ru.itis.models.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/mode")
public class NightModeServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mode mode = objectMapper.readValue(req.getReader(), Mode.class);
        req.getSession().setAttribute("night-mode", mode.getNight());
        String modeJSON = objectMapper
                .writeValueAsString(mode.getNight());
        resp.setContentType("application/json");
        resp.getWriter().println(modeJSON);
    }
}

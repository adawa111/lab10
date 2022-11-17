package Servlet;

import Dao.DaoCliente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletAdmin", value = "/ServletAdmin")
public class ServletAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher view;
        DaoCliente cl = new DaoCliente();
        switch (action) {
            case "crear":
                String dni = request.getParameter("documento");
                String pass = request.getParameter("password");
                cl.crearCredentialCliente(dni,pass);
                response.sendRedirect(request.getContextPath());
                break;

        }


    }
}

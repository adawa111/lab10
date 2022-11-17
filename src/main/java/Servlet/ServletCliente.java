package Servlet;

import Bean.Clientes;
import Dao.DaoCliente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletCliente", value = "/ServletCliente")
public class ServletCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoCliente daocli = new DaoCliente();
        switch (action) {
            case "puntaje":

                break;
            case "datos":
                String id = request.getParameter("id");
                Clientes clie = daocli.buscarCliente(id);
                request.setAttribute("client",clie);
                view = request.getRequestDispatcher("clientedatos.jsp");
                view.forward(request, response);
                break;
            case "contratos":

                break;

            case "estados":

                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package Servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletInicio", value = "/ServletInicio")
public class ServletInicio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        switch (action) {
            case "lista":
                /*
                Inserte su código aquí
                 */
                ArrayList<Arbitro> listaArbitros = null;
                listaArbitros = arbitrosDao.listarArbitros();

                request.setAttribute("listaArbitros",listaArbitros);
                request.setAttribute("opciones",opciones);
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

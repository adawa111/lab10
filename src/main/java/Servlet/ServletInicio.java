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
            case "crear":
                /*
                Inserte su código aquí
                */
                request.setAttribute("paises",paises);
                view = request.getRequestDispatcher("/arbitros/form.jsp");
                view.forward(request, response);
                break;

            case "borrar":
                /*
                Inserte su código aquí
                */

                String id1 = request.getParameter("id");
                int id = Integer.parseInt(id1);
                arbitrosDao.borrarArbitro(id);
                response.sendRedirect(request.getContextPath()+ "/ArbitroServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

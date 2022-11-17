package lab10.lab10;

import Bean.Clientes;
import Bean.Contratos;
import Dao.DaoCliente;
import Dao.DaoContrato;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletCliente", value = "/ServletCliente")
public class ServletCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        String id = request.getParameter("id");
        DaoCliente daoCliente = new DaoCliente();
        DaoContrato contratoDao = new DaoContrato();
        switch (action) {
            case "puntaje": //expected loss

                request.setAttribute("puntaje", daoCliente.mostrarMaxExpectedLoss(id));
                view = request.getRequestDispatcher("clientepuntaje.jsp");
                view.forward(request, response);

                break;
            case "buscarCliente":

                Clientes cliente = daoCliente.buscarCliente(id);
                request.setAttribute("client",cliente);
                view = request.getRequestDispatcher("clientedatos.jsp");
                view.forward(request, response);
                break;
            case "listarContratos":
                ArrayList<Contratos> listaContratos = contratoDao.listarContratos();
                request.setAttribute("listaContratos",listaContratos);
                view = request.getRequestDispatcher("clientecontratos.jsp");
                view.forward(request, response);
                break;

            case "mostrarContratos":

                request.setAttribute("listaContratosxEstado",contratoDao.mostrarContratosxEstado());
                view = request.getRequestDispatcher("clienteestados.jsp");
                view.forward(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/ServletCliente");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

package Servlet;

import Bean.Clientes;
import Bean.Credentials;
import Dao.DaoCliente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletInicio", value = "/ServletInicio")
public class ServletInicio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoCliente cli = new DaoCliente();
        switch (action) {
            case "login":
                /*
                Inserte su código aquí
                 */
                String user = request.getParameter("usuario");
                String pass = request.getParameter("password");
                Credentials cre = cli.buscarUsuario(user,pass);

                if(cre != null){
                    HttpSession session = request.getSession();
                    session.setAttribute("sesion",cre);
                    int a = cre.getTipoUsuario();
                    if (a==1){
                        ArrayList<Clientes> listaclientes = null;
                        listaclientes = cli.listarClientes();
                        request.setAttribute("lista",listaclientes);
                        view = request.getRequestDispatcher("admin.jsp");
                        view.forward(request,response);
                    } else if (a==2) {
                        view = request.getRequestDispatcher("cliente.jsp");
                        view.forward(request,response);
                    }
                }else {
                    response.sendRedirect(request.getContextPath()+"/ServletInicio?error");
                }
                break;


        }
    }
}

package testprodest.cgt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Helen
 */
@WebServlet(name = "RecebeInfo", urlPatterns = "/RecebeInfo")
public class RecebeInfo extends HttpServlet {

    public static final Apl apl = new Apl();
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ClassNotFoundException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String a = apl.resultado(request.getParameter("codigo"), request.getParameter("cpf"));
            
            out.println("<!DOCTYPE html> <html><head>"
                    + "<title>Teste - Projeto ES na Palma da Mão</title>"
                    + "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>"
                    + "<link rel='stylesheet' type='text/css' href='tela.css'>"
                    + "</head><body BGCOLOR='FFC0CB'>"
                    + "<div class ='container'>"
                    + "<header>"
                    + "<h1>Projeto ES na Palma da Mão</h1>"
                    + "</header>"
                    + "<nav>"
                    + "<ul>"
                    + "<form action='RecebeInfo' method='POST'>"
                    + "<fieldset>"
                    + "<legend>Buscar por:</legend>"
                    + "<label>CPF:</label><br>"
                    + "<input type='text' name='cpf' autofocus><br>"
                    + "Ex: 000.000.000-00<br><br>"
                    + "ou<br><br>"
                    + "Código:<br>"
                    + "<input type='text' name='codigo'><br><br>"
                    + "<input type='submit' value='OK' autofocus >"
                    + "</fieldset>"
                    + "</form></ul> </nav> "
                    + "<nav> <ul> <form>"
                    + "<fieldset>"
                    + "<legend>Resultado da busca:</legend>"
                    + a
                    + "</fieldset>"
                    + "</form> </ul> </nav>"
                    + "<footer>Teste - Prodest - ES na Palma da Mão</footer>"
                    + " </div> </body>"
                    + "</html>");

        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RecebeInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RecebeInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Servlet Recebe Info";
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ValidaSesionesServlet extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      HttpSession sesion = request.getSession();
      String titulo = null;

      //Pedimos el atributo, y verificamos si existe
      String claveSesion = (String) sesion.getAttribute("claveSesion");
      String usuario = (String) sesion.getAttribute("nombre");
out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">\n" +
"        ");
      out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n" +
"        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js\" integrity=\"sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49\" crossorigin=\"anonymous\"></script>\n" +
"        <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js\" integrity=\"sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy\" crossorigin=\"anonymous\"></script>\n" +
"       ");
      out.println("<div class='jumbotron'>");

            Connection c; 
            Statement s=null;
            ResultSet r;
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                c = DriverManager.getConnection("jdbc:mysql://localhost/Lab3","root","PIes3.1416");
                s= c.createStatement();
            }
            catch( SQLException error) {
                out.print(error.toString() );
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ValidaSesionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            String us=null, pas=null;
            try{
                r = s.executeQuery("select * from Usuario where userr='"+usuario+"'");
                if(r.next()){
                    us = r.getString("userr");
                    pas = r.getString("passwordd");
                }
            }
            catch( SQLException error) {
                out.print(error.toString() );
            }
      if(claveSesion.equals(us+pas)){
        titulo = "Llave CORRECTA, continua la sesion.";
        out.println("<br><a href=\"/EjemploSession/"+us+".html\"> Ir a pagina del usuario.   </a><br>");
        
      }
      else
      {
        titulo = "Llave INCORRECTA inicie nuevamente sesion.";
      }   
      
       

      //Mostramos los  valores en el cliente
      out.println("¿Continua la Sesion y es valida?: " + titulo);
      out.println("<br>");
      out.println("ID de la sesi&oacute;n JSESSIONID: " + sesion.getId());
      out.println("</div>");
//      out.println(us+pas);
//      out.println(claveSesion);
//      out.println(usuario);
  
    }

}

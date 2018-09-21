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


            Connection c=null; 
            Statement s=null;
            ResultSet r = null;
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
                r = s.executeQuery("select * from Usuario");
                if(r.next()){
                    us = r.getString("userr");
                    pas = r.getString("passwordd");
                }
                else
                    out.println("<script>alert('No existe la persona.')</script>");
//                s.executeUpdate("INSERT INTO datos2 VALUES( '"+contra+"','"+name+"','"+estado+"','"+sex+"');");
//                out.println("<script>confirma()</script>");
//                c.close();
        
            }
            catch( SQLException error) {
                out.print(error.toString() );
            }
      if(claveSesion.equals(us+pas)){
        titulo = "llave correcta continua la sesion";
      }
      else
      {
        titulo = "llave incorrecta inicie nuevamente sesion";
      }   


      //Mostramos los  valores en el cliente
      out.println("¿Continua la Sesion y es valida?: " + titulo);
      out.println("<br>");
      out.println("ID de la sesi&oacute;n JSESSIONID: " + sesion.getId());
  
    }

}

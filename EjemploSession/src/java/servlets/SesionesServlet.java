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


public class SesionesServlet extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

      response.setContentType("text/html");
      HttpSession sesion = request.getSession();
      String titulo = null;

      //Pedimos el atributo, y verificamos si existe
      Integer contadorVisitas =
                (Integer) sesion.getAttribute("contadorVisitas");

      //Si es igual a nulo, quiere decir que es la primera
      //vez que accedemos al recurso
      if(contadorVisitas == null){
        contadorVisitas = new Integer(1);
        titulo = "Bienvenido por primera vez...";
      }
      else
      {
        //si es distinto de nulo, incrementamos el contador
        titulo = "Bienvenido Nuevamente...";
        contadorVisitas += 1;
      }

      //En cualquier caso, agregamos el valor del contador
      //a la sesion
      sesion.setAttribute("contadorVisitas", contadorVisitas);

      //Mostramos los  valores en el cliente
      PrintWriter out = response.getWriter();
      out.println("T&iacute;tulo: " + titulo);
      out.println("<br>");
      out.println("No. Accesos al recurso: " + contadorVisitas);
      out.println("<br>");
      out.println("ID de la sesi&oacute;n: " + sesion.getId());
  
    }

   

}

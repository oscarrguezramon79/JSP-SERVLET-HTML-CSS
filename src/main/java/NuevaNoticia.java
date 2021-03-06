

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NuevaNoticia extends HttpServlet
{
  // Manipular la petici?n enviada por el cliente
  // utilizando el atributo method=post.
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
  throws ServletException, IOException
  {
    // Tipo de la respuesta que ser? enviada al cliente
    response.setContentType("text/html");

    // Obtener el objeto 'PrintWriter' para devolver la respuesta
    PrintWriter out = response.getWriter();

    // Registrar la tutor?a
    try
    {
      // Abrir el fichero para el registro de la tutor?a solicitada
      FileWriter fw = new FileWriter("C:/Desarrollo/Java/Pruebas/WebApp/data/datos.dat", true); 
      PrintWriter fichFormulario = new PrintWriter(fw);

      // Tomar los datos recibidos del cliente y escribirlos
      // en el fichero. Se finaliza cada registro con la marca
      // <FIN> para su posterior identificaci?n.
      Enumeration nombresParams = request.getParameterNames();
      while (nombresParams.hasMoreElements())
      {
        String param = (String)nombresParams.nextElement();
        String valor = request.getParameter(param);
        fichFormulario.println(param + ": " + valor);
      }
      fichFormulario.println("<FIN>");

      // Cerrar el fichero
      fichFormulario.close(); 
      fw.close();

      // Responder al cliente
      out.println("<html>");
      out.println("<title>Respuesta a la solicitud</title>");
      out.println("Su petici?n ha sido registrada<br>Un saludo");
      out.println("</html>");
    }
    catch(IOException e)
    {
      out.println("Hubo problemas cursando su solicitud." +
                  "<br>Por favor, int?ntelo otra vez.");
    }
    // Cerrar el flujo
    out.close();
  }
  
  // Devuelve una descripci?n breve.
  public String getServletInfo()
  {
    return "Servlet Formulario";
  }
}

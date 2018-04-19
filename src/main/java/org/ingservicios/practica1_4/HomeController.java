package org.ingservicios.practica1_4;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private static final String nombreUsuario = "abc";
	private static final String claveUsuario = "def";
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Bienvenido! El cliente es {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	@RequestMapping(value = "/Acceso", method = RequestMethod.POST)
	public String emailList(HttpServletRequest req, Model mod) {
		
		String usuario = req.getParameter("usuario");
		String clave = req.getParameter("clave");
		
		if (usuario.equals(nombreUsuario) && clave.equals(claveUsuario)) {
			
		    List<UsuarioDTO> LUsuarios = new ArrayList<UsuarioDTO>();
			LUsuarios = dao.leeUsuarios();
			mod.addAttribute("LUsuarios" , LUsuarios);//Pasamos la lista usuarios al objeto request para que pueda interactuar con el jsp.

			return "usuarios";
		}
		else
		{
			return "registro";
			//En registro habría que llamar al "Registrarse" que va a ser la parte de introducir al usuario en la BBDD.
		}
	}
	
	@Autowired
	private UsuarioInterfaz dao;
	
	@RequestMapping(value = "/Registrarse", method = RequestMethod.POST)
	public String Registrarse(HttpServletRequest req, Model mod) {
		
	    UsuarioDTO UsuarioNew = new UsuarioDTO();
	    UsuarioNew.setNombre(req.getParameter("nombre"));
	    UsuarioNew.setApellidos(req.getParameter("apellidos"));
	    UsuarioNew.setEmail(req.getParameter("email"));
	    dao.insertaUsuario(UsuarioNew);
	    
		mod.addAttribute("UsuarioNew" , UsuarioNew);
	    
	    return "ConfirmaRegistro";
		
	}
	
	@RequestMapping(value = "/ConfirmaRegistro", method = RequestMethod.POST)
	public String ConfirmaRegistro(HttpServletRequest req, Model mod) {
		// Se leen los parámetros
	    String nombre = req.getParameter("nombre");
	 	String apellidos = req.getParameter("apellidos");
	 	String email = req.getParameter("email");
	 	
	 	System.out.println(nombre + " " + apellidos + " "+ email);

	 	System.out.println(req.getSession(false)==null);
	 	if (req.getSession(false)==null){
	 		if(nombre==null) {
	 			return "accesoNulo";
	 		}else {
	 			System.out.println("Sesion no activa");
	 			/*nombre=req.getParameter("nombre");
	 			apellidos=req.getParameter("apellidos");
	 		    email=req.getParameter("email");*/
	 			req.setAttribute("nombre", nombre);
	 			req.setAttribute("apellidos",apellidos);
	 			req.setAttribute("email",email);
	 			HttpSession sesion=req.getSession(true);
	 			System.out.println("Sesion activada");
	 			sesion.setMaxInactiveInterval(60);
	 			sesion.setAttribute("nombre",nombre);
	 			sesion.setAttribute("apellidos",apellidos);
	 			sesion.setAttribute("email",email);
	 			//response.setContentType("text/html");
	 			System.out.println(nombre + " " + apellidos + " "+ email);
	 			
	 			return "informacionAcceso";
	 		}
 		}else {
				
			HttpSession sesion=req.getSession();
			System.out.println("Leemos datos de sesion");
			/*nombre=(String)sesion.getAttribute("nombre");
 			apellidos=(String)sesion.getAttribute("apellidos");
 			email=(String)sesion.getAttribute("email");*/
 			req.setAttribute("nombre", nombre);
 			req.setAttribute("apellidos",apellidos);
 			req.setAttribute("email",email);
			System.out.println(nombre + " " + apellidos + " "+ email);
 			
			return "informacionAcceso";

		} 
		
	}
	
	@RequestMapping(value = "/ConfirmaRegistro", method = RequestMethod.GET)
		public String ConfirmaRegistroGET(HttpServletRequest req, Model mod) {
			String nombre = req.getParameter("nombre");
		 	String apellidos = req.getParameter("apellidos");
		 	String email = req.getParameter("email");
		
		 	System.out.println(nombre + " " + apellidos + " "+ email);
		 	
			ConfirmaRegistro(req, mod);
			return "informacionAcceso";
		}
	
	
}

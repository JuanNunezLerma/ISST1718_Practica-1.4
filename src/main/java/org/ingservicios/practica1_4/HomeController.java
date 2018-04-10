package org.ingservicios.practica1_4;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import javax.servlet.http.HttpServletRequest;

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
			//En registro habría que llamar al "Servlet3" que va a ser la parte de introducir al usuario en la BBDD.
		}
	}
	
	@Autowired
	private UsuarioInterfaz dao;
	
	/*@RequestMapping(value = "/Servlet2", method = RequestMethod.POST)
	public String Servlet2(HttpServletRequest req, Model mod) {
		//Seguir por aqui, llamar al metodo de leeUsuarios del UsuarioDAOJdbc. y 
		//luego hay que poner el model pasandole la LUsuarios del usuarios.jsp para 
		//que llame a esta vista y muestre la tabla.
		
	    List<UsuarioDTO> LUsuarios = new ArrayList<UsuarioDTO>();
		LUsuarios = dao.leeUsuarios();
		mod.addAttribute("LUsuarios" , LUsuarios);//Pasamos la lista usuarios al objeto request para que pueda interactuar con el jsp.

		return "usuarios";
		
	}*/
	
	

	
}

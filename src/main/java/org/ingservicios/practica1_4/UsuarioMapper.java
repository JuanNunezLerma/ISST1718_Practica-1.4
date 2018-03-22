package org.ingservicios.practica1_4;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class UsuarioMapper implements RowMapper<UsuarioDTO>{
	
	public UsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setNombre(rs.getString("nombre"));
		usuario.setApellido(rs.getString("apellido"));
		usuario.setEmail(rs.getString("email"));
		return usuario;
	}
}
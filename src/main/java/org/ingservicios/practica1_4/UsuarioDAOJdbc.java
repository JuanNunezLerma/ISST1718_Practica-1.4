package org.ingservicios.practica1_4;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
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

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class UsuarioDAOJdbc implements UsuarioInterfaz {
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	//Métodos
	public void insertaUsuario(UsuarioDTO usuario){
		String sql = "insert into Usuario values(?,?,?)";
		Object[ ] parametros = {usuario.getNombre(),usuario.getApellido(),usuario.getEmail()};
		this.jdbcTemplate.update(sql,parametros);
	}
	
	public List<UsuarioDTO> leeUsuarios(){
		String sql = "select * from Usuario";
		UsuarioMapper mapper = new UsuarioMapper();
		List<UsuarioDTO> usuarios = this.jdbcTemplate.query(sql, mapper);
		return usuarios;
		}
	
	public UsuarioDTO buscaUsuario(int nombre){ //Devuelve el usuario buscado o null si no existe
		String sql = "select * from Usuario where nombre = ?";
		Object[ ] parametros = {nombre}; //Array de objetos
		UsuarioMapper mapper = new UsuarioMapper();
		List<UsuarioDTO> usuarios = this.jdbcTemplate.query(sql, parametros, mapper);
		if (usuarios.isEmpty()){
			return null;
		  }else 
			return usuarios.get(0);
		  }
}

package template.rest.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import template.rest.dao.AlunoDAO;
import template.rest.dao.Database;
import template.rest.dao.UsuarioDAO;
import template.rest.dto.Aluno;

public class GerenciadorAlunos {
	
	Database database = new Database();
	
	public ArrayList<Aluno> getAlunos()throws Exception {
		ArrayList<Aluno> alunosTemp = new ArrayList<Aluno>();
		try {
			Connection connection = database.getConnection();
			AlunoDAO alunoDAO = new AlunoDAO();
			alunosTemp = alunoDAO.getAlunos(connection);
			connection.close();

		}
		catch (Exception e) {
			throw e;
		}
		return alunosTemp;
	}
	
	public void cadastraAluno(String matricula, String nome, String cpf) {
		Connection connection = database.getConnection();
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.cadastraAluno(connection, matricula, nome, cpf);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void editarAluno(String id, String matricula, String nome, String cpf) {
		Connection connection = database.getConnection();
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.editarAluno(connection, id,matricula, nome, cpf);
		try {
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirAluno(String id) {
		Connection connection = database.getConnection();
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.excluirAluno(connection, id);
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String login(String usuario, String senha) {
		
		Connection connection = database.getConnection();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		boolean ehAutentico = usuarioDAO.verificaAutenticidadeUsuario(connection, usuario, senha);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.valueOf(ehAutentico);
		
	}
}

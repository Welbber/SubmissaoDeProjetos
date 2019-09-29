package template.rest.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import template.rest.dao.ProfessorDAO;
import template.rest.dao.Database;
import template.rest.dao.UsuarioDAO;
import template.rest.dto.Professor;
import template.rest.dto.Endereco;

public class GerenciadorDeProfessores {

	Database database = new Database();

	public ArrayList<Professor> getProfessores() throws Exception {
		ArrayList<Professor> professoresTemp = new ArrayList<Professor>();
		try {
			Connection connection = database.getConnection();
			ProfessorDAO professorDAO = new ProfessorDAO();
			professoresTemp = professorDAO.getProfessores(connection);
			connection.close();

		} catch (Exception e) {
			throw e;
		}
		return professoresTemp;
	}

	public void cadastrarProfessor(String matricula, String nome, Endereco endereco, String curso) {
		Connection connection = database.getConnection();
		ProfessorDAO professorDAO = new ProfessorDAO();
		professorDAO.cadastrarProfessor(connection, matricula, nome, endereco, curso);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarProfessor(String id, String matricula, String nome, Endereco endereco, String curso) {
		Connection connection = database.getConnection();
		ProfessorDAO professorDAO = new ProfessorDAO();
		professorDAO.editarProfessor(connection, id, matricula, nome, endereco, curso);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void excluirProfessor(String id) {
		Connection connection = database.getConnection();
		ProfessorDAO professorDAO = new ProfessorDAO();
		professorDAO.excluirProfessor(connection, id);
		try {
			connection.close();
		} catch (SQLException e) {
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

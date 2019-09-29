package template.rest.model;

import java.sql.Connection;
import java.sql.SQLException;

import template.rest.dao.AlunoProjetoDAO;
import template.rest.dao.Database;

public class GerenciadorAlunoProjeto {
	Database database = new Database();

	public void inserirAluno(String id, String idProjeto) {
		Connection connection = database.getConnection();

		AlunoProjetoDAO alunoProjetoDAO = new AlunoProjetoDAO();
		alunoProjetoDAO.inserirAlunoProjeto(connection, id, idProjeto);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void retirarAluno(String id) {
		Connection connection = database.getConnection();

		AlunoProjetoDAO alunoProjetoDAO = new AlunoProjetoDAO();
		alunoProjetoDAO.retirarAlunoProjeto(connection, id);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

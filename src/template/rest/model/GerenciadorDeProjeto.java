package template.rest.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import template.rest.dao.AlunoDAO;
import template.rest.dao.Database;
import template.rest.dao.ProjetoDAO;
import template.rest.dto.Projeto;

public class GerenciadorDeProjeto {

	Database database = new Database();

	public ArrayList<Projeto> getProjeto() throws Exception {
		ArrayList<Projeto> ProjetosTemp = new ArrayList<>();
		try {
			Connection connection = database.getConnection();
			ProjetoDAO projetoDAO = new ProjetoDAO();
			ProjetosTemp = projetoDAO.getProjetos(connection);
			connection.close();

		} catch (Exception e) {
			throw e;
		}
		return ProjetosTemp;
	}

	public void cadastrarProjeto(String titulo, String matriculaProfessor, ArrayList<String> matriculaAluno,
			String area, String resumo, String palavraChave1, String palavraChave2, String palavraChave3, String url) {
		Connection connection = database.getConnection();
		ProjetoDAO projetoDAO = new ProjetoDAO();
		projetoDAO.cadastrarProjeto(connection, titulo, matriculaProfessor, matriculaAluno, area, resumo, palavraChave1,
				palavraChave2, palavraChave3, url);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editarProjeto(String id, String titulo, String matriculaProfessor, String area, String resumo,
			String palavraChave1, String palavraChave2, String palavraChave3, String url) {
		Connection connection = database.getConnection();
		ProjetoDAO projetoDAO = new ProjetoDAO();
		projetoDAO.editarProjeto(connection, id, titulo, matriculaProfessor, area, resumo, palavraChave1, palavraChave2,
				palavraChave3, url);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void excluirProjeto(String id) {
		Connection connection = database.getConnection();
		AlunoDAO alunoDAO = new AlunoDAO();
		alunoDAO.excluirAluno(connection, id);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

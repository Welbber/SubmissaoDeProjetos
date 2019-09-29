package template.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import jersey.repackaged.com.google.common.util.concurrent.ExecutionError;
import template.rest.dto.Aluno;
import template.rest.dto.AlunoProjeto;
import template.rest.dto.Professor;
import template.rest.dto.Projeto;

public class ProjetoDAO {

	public ArrayList<Projeto> getProjetos(Connection connection) throws Exception {

		ArrayList<Projeto> projetos = new ArrayList<>();

		try {
			ArrayList<AlunoProjeto> alunos = new AlunoProjetoDAO().getAlunoProjeto(connection);

			PreparedStatement ps = connection.prepareStatement("SELECT pr.id_projeto, pe.matricula, pr.titulo, "
					+ "pr.resumo, pr.area, pr.palavra_chave1, palavra_chave2, palavra_chave3 "
					+ "FROM controle_academico.projeto AS pr INNER JOIN controle_academico.pessoa "
					+ "AS pe ON pe.id_pessoa = pr.id_professor;");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Projeto projeto = new Projeto();
				ArrayList<AlunoProjeto> alunoProjeto = new ArrayList<>();
				projeto.setId(rs.getString("id_projeto"));
				projeto.setMatriculaProfessor(rs.getString("matricula"));
				projeto.setTitulo(rs.getString("titulo"));
				projeto.setResumo(rs.getString("resumo"));
				projeto.setArea(rs.getString("area"));
				projeto.setPalavraChave1(rs.getString("palavra_chave1"));
				projeto.setPalavraChave2(rs.getString("palavra_chave2"));
				projeto.setPalavraChave3(rs.getString("palavra_chave3"));
				for (AlunoProjeto aluno : alunos) {

					if (aluno.getId_projeto().equals(projeto.getId())) {
						alunoProjeto.add(aluno);
					}
				}
				projeto.setAlunos(alunoProjeto);
				projetos.add(projeto);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}
		return projetos;
	}

	public void cadastrarProjeto(Connection connection, String titulo, String matriculaProfessor,
			ArrayList<String> matriculaAluno, String area, String resumo, String palavraChave1, String palavraChave2,
			String palavraChave3, String url){

		AlunoDAO alunoDAO = new AlunoDAO();
		ProfessorDAO professorDAO = new ProfessorDAO();

		ArrayList<Aluno> alunos = alunoDAO.getAlunos(connection);
		ArrayList<Professor> professores = professorDAO.getProfessores(connection);

		String queryAluno = "";
		String idProfessor = "";

		for (Aluno aluno : alunos) {
			for (String matricula : matriculaAluno) {
				if (matricula.equals(aluno.getMatricula())) {
					queryAluno += "INSERT INTO controle_academico.aluno_projeto (id_aluno, id_projeto) VALUES ("
							+ aluno.getId() + ", (SELECT last_insert_id())); ";
				}
			}
		}
		for (Professor professor : professores) {
			if (professor.getMatricula().equals(matriculaProfessor)) {
				idProfessor = professor.getId();
			}
		}
		try {
			Statement stm = (Statement) connection.createStatement();

			String query = "INSERT INTO controle_academico.projeto (id_professor,titulo, area, resumo,"
					+ " palavra_chave1, palavra_chave2, palavra_chave3, url_documento) VALUES (" + idProfessor + ", '"
					+ titulo + "', '" + area + "', '" + resumo + "', '" + palavraChave1 + "', '" + palavraChave2
					+ "', '" + palavraChave3 + "', '" + url + "'); ";
			query += queryAluno;
			stm.execute(query);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void editarProjeto(Connection connection, String id, String titulo, String matriculaProfessor, String area,
			String resumo, String palavraChave1, String palavraChave2, String palavraChave3, String url) {
		try {
			Statement stm = (Statement) connection.createStatement();

			String query = "UPDATE controle_academico.projeto SET titulo= '" + titulo + "', matricula_professor='"
					+ matriculaProfessor + "', area = '" + area + "', resumo = '" + resumo + "', palavra_chave1 = '"
					+ palavraChave1 + "', palavra_chave2 = '" + palavraChave2 + "', palavra_chave3 = '" + palavraChave3
					+ "' WHERE id_projeto =" + id + ";";
			stm.execute(query);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void excluirProjeto(Connection connection, String id) {
		try {
			Statement stm = (Statement) connection.createStatement();

			String query = "DELETE FROM controle_academico.pessoa WHERE id_pessoa ="+id+";";
			stm.execute(query);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}

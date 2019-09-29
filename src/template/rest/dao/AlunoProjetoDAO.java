package template.rest.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import template.rest.dto.AlunoProjeto;

public class AlunoProjetoDAO {
	public ArrayList<AlunoProjeto> getAlunoProjeto(Connection connection) {

		ArrayList<AlunoProjeto> alunos = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT p.nome, p.matricula, ap.id_projeto" + " FROM controle_academico.pessoa AS p"
							+ " RIGHT JOIN controle_academico.aluno_projeto AS ap ON ap.id_aluno = p.id_pessoa");
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AlunoProjeto aluno = new AlunoProjeto();
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setId_projeto(rs.getString("id_projeto"));
				alunos.add(aluno);
			}
			return alunos;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return alunos;
	}

	public void inserirAlunoProjeto(Connection connection, String id, String idProjeto) {
		try {
			Statement stm = connection.createStatement();
			String query = "UPDATE controle_academico.aluno_projeto SET id_aluno =" + id + ", id_projeto=" + idProjeto
					+ ";";
			stm.execute(query);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void retirarAlunoProjeto(Connection connection, String id) {
		try {
			Statement stm = connection.createStatement();
			String query = "DELETE FROM controle_academico.aluno_projeto WHERE id_aluno =" + id + ";";
			stm.execute(query);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}

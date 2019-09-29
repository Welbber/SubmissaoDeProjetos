package template.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import template.rest.dto.Aluno;
import template.rest.dto.Endereco;

public class AlunoDAO {

	public ArrayList<Aluno> getAlunos(Connection connection){

		ArrayList<Aluno> alunos = new ArrayList<>();
		Endereco endereco = new Endereco();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT p.id_pessoa, p.matricula, p.nome, "
					+ "a.cpf, e.rua, e.numero, e.cep, e.cidade, e.estado, e.pais, a.cpf, p.curso "
					+ "FROM controle_academico.pessoa AS p "
					+ "INNER JOIN controle_academico.aluno AS a ON a.id_aluno = p.id_pessoa "
					+ "INNER JOIN controle_academico.endereco AS e ON p.id_pessoa = e.id_pessoa;");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getString("id_pessoa"));
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setNome(rs.getString("nome"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setCEP(rs.getString("cep"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setPais(rs.getString("pais"));
				aluno.setEndereco(endereco);
				aluno.setCpf(rs.getString("cpf"));
				aluno.setCurso(rs.getString("curso"));
				alunos.add(aluno);
			}
			return alunos;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return alunos;
	}
	
	public void cadastraAluno(Connection connection, String matricula, String nome, Endereco endereco, String cpf,
			String curso) {

		try {
			Statement stm = connection.createStatement();

			String query = "INSERT INTO controle_academico.pessoa (matricula, nome, curso) VALUES ( '" + matricula
					+ "', '" + nome + "', '" + curso + "'); "
					+ "INSERT INTO controle_academico.aluno (cpf, id_aluno) VALUES ('" + cpf
					+ "', (SELECT last_insert_id())); INSERT INTO controle_academico.endereco"
					+ " (rua, numero, cep, cidade, estado, pais, id_pessoa) VALUES ( '" + endereco.getRua() + "', '"
					+ endereco.getNumero() + "', '" + endereco.getCEP() + "', '" + endereco.getCidade() + "', '"
					+ endereco.getEstado() + "', '" + endereco.getPais() + "', (SELECT last_insert_id()));";
			System.out.println(query);
			stm.execute(query);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void editarAluno(Connection connection, String id, String matricula, String nome, Endereco endereco,
			String cpf, String curso) {
		try {
			Statement stm = connection.createStatement();
			String query = "UPDATE controle_academico.pessoa SET matricula = '" + matricula + "', nome = '" + nome
					+ "' WHERE id_pessoa = " + id + "; UPDATE controle_academico.endereco SET rua = '"
					+ endereco.getRua() + "', numero = '" + endereco.getNumero() + "', cep = '" + endereco.getCEP()
					+ "', cidade = '" + endereco.getCidade() + "', estado = '" + endereco.getEstado() + "', pais = '"
					+ endereco.getPais() + "' WHERE id_pessoa=\"" + id
					+ "\"; UPDATE controle_academico.aluno SET cpf = '" + cpf + "' WHERE id_aluno = " + id + ";";
			stm.execute(query);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public void excluirAluno(Connection connection, String id) {
		try {
			Statement stm = connection.createStatement();
			String query = "DELETE FROM controle_academico.pessoa WHERE id_pessoa = " + id
					+ "; DELETE FROM controle_academico.aluno WHERE id_aluno = " + id 
					+ "; DELETE FROM controle_academico.endereco WHERE id_pessoa = " + id
					+ "; DELETE FROM controle_academico.aluno_projeto WHERE id_aluno = "+id+";";
			stm.execute(query);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
package template.rest.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import template.rest.dto.Aluno;

public class AlunoDAO {
	
	public ArrayList<Aluno> getAlunos(Connection connection) throws Exception {
		
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT id, matricula, nome, cpf FROM controle_academico.alunos");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Aluno aluno = new Aluno();
				aluno.setId(rs.getString("id"));
				aluno.setMatricula(rs.getString("matricula"));
				aluno.setNome(rs.getString("nome"));
				aluno.setCpf(rs.getString("cpf"));
				alunos.add(aluno);
			}
			return alunos;
		}
		catch(Exception e) {
			throw e;
		}
	}
	
	public void cadastraAluno(Connection connection, String matricula, String nome, String cpf){
		
		try {
			Statement stm = connection.createStatement();
			
			String query = "INSERT INTO controle_academico.alunos (matricula, nome, cpf) VALUES ( '"
					+ matricula + "', '" + nome + "', '" + cpf + "');";
			stm.execute(query);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public void editarAluno(Connection connection, String id, String matricula, String nome, String cpf) {
		try {
			Statement stm = connection.createStatement();
			String query = "UPDATE controle_academico.alunos SET matricula = \""+matricula+"\", nome = \""+nome+"\", cpf = \""+cpf+"\""+
					" WHERE id=\""+id+"\";";
			stm.execute(query);
		} catch(Exception e) {
			e.getStackTrace();
		}
	}
	public void excluirAluno(Connection connection, String id) {
		try {
			Statement stm = connection.createStatement();
			String query = "DELETE FROM controle_academico.alunos WHERE id= \""+id+"\";";
			stm.execute(query);
		} catch(Exception e) {
			e.getStackTrace();
		}
	}
	
}


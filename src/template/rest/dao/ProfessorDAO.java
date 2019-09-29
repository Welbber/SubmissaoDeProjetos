package template.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import template.rest.dto.Professor;
import template.rest.dto.Professor;
import template.rest.dto.Endereco;
import template.rest.dto.Professor;

public class ProfessorDAO {
	public ArrayList<Professor> getProfessores(Connection connection) {
		
		ArrayList<Professor> professores = new ArrayList<Professor>();
		Endereco endereco = new Endereco();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT p.id_pessoa, p.matricula, p.nome, "
					+ "e.rua, e.numero, e.cep, e.cidade, e.estado, e.pais, p.curso " + 
					"FROM controle_academico.pessoa AS p " + 
					"INNER JOIN controle_academico.professor AS a ON a.id_professor = p.id_pessoa " + 
					"INNER JOIN controle_academico.endereco AS e ON p.id_pessoa = e.id_pessoa;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Professor professor = new Professor();
				professor.setId(rs.getString("id_pessoa"));
				professor.setMatricula(rs.getString("matricula"));
				professor.setNome(rs.getString("nome"));
				endereco.setRua(rs.getString("rua"));
				endereco.setNumero(rs.getString("numero"));
				endereco.setCEP(rs.getString("cep"));
				endereco.setCidade(rs.getString("cidade"));
				endereco.setEstado(rs.getString("estado"));
				endereco.setPais(rs.getString("pais"));
				professor.setEndereco(endereco);
				professor.setCurso(rs.getString("curso"));
				professores.add(professor);
			}
			return professores;
		}
		catch(Exception e) {
			e.getStackTrace();
		}
		return professores;
	}
	
	public void cadastrarProfessor(Connection connection, String matricula, String nome, Endereco endereco, String curso){
		
		try {
			Statement stm = connection.createStatement();

			String query = "INSERT INTO controle_academico.pessoa (matricula, nome, curso) VALUES ( '"
					+ matricula + "', '" + nome + "', '" + curso + "'); "+
					"INSERT INTO controle_academico.professor (id_professor) VALUES ("
					+ "(SELECT last_insert_id())); INSERT INTO controle_academico.endereco"
					+" (rua, numero, cep, cidade, estado, pais, id_pessoa) VALUES ( '"
					+ endereco.getRua()+"', '"+endereco.getNumero()+"', '"+endereco.getCEP()+"', '"
					+endereco.getCidade()+"', '"+endereco.getEstado()+"', '"+endereco.getPais()+
					"', (SELECT last_insert_id()));";

			stm.execute(query);
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public void editarProfessor(Connection connection, String id, String matricula, String nome, Endereco endereco, String curso) {
		try {
			Statement stm = connection.createStatement();
			String query = "UPDATE controle_academico.pessoa SET matricula = '"+matricula+"', nome = '"+nome+"' WHERE id_pessoa = "+id
			+ "; UPDATE controle_academico.endereco SET rua = '"+endereco.getRua()+"', numero = '"+endereco.getNumero()
			+ "', cep = '"+endereco.getCEP()+"', cidade = '"+endereco.getCidade()+"', estado = '"+endereco.getEstado()
			+"', pais = '"+endereco.getPais()+"' WHERE id_pessoa=\""+id+"\";";
			stm.execute(query);
		} catch(Exception e) {
			e.getStackTrace();
		}
	}
	public void excluirProfessor(Connection connection, String id) {
		try {
			Statement stm = connection.createStatement();
			String query = "DELETE FROM controle_academico.pessoa WHERE id_pessoa = "+id+";"
					+ " DELETE FROM controle_academico.professor WHERE id_professor = "+id+";"
					+" DEELETE FROM controle_academico.endereco WHERE id_pessoa = "+id+";";
			stm.execute(query);
		} catch(Exception e) {
			e.getStackTrace();
		}
	}
	
}
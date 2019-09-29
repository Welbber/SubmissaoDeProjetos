package template.rest.dto;

public class AlunoProjeto {
	private String id;
	private String nome;
	private String matricula;
	private String id_projeto;

	public String getID() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId_projeto() {
		return id_projeto;
	}

	public void setId_projeto(String id_projeto) {
		this.id_projeto = id_projeto;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}

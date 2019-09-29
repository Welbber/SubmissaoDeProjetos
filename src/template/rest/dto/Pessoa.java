package template.rest.dto;

public abstract class Pessoa {
	private String id;
	private String matricula;
	private String nome;
	private Endereco endereco;
	private String curso;

	public String getId() {
		return id;
	}

	public void setId(String string) {
		this.id = string;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
}

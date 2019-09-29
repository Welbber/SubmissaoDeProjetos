package template.rest.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "aluno")

public class Aluno extends Pessoa {

	private String cpf;

	public Aluno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}

package template.rest;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import template.rest.dto.Aluno;
import template.rest.dto.Professor;

@XmlRootElement(name = "professores")
public class ResponseListProfessor {

	private ArrayList<Professor> lista;

	public ArrayList<Professor> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Professor> lista) {
		this.lista = lista;
	}

}

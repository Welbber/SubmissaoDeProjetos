package template.rest;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import template.rest.dto.Projeto;

@XmlRootElement(name = "professores")
public class ResponseListProjeto {

	private ArrayList<Projeto> lista;

	public ArrayList<Projeto> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Projeto> lista) {
		this.lista = lista;
	}

}

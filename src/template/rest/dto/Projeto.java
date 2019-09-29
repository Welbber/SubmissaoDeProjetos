package template.rest.dto;

import java.util.ArrayList;

public class Projeto {
	private String id;
	private String titulo;
	private String matriculaProfessor;
	private ArrayList<AlunoProjeto> alunos;
	private ArrayList<String> matriculaAlunos;
	private String area;
	private String resumo;
	private String palavraChave1;
	private String palavraChave2;
	private String palavraChave3;
	private String url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMatriculaProfessor() {
		return matriculaProfessor;
	}
	public void setMatriculaProfessor(String matriculaProfessor) {
		this.matriculaProfessor = matriculaProfessor;
	}
	public ArrayList<AlunoProjeto> getAlunos() {
		return alunos;
	}
	public void setAlunos(ArrayList<AlunoProjeto> alunos) {
		this.alunos = alunos;
	}
	public ArrayList<String> getMatriculaAlunos() {
		return matriculaAlunos;
	}
	public void setId(ArrayList<String> matriculaAlunos) {
		this.matriculaAlunos = matriculaAlunos;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public String getPalavraChave1() {
		return palavraChave1;
	}
	public void setPalavraChave1(String palavraChave1) {
		this.palavraChave1 = palavraChave1;
	}
	public String getPalavraChave2() {
		return palavraChave2;
	}
	public void setPalavraChave2(String palavraChave2) {
		this.palavraChave2 = palavraChave2;
	}
	public String getPalavraChave3() {
		return palavraChave3;
	}
	public void setPalavraChave3(String palavraChave3) {
		this.palavraChave3 = palavraChave3;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

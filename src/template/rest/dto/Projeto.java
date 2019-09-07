package template.rest.dto;

public class Projeto {
	private String id;
	private String tituto;
	private Professor professor;
	private String area;
	private String resumo;
	private String PalavraChave1;
	private String palavraChave2;
	private String palavraChave3;
	private String url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTituto() {
		return tituto;
	}
	public void setTituto(String tituto) {
		this.tituto = tituto;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
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
		return PalavraChave1;
	}
	public void setPalavraChave1(String palavraChave1) {
		PalavraChave1 = palavraChave1;
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

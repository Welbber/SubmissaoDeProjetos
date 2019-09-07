package template.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.MultiPart;

import template.rest.dto.Aluno;
import template.rest.model.GerenciadorAlunos;



@Path("/ws")
public class ManagerService {
	
	GerenciadorAlunos gerenciadorAlunos = new GerenciadorAlunos();
		
	@GET
	@Path("/alunos/xml")
	@Produces(MediaType.APPLICATION_XML)
	public ResponseList getAlunosXML() {
		
		ResponseList listaAlunos = new ResponseList();
		try {
			listaAlunos.setLista(gerenciadorAlunos.getAlunos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAlunos;
	}
		
	@POST
	@Path("/cadastraAluno")
	@Consumes(MediaType.APPLICATION_XML)
	public Response cadastraAluno(Aluno alunoParametro) {
		
		gerenciadorAlunos.cadastraAluno(alunoParametro.getMatricula(),alunoParametro.getNome(), alunoParametro.getCpf());
		String result = "Aluno cadastrado : " + alunoParametro.getNome();
		return Response.status(201).entity(result).build();
		
	}
	
	@PUT
	@Path("/editarAluno")
	@Consumes(MediaType.APPLICATION_XML)
	public Response editarAluno(Aluno alunoParametro) {
		gerenciadorAlunos.editarAluno(alunoParametro.getId(), alunoParametro.getMatricula(),alunoParametro.getNome(), alunoParametro.getCpf());
		
		String result = "Aluno editado : "+alunoParametro.getNome();
		return Response.status(200).entity(result).build();
	}
	@DELETE
	@Path("/excluirAluno")
	@Consumes(MediaType.APPLICATION_XML)
	public Response excluirAluno(Aluno alunoParametro) {
		gerenciadorAlunos.excluirAluno(alunoParametro.getId());
		String result = "Aluno excluido : "+alunoParametro.getNome();
		return Response.status(200).entity(result).build();
	}
	//TODO - ROTEIRO
	//1 - Método getAlunosJSON para retornar a lista de alunos em formato JSON
	//2 - Método cadastraAluno para cadastrar um aluno na base de dados (Atenção para o uso do método HTTP apropriado)
	//3 - Método atualizaAluno para atualizar um aluno na base de dados(Atenção para o uso do método HTTP apropriado)
	//4 - Método removeAluno para remover um aluno da base de dados(Atenção para o uso do método HTTP apropriado)
	//5 - Executar todos os métodos consumindo dados em XML e em JSON
	
	
	@POST
	@Path("/login")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(MultiPart data) {
		return gerenciadorAlunos.login(data.getBodyParts().get(0).getEntityAs(String.class), data.getBodyParts().get(1).getEntityAs(String.class));
	}
	
	

}
package template.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPart;

import template.rest.dto.Aluno;
import template.rest.dto.AlunoProjeto;
import template.rest.dto.Professor;
import template.rest.dto.Projeto;
import template.rest.model.GerenciadorAlunoProjeto;
import template.rest.model.GerenciadorDeAlunos;
import template.rest.model.GerenciadorDeProfessores;
import template.rest.model.GerenciadorDeProjeto;

@Path("/ws")
public class ManagerService {

	GerenciadorDeAlunos gerenciadorAlunos = new GerenciadorDeAlunos();
	GerenciadorDeProfessores gerenciadorProfessores = new GerenciadorDeProfessores();
	GerenciadorDeProjeto gerenciadorProjetos = new GerenciadorDeProjeto();
	GerenciadorAlunoProjeto gerenciadorAlunoProjeto = new GerenciadorAlunoProjeto();
	private String uploadFileLocation;

	@GET
	@Path("/alunos/JSON")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseListAluno getAlunosJSON() {

		ResponseListAluno listaAlunos = new ResponseListAluno();
		try {
			listaAlunos.setLista(gerenciadorAlunos.getAlunos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAlunos;
	}

	@POST
	@Path("/cadastrarAluno")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarAluno(Aluno alunoParametro) {
		gerenciadorAlunos.cadastrarAluno(alunoParametro.getMatricula(), alunoParametro.getNome(),
				alunoParametro.getEndereco(), alunoParametro.getCpf(), alunoParametro.getCurso());
		String result = "Aluno cadastrado : " + alunoParametro.getNome();
		return Response.status(201).entity(result).build();

	}

	@PUT
	@Path("/editarAluno")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editarAluno(Aluno alunoParametro) {
		gerenciadorAlunos.editarAluno(alunoParametro.getId(), alunoParametro.getMatricula(), alunoParametro.getNome(),
				alunoParametro.getEndereco(), alunoParametro.getCpf(), alunoParametro.getCurso());

		String result = "Aluno editado : " + alunoParametro.getNome();
		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("/excluirAluno")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response excluirAluno(Aluno alunoParametro) {
		gerenciadorAlunos.excluirAluno(alunoParametro.getId());
		String result = "Aluno excluido : " + alunoParametro.getNome();
		return Response.status(200).entity(result).build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public String login(MultiPart data) {
		return gerenciadorAlunos.login(data.getBodyParts().get(0).getEntityAs(String.class),
				data.getBodyParts().get(1).getEntityAs(String.class));
	}

	// ----------------------------------------
	@GET
	@Path("/professores/JSON")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseListProfessor getProfessoresJSON() {

		ResponseListProfessor listaProfessores = new ResponseListProfessor();
		try {
			listaProfessores.setLista(gerenciadorProfessores.getProfessores());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProfessores;
	}

	@POST
	@Path("/cadastrarProfessor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarAluno(Professor professorParametro) {

		gerenciadorProfessores.cadastrarProfessor(professorParametro.getMatricula(), professorParametro.getNome(),
				professorParametro.getEndereco(), professorParametro.getCurso());
		String result = "Professor cadastrado : " + professorParametro.getNome();
		return Response.status(201).entity(result).build();

	}

	@PUT
	@Path("/editarProfessor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editarAluno(Professor professorParametro) {
		gerenciadorProfessores.editarProfessor(professorParametro.getId(), professorParametro.getMatricula(),
				professorParametro.getNome(), professorParametro.getEndereco(), professorParametro.getCurso());

		String result = "Aluno editado : " + professorParametro.getNome();
		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("/excluirProfessor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response excluirAluno(Professor professorParametro) {
		gerenciadorProfessores.excluirProfessor(professorParametro.getId());
		String result = "Aluno excluido : " + professorParametro.getNome();
		return Response.status(200).entity(result).build();
	}

	// ---------------------------------------------------------

	@GET
	@Path("/projetos/JSON")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseListProjeto getProjetosJSON() {

		ResponseListProjeto listaProjetos = new ResponseListProjeto();
		try {
			listaProjetos.setLista(gerenciadorProjetos.getProjeto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaProjetos;
	}

	private void saveToDisk(InputStream uploadInputStream, FormDataContentDisposition fileDetail) {

		uploadFileLocation = "E:/projeto/" + fileDetail.getFileName();
		try {
			OutputStream out = new FileOutputStream(new File(uploadFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadFileLocation));
			while ((read = uploadInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("/uploadFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String uploadFile(@FormDataParam("file") InputStream uploadInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		saveToDisk(uploadInputStream, fileDetail);
		return "File uploaded successfully!!";
	}

	@POST
	@Path("/cadastrarProjeto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarProjeto(Projeto projetoParametro) throws Exception {
		gerenciadorProjetos.cadastrarProjeto(projetoParametro.getTitulo(), projetoParametro.getMatriculaProfessor(),
				projetoParametro.getMatriculaAlunos(), projetoParametro.getArea(), projetoParametro.getResumo(),
				projetoParametro.getPalavraChave1(), projetoParametro.getPalavraChave2(),
				projetoParametro.getPalavraChave3(), uploadFileLocation);
		String result = "Professor cadastrado : " + projetoParametro.getTitulo();
		return Response.status(201).entity(result).build();
	}

	@PUT
	@Path("/editarProjeto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editarAluno(Projeto projetoParametro) {
		gerenciadorProjetos.editarProjeto(projetoParametro.getId(), projetoParametro.getTitulo(),
				projetoParametro.getMatriculaProfessor(), projetoParametro.getArea(), projetoParametro.getResumo(),
				projetoParametro.getPalavraChave1(), projetoParametro.getPalavraChave2(),
				projetoParametro.getPalavraChave3(), uploadFileLocation);
		String result = "Aluno editado : " + projetoParametro.getTitulo();
		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("/excluirProjeto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response excluirProjeto(Projeto projetoParametro) {
		gerenciadorProjetos.excluirProjeto(projetoParametro.getId());
		String result = "Aluno excluido : " + projetoParametro.getTitulo();
		return Response.status(200).entity(result).build();
	}

	// -----------------------------------------------------------------
	@PUT
	@Path("/adicionarAlunoProjeto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionarAlunoProjeto(AlunoProjeto alunoProjetoParametro) {
		gerenciadorAlunoProjeto.inserirAluno(alunoProjetoParametro.getID(), alunoProjetoParametro.getId_projeto());
		String result = "Aluno adicionado : " + alunoProjetoParametro.getID();
		return Response.status(200).entity(result).build();
	}

	@DELETE
	@Path("/retirarAluno")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response retirarAlunoProjeto(AlunoProjeto alunoProjetoParametro) {
		gerenciadorAlunoProjeto.retirarAluno(alunoProjetoParametro.getID());
		String result = "Aluno excluido : " + alunoProjetoParametro.getID();
		return Response.status(200).entity(result).build();
	}
}
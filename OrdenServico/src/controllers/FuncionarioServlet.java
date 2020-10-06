package controllers; // Nome do pacote referente ao código abaixo ex: controllers

import java.io.IOException; //
import java.util.ArrayList; // importação do ArrayList
import javax.servlet.ServletException; //
import javax.servlet.annotation.WebServlet; //
import javax.servlet.http.HttpServlet; //
import javax.servlet.http.HttpServletRequest; //
import javax.servlet.http.HttpServletResponse; //

import domains.Funcionario; // importação da classe domains ou modelo
import domains.dao.FuncionarioDAO; // importação da classe DAO

@WebServlet("/funcionarios")
public class FuncionarioServlet extends HttpServlet { //

	private static final long serialVersionUID = 1L; // Controle de versionamento do código
	private Funcionario funcionario;
	private static FuncionarioDAO fd = new FuncionarioDAO();
	private static ArrayList<Funcionario> funcionarios = fd.open();// Abrindo arquivo Funcionário e carregando numa
																	// lista

	public static ArrayList<Funcionario> getFuncionarios() { // retorna a lista funcionários sem precisar instânciar
		return funcionarios;
	}

	public static int getAutoId() {
		if (FuncionarioServlet.funcionarios.isEmpty())
			return 1;
		else
			return FuncionarioServlet.funcionarios.get(FuncionarioServlet.funcionarios.size() - 1).getId() + 1;
	}

	@Override // CREATE
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mensagem = "";// Variável criada em branco
		funcionario = new Funcionario();// Estânciando o objeto funcionario;
		String nome = req.getParameter("nome");
		String nascimento = req.getParameter("nascimento");
		String endereco = req.getParameter("endereco");
		String especialidade = req.getParameter("especialidade");
		String[] comp = req.getParameter("competencias").split(",");
		ArrayList<String> competencias = new ArrayList<>();
		for (String c : comp) {
			competencias.add(c);
		}
		String periodo = req.getParameter("periodo");
		String valorHora = req.getParameter("valorHora");
		// Preecher o modelo funcionario, com os dados recebidos
		funcionario.setId(getAutoId());
		funcionario.setNome(nome);
		funcionario.setNascimento(nascimento);
		funcionario.setEndereco(endereco);
		funcionario.setEspecialidade(especialidade);
		funcionario.setCompetencias(competencias);
		funcionario.setPeriodo(periodo);
		funcionario.setValorHora(Double.parseDouble(valorHora));
		funcionarios.add(funcionario);
		if (fd.save(funcionarios)) {
			mensagem = "Dados enviados para arquivo CSV";
		} else {
			mensagem = "Erro ao enviar para o arquivo";
		}
		resp.sendRedirect("funcionario.jsp?mensagem=" + mensagem);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		funcionario = new Funcionario(Integer.parseInt(req.getParameter("id")));
		funcionarios.remove(funcionarios.indexOf(funcionario));
		if (!fd.save(funcionarios)) {
			resp.sendRedirect("funcionario.jsp?mensagem=Erro ao excluir");
		}
	}

	@Override // UPdate
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		funcionario = new Funcionario();// Estânciando o objeto funcionario;
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String nascimento = req.getParameter("nascimento");
		String endereco = req.getParameter("endereco");
		String especialidade = req.getParameter("especialidade");
		String[] comp = req.getParameter("competencias").split(",");
		ArrayList<String> competencias = new ArrayList<>();
		for (String c : comp) {
			competencias.add(c);
		}
		String periodo = req.getParameter("periodo");
		String valorHora = req.getParameter("valorhora");
		// Preecher o modelo funcionario, com os dados recebidos
		funcionario.setId(Integer.parseInt(id));
		funcionario.setNome(nome);
		funcionario.setNascimento(nascimento);
		funcionario.setEndereco(endereco);
		funcionario.setEspecialidade(especialidade);
		funcionario.setCompetencias(competencias);
		funcionario.setPeriodo(periodo);
		funcionario.setValorHora(Double.parseDouble(valorHora));
		funcionarios.set(funcionarios.indexOf(funcionario), funcionario);
		if (!fd.save(funcionarios)) {
			resp.sendRedirect("funcionario.jsp?mensagem=Erro ao atualizar");
		}

	}
}

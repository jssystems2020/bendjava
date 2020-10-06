<%@page import="controllers.FuncionarioServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domains.Funcionario"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Funcionários</title>
</head>
<body>
	<!-- CREATE -->
	<form method="POST" action="funcionarios">
		<input type="text" size="10" name="id" placeholder="ID" /> <input
			type="text" size="10" name="nome" placeholder="NOME" /> <input
			type="text" size="10" name="nascimento" placeholder="NASCIMENTO" />
		<input type="text" size="10" name="endereco" placeholder="ENDERECO" />
		<input type="text" size="12" name="especialidade"
			placeholder="ESPECIALIDADE" /> <input type="text" size="12"
			name="competencias" placeholder="COMPETENCIAS" /> <input type="text"
			size="6" name="periodo" placeholder="PERIODO" /> <input type="text"
			size="9" name="valorHora" placeholder="VALORHORA" /> <input
			type="submit" value="Enviar">
	</form>
	<div id="msg">
		<%
			if (request.getParameter("mensagem") != null) {
				out.print(request.getParameter("mensagem"));
			}
		%>
	</div>
	<!-- READ -->
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Nascimento</th>
				<th>Endereco</th>
				<th>Especialidade</th>
				<th>Competencias</th>
				<th>Periodo</th>
				<th>ValorHora</th>
				<th>Ações</th>
			</tr>
		</thead>
		<!-- DELETE / UPDATE -->
		<tbody>
			<%
				for (Funcionario f : FuncionarioServlet.getFuncionarios()) {
					out.print("<tr>");
					out.print(f.toHTML());
					out.print("<td>");
					out.print("<button onclick='del(this)'>Excluir</button>");
					out.print("<button onclick='update(this)'>Alterar</button>");
					out.print("</td>");
					out.print("</tr>");
				}
			%>
		</tbody>
	</table>
	<script>
		function del(e) {// Serve para enviar um Id para o Servlet
			let id = e.parentNode.parentNode.cells[0].innerText;
			if (window.confirm("Confirma exclusão do Id " + id)) {
				let xm = new XMLHttpRequest();
				xm.addEventListener("readystatechange", function() {
					if (this.readyState === this.DONE) {
						window.location.reload();
					}
				});
				let url = "funcionarios?id=" + id;
				xm.open("DELETE", url);
				xm.send();
			}
		}
		function update(e) {
			e.parentNode.parentNode.cells[1].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[2].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[3].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[4].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[5].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[6].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[7].setAttribute("contenteditable",
					"true");
			e.parentNode.parentNode.cells[8].innerHTML = "<button onclick='send(this)'>Concluir</button>";
		}
		function send(e) {
			let id = e.parentNode.parentNode.cells[0].innerText;
			let nome = e.parentNode.parentNode.cells[1].innerText;
			let nascimento = e.parentNode.parentNode.cells[2].innerText;
			let endereco = e.parentNode.parentNode.cells[3].innerText;
			let especialidade = e.parentNode.parentNode.cells[4].innerText;
			let competencias = e.parentNode.parentNode.cells[5].innerText;
			let periodo = e.parentNode.parentNode.cells[6].innerText;
			let valorhora = e.parentNode.parentNode.cells[7].innerText;
			let url = "funcionarios?id=" + id + "&nome=" + nome
					+ "&nascimento=" + nascimento + "&endereco=" + endereco
					+ "&especialidade=" + especialidade + "&competencias="
					+ competencias + "&periodo=" + periodo + "&valorhora="
					+ valorhora;
			let xm = new XMLHttpRequest();
			xm.addEventListener("readystatechange", function() {
				if (this.readyState === this.DONE) {
					window.location.reload();
				}
			});
			xm.open("PUT", url);
			xm.send();
		}
	</script>
</body>
</html>
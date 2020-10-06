package domains;

public class OrdenServico {
	int id;
	String dataAgendamento;
	String dataExecucao;
	int totalHoras;
	String endereco;
	Funcionario funcionario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDataAgendamento() {
		return dataAgendamento;
	}
	public void setDataAgendamento(String dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}
	public String getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(String dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public int getTotalHoras() {
		return totalHoras;
	}
	public void setTotalHoras(int totalHoras) {
		this.totalHoras = totalHoras;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public String toCSV() {
		return   id + ";" + dataAgendamento + ";" + dataExecucao
				+ ";" + totalHoras + ";" + endereco + ";" + funcionario.getId() + "\r\n";
	}
	
	public String toHTML() {
		return  "<td>" + id + "</td><td>" + dataAgendamento + "<td></td>" + dataExecucao
				+ "</td><td>" + totalHoras + "</td><td>" + endereco + "</td><td>" + funcionario.getId() + "</td>";
	}

}

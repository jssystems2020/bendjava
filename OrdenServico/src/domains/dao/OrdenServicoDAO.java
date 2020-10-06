package domains.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import domains.Funcionario;
import domains.OrdenServico;

public class OrdenServicoDAO {
	private OrdenServico ordenServico;
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "D:\\WORKSPACE 2DES PWBE\\OrdenServico\\bd\\os.csv";
	private String[] campos;
	
	public boolean save(ArrayList<OrdenServico> ordensServico) {
		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(arquivo,false));
			for(OrdenServico o: ordensServico) {
				bw.write(o.toCSV());
			}
			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println("Erro ao salvar "+e);
		}
		return retorno;
	}
	
	public ArrayList<OrdenServico> open(){
		ArrayList<OrdenServico> ordensServico = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha = br.readLine();
			while(linha != null) {
				campos = linha.split(";");
				ordenServico = new OrdenServico();
				ordenServico.setId(Integer.parseInt(campos[0]));
				ordenServico.setDataAgendamento(campos[1]);
				ordenServico.setDataExecucao(campos[2]);
				ordenServico.setTotalHoras(Integer.parseInt(campos[3]));
				ordenServico.setEndereco(campos[4]);
				ordenServico.setFuncionario(new Funcionario(Integer.parseInt(campos[6])));
				ordensServico.add(ordenServico);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo não foi encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo: "+e);
		}
		return ordensServico;
	}
}

package domains.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import domains.Funcionario;

public class FuncionarioDAO {
	private Funcionario funcionario;
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo = "D:\\WORKSPACE 2DES PWBE\\OrdenServico\\bd\\funcionarios.csv";
	private String[] campos;
	
	public boolean save(ArrayList<Funcionario> funcionarios) {
		boolean retorno = false;
		try {
			bw = new BufferedWriter(new FileWriter(arquivo,false));
			for(Funcionario f: funcionarios) {
				bw.write(f.toCSV());
			}
			bw.close();
			retorno = true;
		} catch (IOException e) {
			System.out.println("Erro ao salvar "+e);
		}
		return retorno;
	}
	
	public ArrayList<Funcionario> open(){
		ArrayList<Funcionario> funcionarios = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(arquivo));
			String linha = br.readLine();
			while(linha != null) {
				campos = linha.split(";");
				funcionario = new Funcionario();
				funcionario.setId(Integer.parseInt(campos[0]));
				funcionario.setNome(campos[1]);
				funcionario.setNascimento(campos[2]);
				funcionario.setEndereco(campos[3]);
				funcionario.setEspecialidade(campos[4]);
				String[] competencias = campos[5].split(",");
				for(int i = 0; i < competencias.length; i++) {
					funcionario.getCompetencias().add(competencias[i]);
				}
				funcionario.setPeriodo(campos[6]);
				funcionario.setValorHora(Double.parseDouble(campos[7]));
				funcionarios.add(funcionario);
				linha = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("O arquivo não foi encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao abrir arquivo: "+e);
		}
		return funcionarios;
	}
}

package arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import dao.ClasseDAO;
import model.BeanAlunoFone;

public class Arquivo {
	
	@Test
	public void initGravaArquivo() throws IOException {
		File arquivo = new File("C:\\Users\\fermat\\git\\repository3\\projeto-oo-jdbc\\src\\test\\java\\arquivo\\arquivo.txt");
		
		//File arquivo = new File("C:\\Users\\fermat\\git\\repository3\\projeto-oo-jdbc\\src\\test\\java\\arquivo\\arquivo.csv");

		if(!arquivo.exists()) {
			arquivo.createNewFile();
		}
		
		FileWriter escrever_arquivo = new FileWriter(arquivo);
		
		//escrever_arquivo.write("TEXTO");
		try {
			ClasseDAO classeDao = new ClasseDAO();
			List<BeanAlunoFone> list = classeDao.listarAlunoFone(11L);
			
			for(BeanAlunoFone b : list) {
				escrever_arquivo.write(b.getNome()+";"+b.getEmail()+";"+b.getNumero()+";"+b.getTipo()+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		escrever_arquivo.flush();//Persistir
		escrever_arquivo.close();//Fechar
	
	}
	@Test
	public void initLerArquivo() throws FileNotFoundException {
		FileInputStream entrada = new FileInputStream(
				new File("C:\\Users\\fermat\\git\\repository3\\projeto-oo-jdbc\\src\\test\\java\\arquivo\\arquivo.txt"));
		
		
		//FileInputStream entrada = new FileInputStream(
		//		new File("C:\\Users\\fermat\\git\\repository3\\projeto-oo-jdbc\\src\\test\\java\\arquivo\\arquivo.csv"));
		
		Scanner lerArquivo = new Scanner(entrada,"UTF-8");
		
		//Ler arquivo
		/*while (lerArquivo.hasNext()) {
			String linha = lerArquivo.nextLine();
			
			if(linha != null && !linha.isEmpty()) {
				System.out.println(linha);
			}	
		}*/
		
		//Ler arquivo e add em uma lista de objetos
		List<BeanAlunoFone> list = new ArrayList<BeanAlunoFone>();
		while (lerArquivo.hasNext()) {
			String linha = lerArquivo.nextLine();
			
			if(linha != null && !linha.isEmpty()) {
				String[] dados = linha.split("\\;");
				
				BeanAlunoFone beanAlunoFone = new BeanAlunoFone();
				beanAlunoFone.setNome(dados[0]);
				beanAlunoFone.setEmail(dados[1]);
				beanAlunoFone.setNumero(dados[2]);
				beanAlunoFone.setTipo(dados[3]);
				
				list.add(beanAlunoFone);
			}	
		}
		for(BeanAlunoFone b : list) {
			System.out.println(b.toString());
		}
	}
	
	@Test
	public void initGravaArquivoPOI() throws Throwable {
		File arquivo = new File("C:\\Users\\fermat\\git\\repository3\\projeto-oo-jdbc\\src\\test\\java\\arquivo\\arquivo_planilha.xls");

		if(!arquivo.exists()) {
			arquivo.createNewFile();
		}
		
		ClasseDAO classeDao = new ClasseDAO();
		List<BeanAlunoFone> list = classeDao.listarAlunoFone(11L);
		
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();//Vai ser usado para escrever a planilha
		HSSFSheet linhasInformacoes = hssfWorkbook.createSheet("Planilha de Informações");//Criar planilha
		//Controle linha
		int numeroLinha = 0;
		for(BeanAlunoFone b : list) {
			Row linha = linhasInformacoes.createRow(numeroLinha++);//Criando a linha
			
			int celula = 0;
			
			Cell celNome = linha.createCell(celula++);
			celNome.setCellValue(b.getNome());
			
			Cell celEmail = linha.createCell(celula++);
			celEmail.setCellValue(b.getEmail());
			
			Cell celNumero = linha.createCell(celula++);
			celNumero.setCellValue(b.getNumero());
			
			Cell celTipo = linha.createCell(celula++);
			celTipo.setCellValue(b.getTipo());			
			
		}//Termina de montar a planilha
		
		FileOutputStream registro = new FileOutputStream(arquivo);
		hssfWorkbook.write(registro);
		registro.flush();
		registro.close();
	
	}
}

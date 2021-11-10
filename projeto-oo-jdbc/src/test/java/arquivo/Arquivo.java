package arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import dao.ClasseDAO;
import model.BeanAlunoFone;

public class Arquivo {
	
	@Test
	public void initGravaArquivo() throws IOException {
		File arquivo = new File("C:\\Users\\fermat\\git\\repository3\\projeto-oo-jdbc\\src\\test\\java\\arquivo\\arquivo.txt");
		
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
}

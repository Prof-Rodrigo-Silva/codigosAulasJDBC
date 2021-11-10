package arquivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

public class Arquivo {
	
	@Test
	public void initArquivo() throws IOException {
		File arquivo = new File("C:\\Users\\fermat\\git\\repository3\\projeto-oo-jdbc\\src\\test\\java\\arquivo\\arquivo.txt");
		
		if(!arquivo.exists()) {
			arquivo.createNewFile();
		}
		
		FileWriter escrever_arquivo = new FileWriter(arquivo);
		
		escrever_arquivo.write("TEXTO");
		escrever_arquivo.flush();//Persistir
		escrever_arquivo.close();//Fechar
	
	}
}

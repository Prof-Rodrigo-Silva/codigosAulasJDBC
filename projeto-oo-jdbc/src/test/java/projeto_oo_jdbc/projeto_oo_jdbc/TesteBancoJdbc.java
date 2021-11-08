package projeto_oo_jdbc.projeto_oo_jdbc;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.ClasseDAO;
import model.Aluno;

public class TesteBancoJdbc {
	
	/*@Test
	public void initBanco() {
		SingleConnection.getConnetion();
	}*/
	
	@Test
	public void initBanco() {
		Aluno aluno = new Aluno();
		ClasseDAO classeDao = new ClasseDAO();
		
		//classeDao.salvar(aluno);
		
		aluno.setId(2L);
		aluno.setNome("Luciana");
		aluno.setEmail("outroteste@gmail.com");
		
		classeDao.salvar(aluno);
	}
	
	

}

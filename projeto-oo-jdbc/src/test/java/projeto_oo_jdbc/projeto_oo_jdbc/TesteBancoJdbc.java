package projeto_oo_jdbc.projeto_oo_jdbc;

import java.util.List;

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
	
	@Test
	public void initListar() {
		ClasseDAO classeDao = new ClasseDAO();
		try {
			List<Aluno> list = classeDao.listar();
			
			for(Aluno aluno : list) {
				System.out.println(aluno.getNome());
				System.out.println("----------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initBuscar() {
		ClasseDAO classeDao = new ClasseDAO();
		try {
			Aluno aluno = classeDao.buscar(2L);
			
			System.out.println(aluno.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}

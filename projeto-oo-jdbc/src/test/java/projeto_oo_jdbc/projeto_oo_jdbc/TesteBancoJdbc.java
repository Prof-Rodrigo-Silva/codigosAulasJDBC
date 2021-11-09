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
		
		//aluno.setId(2L);
		aluno.setNome("Yúri");
		aluno.setEmail("yuri@gmail.com");
		
		classeDao.salvar(aluno);
	}
	
	@Test
	public void initListar() {
		try {
			ClasseDAO classeDao = new ClasseDAO();
			List<Aluno> list = classeDao.listar();
			
			for(Aluno aluno : list) {
				System.out.println(aluno.toString());
				System.out.println("----------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void initBuscar() {
		try {
			ClasseDAO classeDao = new ClasseDAO();
			Aluno aluno = classeDao.buscar(4L);
			
			System.out.println(aluno.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void initAtualizar () {
		
		try {
			ClasseDAO classeDao = new ClasseDAO();
			Aluno aluno = classeDao.buscar(5L);
			
			aluno.setNome("Rodrigo");
			
			classeDao.atualizar(aluno);
		} catch (Exception e) {
			
			e.printStackTrace();
		}	
	}
	
	@Test
	public void initDeletar() {
		try {
			ClasseDAO classeDao = new ClasseDAO();
			classeDao.deletar(5L);
					
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}

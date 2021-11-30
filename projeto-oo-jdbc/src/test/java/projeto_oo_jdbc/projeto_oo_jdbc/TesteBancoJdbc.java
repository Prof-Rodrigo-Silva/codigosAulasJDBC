package projeto_oo_jdbc.projeto_oo_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.ClasseDAO;
import model.Aluno;
import model.BeanAlunoFone;
import model.Telefone;

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
			classeDao.deletar(7L);
					
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void salvarTelefone() {
		Telefone telefone = new Telefone();
		ClasseDAO classeDAO = new ClasseDAO();
		telefone.setNumero("99900334455");
		telefone.setTipo("comercial");
		telefone.setIdAluno(10L);
		
		classeDAO.salvarTelefone(telefone);
	}
	@Test
	public void initListarAlunoTelefone() {
		try {
			ClasseDAO classeDao = new ClasseDAO();
			List<BeanAlunoFone> list = classeDao.listarAlunoFone(11L);
			
			for(BeanAlunoFone beanAlunoFone : list) {
				System.out.println(beanAlunoFone.toString());
				System.out.println("--------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void initDeletarCascata() {
		try {
			ClasseDAO classeDao = new ClasseDAO();
			classeDao.deletarCascata(10L);
					
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	@Test
	public void initUltimoRegistro() throws SQLException {
		ClasseDAO classeDAO = new ClasseDAO();
		String id = classeDAO.ultimoRegistro();
		System.out.println(id);
	}
	
}

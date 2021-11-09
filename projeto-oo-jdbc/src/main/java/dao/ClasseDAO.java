package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Aluno;
import model.BeanAlunoFone;
import model.Telefone;

public class ClasseDAO {

	private Connection connection;

	public ClasseDAO() {
		connection = SingleConnection.getConnetion();
	}

	// Insert
	public void salvar(Aluno aluno) {
		try {
			//String sql = "insert into alunojdbc(id, nome, email) values(?,?,?)";
			String sql = "insert into alunojdbc(nome, email) values(?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			 /*statement.setLong(1, 1);
			 statement.setString(2, "Rodrigo");
			 statement.setString(3,"rodrigoteste@gmail.com");
			 
		    statement.setLong(1, aluno.getId());
			statement.setString(2, aluno.getNome());
			statement.setString(3, aluno.getEmail());*/
			 
			statement.setString(1, aluno.getNome());	
			statement.setString(2, aluno.getEmail());
			
			statement.execute();
			connection.commit();// Executa no banco

		} catch (Exception e) {
			try {
				connection.rollback();// Reverte a operação
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "INSERT INTO telefonealuno( numero, tipo, usuario) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			 
			statement.setString(1, telefone.getNumero());	
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getIdAluno());
			
			statement.execute();
			connection.commit();// Executa no banco

		} catch (Exception e) {
			try {
				connection.rollback();// Reverte a operação
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Aluno> listar() throws Exception {
		
		List<Aluno> list = new ArrayList<Aluno>();

		String sql = "select * from alunojdbc";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(resultado.getLong("id"));
			aluno.setNome(resultado.getString("nome"));
			aluno.setEmail(resultado.getString("email"));

			list.add(aluno);

		}

		return list;
	}
	
public List<BeanAlunoFone> listarAlunoFone(Long idAluno) throws Exception {
		
		List<BeanAlunoFone> list = new ArrayList<BeanAlunoFone>();

		String sql = " select nome, email, numero, tipo from telefonealuno as fone ";
		sql+=" inner join alunojdbc as aluno ";
		sql+=" on fone.usuario = aluno.id ";
		sql+=" where aluno.id = "+idAluno;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			BeanAlunoFone beanAlunoFone = new BeanAlunoFone();
			beanAlunoFone.setNome(resultado.getString("nome"));
			beanAlunoFone.setNumero(resultado.getString("numero"));
			beanAlunoFone.setEmail(resultado.getString("email"));
			beanAlunoFone.setTipo(resultado.getString("tipo"));

			list.add(beanAlunoFone);

		}

		return list;
	}

	public Aluno buscar(Long id) throws Exception {
		Aluno aluno = new Aluno();

		String sql = "select * from alunojdbc where id =" + id;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			aluno.setId(resultado.getLong("id"));
			aluno.setNome(resultado.getString("nome"));
			aluno.setEmail(resultado.getString("email"));

		}

		return aluno;
	}

	public void atualizar(Aluno aluno) {

		try {
			String sql = "update alunojdbc set nome = ? where id = " + aluno.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, aluno.getNome());
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from alunojdbc where id = "+id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
	}
	
	public void deletarCascata(Long id) {
		try {
			String sqltelefone = "delete from telefonealuno where usuario = "+id;
			String sqlaluno = "delete from alunojdbc where id = "+id;
			
			PreparedStatement statement = connection.prepareStatement(sqltelefone);
			statement.execute();
			connection.commit();
			
			statement = connection.prepareStatement(sqlaluno);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

}

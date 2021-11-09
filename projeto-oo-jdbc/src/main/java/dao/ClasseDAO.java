package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Aluno;

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

}

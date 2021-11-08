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
	//Insert
	public void salvar(Aluno aluno) {
		try {
		String sql = "insert into alunojdbc(id, nome, email) values(?,?,?)";
		PreparedStatement insert = connection.prepareStatement(sql);
		/*insert.setLong(1, 1);
		insert.setString(2, "Rodrigo DAO");
		insert.setString(3, "rodrigoteste@gmail.com");*/
		insert.setLong(1, aluno.getId());
		insert.setString(2, aluno.getNome());
		insert.setString(3, aluno.getEmail());
		insert.execute();
		connection.commit();//Executa no banco
		
		
		
		} catch (Exception e) {
			try {
				connection.rollback();//Reverte a operação
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}	
	}
	
	public List<Aluno> listar() throws Exception{
		List<Aluno> list = new ArrayList<Aluno>();
		
		String sql = "select * from alunojdbc";
		
		PreparedStatement query  = connection.prepareStatement(sql);
		
		ResultSet resultado = query.executeQuery();
		
		while (resultado.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(resultado.getLong("id"));
			aluno.setNome(resultado.getString("nome"));
			aluno.setEmail(resultado.getString("email"));
			
			list.add(aluno);
			
		}
		
		return list;		
	}
	
	public Aluno buscar(Long id) throws Exception{
		Aluno aluno = new Aluno();
		
		String sql = "select * from alunojdbc where id ="+id;
		
		PreparedStatement query  = connection.prepareStatement(sql);
		
		ResultSet resultado = query.executeQuery();
		
		while (resultado.next()) {
			aluno.setId(resultado.getLong("id"));
			aluno.setNome(resultado.getString("nome"));
			aluno.setEmail(resultado.getString("email"));
			
		}
		
		return aluno;	
		
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}

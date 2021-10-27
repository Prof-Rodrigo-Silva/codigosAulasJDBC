package projeto_oo_jdbc.projeto_oo_jdbc;

import org.junit.Test;

import conexaojdbc.SingleConnection;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco() {
		SingleConnection.getConnetion();
	}

}

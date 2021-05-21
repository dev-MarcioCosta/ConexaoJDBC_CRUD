package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	/*
	 * CRUD
	 * 
	 * C - CREATE R - READ U - UPDATE D - DELETE
	 */

	// Insert
	public void save(Contato contato) {

		// Querry
		String sql = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (? , ? , ?)";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			// Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criamos uma PrepareStatement, para executar uma query
			pstm = conn.prepareStatement(sql);

			// Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Executar a querry
			pstm.execute();

			System.out.println("Contato salvo com Sucesso");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// fechar as conexões

			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// Update

	public void update(Contato contato) {

		String sql = "UPDATE contatos SET nome = ?,  idade = ?, dataCadastro = ?  WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {

			// Criar conexão com o banco

			conn = ConnectionFactory.createConnectionToMySQL();

			// criar a classe para executar a query

			pstm = conn.prepareStatement(sql);

			// adicionar os valores para atualizar

			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Id do registro que dejesja atualizar
			pstm.setInt(4, contato.getId());

			// Executar a querry

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void deleteById(int id) {

		String sql = "DELETE FROM contatos WHERE id = ?";

		Connection conn = null;

		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = conn.prepareStatement(sql);

			pstm.setInt(1, id);

			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// Read
	public List<Contato> getContatos() {

		String sql = "SELECT * FROM contatos";

		List<Contato> contatos = new ArrayList<Contato>();

		Connection conn = null;
		PreparedStatement pstm = null;

		ResultSet rset = null; // Aqui será armazenados os dados recuperados do banco de dados

		try {
			conn = ConnectionFactory.createConnectionToMySQL();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();

				contato.setId(rset.getInt("id"));
				contato.setNome(rset.getString("nome"));
				contato.setIdade(rset.getInt("id"));
				contato.setDataCadastro(rset.getDate("dataCadastro"));

				contatos.add(contato);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contatos;
	}

}

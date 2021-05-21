package br.com.agenda.aplicacao;


import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {
		
		ContatoDAO contatoDao = new ContatoDAO();
		Contato  contato = new Contato();
		
		contato.setNome("julia");
		contato.setIdade(13);
		contato.setDataCadastro(new Date());
		
		 //Insert
		//contatoDao.save(contato);
	
		//Update de contatos
		Contato c1 = new Contato();
		c1.setNome("Marcelly");
		c1.setIdade(18);
		c1.setDataCadastro(new Date());
		c1.setId(2);
		
		contatoDao.update(c1);
		
		//vizualização de todos os registros do banco de dados
		
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: " + c.getNome());
		}
		
		//Delete
		
		contatoDao.deleteById(7);

	}

}

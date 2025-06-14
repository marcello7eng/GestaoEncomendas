package br.com.encomendas.controller;



import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.encomendas.dao.EncomendaDAO;
import br.com.encomendas.model.Encomenda;

@ManagedBean
@ViewScoped
public class EncomendaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 private Encomenda encomenda = new Encomenda();
	    private List<Encomenda> lista;

	    public void salvar() throws SQLException {
	        EncomendaDAO dao = new EncomendaDAO();
	        dao.salvar(encomenda);
	        encomenda = new Encomenda(); // limpar formulário
	        listar();
	    }

	    public void listar() throws SQLException {
	        EncomendaDAO dao = new EncomendaDAO();
	        lista = dao.listar();
	    }

		public Encomenda getEncomenda() {
			return encomenda;
		}

		public void setEncomenda(Encomenda encomenda) {
			this.encomenda = encomenda;
		}

		public List<Encomenda> getLista() {
			return lista;
		}

		public void setLista(List<Encomenda> lista) {
			this.lista = lista;
		}
	    
	    

}

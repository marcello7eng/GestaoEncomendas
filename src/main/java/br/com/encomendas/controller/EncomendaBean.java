package br.com.encomendas.controller;



import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.encomendas.dao.EncomendaDAO;
import br.com.encomendas.model.Encomenda;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class EncomendaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	 private Encomenda encomenda = new Encomenda();
	    private List<Encomenda> lista;
	    
	    
	    @PostConstruct
	    public void init() {
	        try {
	            listar();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void salvar() throws SQLException {
	        EncomendaDAO dao = new EncomendaDAO();
	        boolean sucesso = dao.salvar(encomenda);
	        if (sucesso) {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Encomenda salva com sucesso!"));
	        } else {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao salvar."));
	        }
	        encomenda = new Encomenda();
	        listar();
	    }

	    public void listar() throws SQLException {
	        EncomendaDAO dao = new EncomendaDAO();
	        lista = dao.listar();
	    }
	    
	    public void editar(Encomenda e) {
	        this.encomenda = e; // carrega no formulário
	    }

	    public void excluir(Encomenda e) {
	        try {
	            EncomendaDAO dao = new EncomendaDAO();
	            dao.excluir(e);
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Encomenda excluída com sucesso!"));
	            listar();
	        } catch (SQLException ex) {
	            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao excluir."));
	            ex.printStackTrace();
	        }
	    }
	    
	    //=================================================
	    
	    private String filtroStatus = "";

	    public String getFiltroStatus() {
	        return filtroStatus;
	    }

	    public void setFiltroStatus(String filtroStatus) {
	        this.filtroStatus = filtroStatus;
	    } 
	    
	    public List<Encomenda> getLista() {
	        if (filtroStatus == null || filtroStatus.isEmpty()) {
	            return lista;
	        }
	        return lista.stream()
	                    .filter(e -> e.getStatus() != null && e.getStatus().equalsIgnoreCase(filtroStatus))
	                    .collect(Collectors.toList());
	    }


	    
	    //=================================================

		public Encomenda getEncomenda() {
			return encomenda;
		}

		public void setEncomenda(Encomenda encomenda) {
			this.encomenda = encomenda;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public void setLista(List<Encomenda> lista) {
			this.lista = lista;
		}
	    
	    //=================================================
	    
	    

}

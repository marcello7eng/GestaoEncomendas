package br.com.encomendas.model;

public class Encomenda {
	
	 private Integer id;
	    private String codigoRastreio;
	    private String link;
	    private String status; // "Recebido" ou "Pendente"
	    private String descricao;
	    private String remetente;
	    private String observacoes;
	    
	    
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getCodigoRastreio() {
			return codigoRastreio;
		}
		public void setCodigoRastreio(String codigoRastreio) {
			this.codigoRastreio = codigoRastreio;
		}
		public String getLink() {
			return link;
		}
		public void setLink(String link) {
			this.link = link;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public String getRemetente() {
			return remetente;
		}
		public void setRemetente(String remetente) {
			this.remetente = remetente;
		}
		public String getObservacoes() {
			return observacoes;
		}
		public void setObservacoes(String observacoes) {
			this.observacoes = observacoes;
		}
		
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Encomenda other = (Encomenda) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

	 
	    
	    
	    
	    

}

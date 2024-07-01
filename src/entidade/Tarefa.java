package entidade;

import java.sql.Date;

public class Tarefa {
	private int id;
    private String descricao;
    private Date data_criacao;
    private Date data_conclusao;
    private boolean concluida;
    
    public Tarefa() {
    }
	
	public Tarefa(int id, String descricao, Date data_criacao, Date data_conclusao, boolean concluida) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data_criacao = data_criacao;
		this.data_conclusao = data_conclusao;
		this.concluida = concluida;
	}



	public Tarefa(String descricao, Date data_criacao, Date data_conclusao, boolean concluida) {
		super();
		this.descricao = descricao;
		this.data_criacao = data_criacao;
		this.data_conclusao = data_conclusao;
		this.concluida = concluida;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Date getData_conclusao() {
		return data_conclusao;
	}

	public void setData_conclusao(Date data_conclusao) {
		this.data_conclusao = data_conclusao;
	}

	public boolean isConcluida() {
		return concluida;
	}

	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}

	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", descricao=" + descricao + ", data_criacao=" + data_criacao + ", data_conclusao="
				+ data_conclusao + ", concluida=" + concluida + "]";
	}
    
    

}

package bean;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


import dao.JogoDao;
import entidade.Jogo;

@ManagedBean
public class JogoBean {
	private Jogo jogo = new Jogo();
	private List<Jogo> listaJogo;

	public String salvar() {
		try {
			jogo.gerarNumerosAleatorios();//gerando os n�meros aleat�rios
			JogoDao.salvar(jogo);
			jogo = new Jogo();

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Salvo com successo!"));
		} catch (Exception e) {
			jogo = new Jogo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha ao salvar!", "Os dados não foram salvos!"));
		}
		return null;
	}

	public String editar() {
		try {
			JogoDao.editar(jogo);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Editado com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!", "Os dados não foram editados!"));
		}
		return null;
	}

	public String excluir() {
		try {
			JogoDao.excluir(jogo);
			listaJogo.remove(jogo);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, null, "Excluido com sucesso!"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Falha ao excluir!", "Os dados não forma excluidos!"));
		}
		return null;
	}

	public String listar() {
		listaJogo = JogoDao.listar();
		return "listagem.xhtml";
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getListaJogo() {
		if (listaJogo == null) {
			listaJogo = JogoDao.listar();
		}
		return listaJogo;
	}

	public void setListaJogo(List<Jogo> listaJogo) {
		this.listaJogo = listaJogo;
	}

	
}

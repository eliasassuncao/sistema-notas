
package br.elias.alunos.bean;
import br.elias.alunos.dao.AlunoDAO;
import br.elias.alunos.aluno.Aluno;
import br.elias.alunos.exception.ErroSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author elias
 */

@ManagedBean
@SessionScoped
public class AlunoBean {
        
    private Aluno aluno = new Aluno();
    private List<Aluno> alunos = new ArrayList<>();
    private AlunoDAO alunoDAO = new AlunoDAO();
    
    public void adicionar(){
        try {
            alunoDAO.salvar(aluno);
            aluno = new Aluno();
            adicionarMensagem("Salvo!", "Aluno salvo com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void listar(){
        try {
            alunos = alunoDAO.buscar();
            if(alunos == null || alunos.isEmpty()){
                adicionarMensagem("Nenhum dado encontrado!", "Sua busca não retornou nenhum aluno!", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
  
    public void deletar(Aluno a){
        try {
            alunoDAO.deletar(a.getId());
            adicionarMensagem("Deletado!", "Aluno deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            adicionarMensagem(ex.getMessage(), ex.getCause().getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void editar(Aluno a){
        aluno = a;
    }

    public void adicionarMensagem(String sumario, String detalhe, FacesMessage.Severity tipoErro){
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
        context.addMessage(null, message);
    }
    
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

}

package caio4abreu.taskgrimoire.controller;

import caio4abreu.taskgrimoire.model.Atividade;
import caio4abreu.taskgrimoire.service.AtividadeService;

/**
 *
 * @author Caio 4breu
 */
public class AtividadeController {
    private AtividadeService atividade = new AtividadeService();
    
    public String adicionarAtividade(String nomeLista, String titulo, String descricao){
        try{
            atividade.adicionarAtividades(nomeLista, titulo, descricao);
            return "Atividade criada com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    
    public String removerAtividade(String nome){
        try{
            atividade.removerAtividades(nome);
            return "Atividade removida com sucesso!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    
    public Atividade espiarAtividade(String nome){
            return atividade.espiarAtividade(nome);
    }
}
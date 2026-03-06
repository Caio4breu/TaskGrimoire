package caio4abreu.taskgrimoire.service;

import caio4abreu.taskgrimoire.model.Atividade;
import caio4abreu.taskgrimoire.model.ListaDeAtividades;

/**
 *
 * @author Caio 4breu
 */
public class AtividadeService {
    private ListaService listaService = new ListaService();
    
    public void adicionarAtividades(String nomeLista, String titulo, String descricao) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode estar vazio");
        }
        ListaDeAtividades lista = listaService.buscarLista(nomeLista);
        Atividade atividade = new Atividade(titulo, descricao);
        lista.adicionar(atividade);
    }
    
    public Atividade removerAtividades(String nomeLista) {
        
    }
    
    public Atividade espiarAtividade(String nomeLista) {
        
    }
}

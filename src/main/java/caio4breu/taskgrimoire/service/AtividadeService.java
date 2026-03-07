package caio4breu.taskgrimoire.service;

import caio4breu.taskgrimoire.model.Atividade;
import caio4breu.taskgrimoire.model.ListaDeAtividades;

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
        ListaDeAtividades lista = listaService.buscarLista(nomeLista);
        if (lista.espiar() == null) {
            throw new IllegalArgumentException("A lista está vazia.");
        }
        return lista.remover();
    }
    
    public Atividade espiarAtividade(String nomeLista) {
        ListaDeAtividades lista = listaService.buscarLista(nomeLista);
        return lista.espiar();
    }
}

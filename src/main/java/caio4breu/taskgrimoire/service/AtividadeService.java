package caio4breu.taskgrimoire.service;

import caio4breu.taskgrimoire.model.Atividade;
import caio4breu.taskgrimoire.model.ListaDeAtividades;
import caio4breu.taskgrimoire.repositories.AtividadeRepositoryDB;

/**
 *
 * @author Caio 4breu
 */
public class AtividadeService {
    private ListaService listaService = new ListaService();
    private AtividadeRepositoryDB atividadeRepository = new AtividadeRepositoryDB();

    public void adicionarAtividades(String nomeLista, String titulo, String descricao) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título não pode estar vazio");
        }
        ListaDeAtividades lista = listaService.buscarLista(nomeLista);
        Atividade atividade = new Atividade(titulo, descricao);
        atividadeRepository.salvar(atividade, lista.getId());
    }
    
    public Atividade removerAtividades(String nomeLista) {
        ListaDeAtividades lista = listaService.buscarLista(nomeLista);
        Atividade proxima = atividadeRepository.buscarProxima(lista.getId());
        if (proxima == null) {
            throw new IllegalArgumentException("A lista está vazia.");
        }
        return atividadeRepository.removerProxima(lista.getId(), lista.getTipo().toString());
    }
    
    public Atividade espiarAtividade(String nomeLista) {
        ListaDeAtividades lista = listaService.buscarLista(nomeLista);
        return atividadeRepository.buscarProxima(lista.getId());
    }
}
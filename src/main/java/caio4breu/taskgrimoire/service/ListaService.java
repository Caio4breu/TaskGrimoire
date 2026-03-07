package caio4breu.taskgrimoire.service;

import caio4breu.taskgrimoire.model.ListaDeAtividades;
import caio4breu.taskgrimoire.model.TipoEstrutura;
import caio4breu.taskgrimoire.repositories.ListaRepository;
import caio4breu.taskgrimoire.repositories.ListaRepositoryImpl;
import java.util.List;

/**
 *
 * @author Caio 4breu
 */
public class ListaService {
    private ListaRepository repository = new ListaRepositoryImpl();
    
    public void criarLista(String nome, TipoEstrutura tipo){
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da lista não pode ser vazio.");
        }
        
        if (repository.buscarPorNome(nome) != null) {
            throw new IllegalArgumentException("Já existe uma lista com esse nome.");
        }
        
        ListaDeAtividades lista = new ListaDeAtividades(nome, tipo);
        repository.salvar(lista);
    }
    
    public ListaDeAtividades buscarLista(String nome){
        ListaDeAtividades lista = repository.buscarPorNome(nome);
        if (lista == null) {
            throw new IllegalArgumentException("Lista não encontrada.");
        }
        return lista;
    }
    
    public List<ListaDeAtividades> listarTodas() {
        return repository.buscarTodos();
    }
    
    public void deletarLista(String nome) {
        if (repository.buscarPorNome(nome) == null) {
            throw new IllegalArgumentException("Lista não encontrada.");
        }
        repository.deletar(nome);
    }
}
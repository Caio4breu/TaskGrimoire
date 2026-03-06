package caio4abreu.taskgrimoire.repositories;

import caio4abreu.taskgrimoire.model.ListaDeAtividades;
import java.util.List;

/**
 *
 * @author Caio 4breu
 */
public interface ListaRepository {
    public void salvar(ListaDeAtividades lista);
    public ListaDeAtividades buscarPorNome(String nome);
    public List<ListaDeAtividades> buscarTodos();
    public void deletar(String nome);
}
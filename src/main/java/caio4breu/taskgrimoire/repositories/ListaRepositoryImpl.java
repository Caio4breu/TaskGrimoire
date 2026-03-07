package caio4breu.taskgrimoire.repositories;

import caio4breu.taskgrimoire.model.ListaDeAtividades;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio 4breu
 */
public class ListaRepositoryImpl implements ListaRepository {
    private List<ListaDeAtividades> listas = new ArrayList<>();
    
    public void salvar(ListaDeAtividades lista){
        listas.add(lista);
    }
    public List<ListaDeAtividades> buscarTodos(){
        return listas;
    }
    
    public ListaDeAtividades buscarPorNome(String nome){
        for (ListaDeAtividades l : listas) {
            if (l.getNome().equals(nome)){
                return l;
            }
        }
        return null;
    }
    
    public void deletar(String nome){
        for (ListaDeAtividades l : listas) {
            if (l.getNome().equals(nome)){
                listas.remove(l);
                break;
            }
        }
    }   
}
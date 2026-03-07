package caio4breu.taskgrimoire.controller;

import caio4breu.taskgrimoire.model.ListaDeAtividades;
import caio4breu.taskgrimoire.model.TipoEstrutura;
import caio4breu.taskgrimoire.service.ListaService;
import java.util.List;

/**
 *
 * @author Caio 4breu
 */
public class ListaController {
    private ListaService lista = new ListaService();
    
    public String criarLista(String nome, TipoEstrutura tipo){
        try {
            lista.criarLista(nome, tipo);
            return "Lista criada com sucesso!";
        } catch(IllegalArgumentException e) {
            return e.getMessage();
        }
    }
    
    public ListaDeAtividades buscarLista(String nome){
        return lista.buscarLista(nome);
    }
    
    public List<ListaDeAtividades> listarTodas(){
        return lista.listarTodas();
    }
    
    public String deletarLista(String nome){
        try{
            lista.deletarLista(nome);
            return "Lista deletada com sucesso!";
        } catch (IllegalArgumentException e){
            return e.getMessage();
        }
    }   
}
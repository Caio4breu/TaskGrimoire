package caio4breu.taskgrimoire.model;

/**
 *
 * @author Caio 4breu
 */
public class Fila {
    No inicio = null;
    No fim = null;

    public void inserir(Atividade dado) {
        No p = new No();
        p.setDado(dado);
        
        // Caso seja o primeiro valor da fila
        if (inicio == null) {
            inicio = p;
            fim = p;
            p.setProximo(null);
        } else { // Caso já exista elementos na fila
            fim.setProximo(p);
            p.setProximo(null);
            fim = p;
        }
    }

    Atividade consultar() {
        if (inicio == null) {
            return null;
        }
        return inicio.getDado();
    }

    
    public Atividade excluir() {
        No aux = inicio;
        
        if (aux == fim && inicio != null) {
            inicio = null;
            fim = null;
        } else {
            inicio = inicio.getProximo();
        }
        return aux.getDado();
    }
}
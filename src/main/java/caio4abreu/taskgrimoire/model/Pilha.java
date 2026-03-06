package caio4abreu.taskgrimoire.model;

/**
 *
 * @author Caio 4breu
 */
public class Pilha {
    No inicio = null;
    No fim = null;

    public void inserir(Atividade dado) {
        No p = new No();
        p.setDado(dado);
        
        // Caso seja o primeiro valor da fila
        if (inicio == null){
            inicio = p;
            fim = p;
            p.setProximo(null);
        } else {
            fim.setProximo(p);
            p.setProximo(null);
            fim = p;
        }
    }

    public Atividade consultar() {
        if (fim == null) {
            return null;
        }
        return inicio.getDado();
    }
    
    public Atividade excluir() {
        No aux = fim;
        
        if(inicio == fim && fim != null) {
            inicio = null;
            fim = null;
        } else {
            No temp = inicio;
            while (temp.getProximo() != fim){
                temp = temp.getProximo();
            }
            temp.setProximo(null);
            fim = temp;
        }
        return aux.getDado();
    }
}

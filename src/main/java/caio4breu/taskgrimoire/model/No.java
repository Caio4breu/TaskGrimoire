package caio4breu.taskgrimoire.model;

/**
 *
 * @author Caio 4breu
 */
public class No {
    private Atividade dado;
    private No proximo;
    
    // Getters & Setters

    public Atividade getDado(){ return dado; }
    public void setDado(Atividade dado){ this.dado = dado; }
    public No getProximo(){ return proximo; }
    public void setProximo(No proximo){ this.proximo = proximo; }
}
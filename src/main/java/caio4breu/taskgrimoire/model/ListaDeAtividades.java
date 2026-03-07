package caio4breu.taskgrimoire.model;


/**
 *
 * @author Caio 4breu
 */
public class ListaDeAtividades {
    private String nome;
    private TipoEstrutura tipo;
    private Fila fila;
    private Pilha pilha;
    
    public String getNome() { return nome; }
    public TipoEstrutura getTipo() { return tipo; }

    
    public ListaDeAtividades(String nome, TipoEstrutura tipo) {
        this.nome = nome;
        this.tipo = tipo;
        
        if (tipo == TipoEstrutura.FILA){
            this.fila = new Fila();
        } else {
            this.pilha = new Pilha();
        }
        
    }
    
    public void adicionar(Atividade atividade) {
        if (tipo == TipoEstrutura.FILA) {
            fila.inserir(atividade);
        } else {
            pilha.inserir(atividade);
        }
    }
    
    public Atividade remover() {
        if (tipo == TipoEstrutura.FILA) {
            return fila.excluir();
        } else {
            return pilha.excluir();
        }
    }
    
    public Atividade espiar() {
        if (tipo == TipoEstrutura.FILA) {
            return fila.consultar();
        } else {
            return pilha.consultar();
        }
    }
}
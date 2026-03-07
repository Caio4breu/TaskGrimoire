package caio4breu.taskgrimoire.model;

import java.time.LocalDateTime;

/**
 *
 * @author Caio 4breu
 */
public class Atividade {
    private String titulo;
    private String descricao;
    private LocalDateTime dataRegistro;
    private LocalDateTime dataTermino;
    
    public Atividade(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataRegistro = LocalDateTime.now();
        this.dataTermino = null;
    }
    
    // Getters & Setters
    public String getTitulo(){ return titulo; }
    public void setTitulo(String titulo){ this.titulo = titulo;}
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public LocalDateTime getDataRegistro() { return dataRegistro; }
    public void setDataRegistro (LocalDateTime dataRegistro) { this.dataRegistro = dataRegistro;}
    
    public LocalDateTime getDataTermino() { return dataTermino; }
    public void setDataTermino(LocalDateTime dataTermino) { this.dataTermino = dataTermino; }
    
    @Override
    public String toString(){
        return "Atividade: " + titulo + "\n" +
                "Descrição: " + descricao + "\n" +
                "Data de inicio: " + dataRegistro;
    }
}
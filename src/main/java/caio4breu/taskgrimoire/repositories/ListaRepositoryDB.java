package caio4breu.taskgrimoire.repositories;

import caio4breu.taskgrimoire.infra.ConexaoDB;
import caio4breu.taskgrimoire.model.ListaDeAtividades;
import caio4breu.taskgrimoire.model.TipoEstrutura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio 4breu
 */
public class ListaRepositoryDB implements ListaRepository {
    
    @Override
    public void salvar(ListaDeAtividades lista){
        String sql = "insert into lista_atividades (nome, tipo) values (?, ?)";
        try (Connection conn = ConexaoDB.conectar();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, lista.getNome());
            stmt.setString(2, lista.getTipo().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar lista: " + e.getMessage());
        }
    }
    
    @Override
    public ListaDeAtividades buscarPorNome(String nome){
        String sql = "select nome, tipo from lista_atividades where nome = ?";
        try (Connection conn = ConexaoDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TipoEstrutura tipo = TipoEstrutura.valueOf(rs.getString("tipo"));
                return new ListaDeAtividades(rs.getString("nome"), tipo);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar lista: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<ListaDeAtividades> buscarTodos() {
        List<ListaDeAtividades> listas = new ArrayList<>();
        String sql = "select nome, tipo from lista_atividades";
        try (Connection conn = ConexaoDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TipoEstrutura tipo = TipoEstrutura.valueOf(rs.getString("tipo"));
                listas.add(new ListaDeAtividades(rs.getString("nome"), tipo));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar lista: " + e.getMessage());
        }
        return listas;
    }
    
    @Override
    public void deletar(String nome){
        String sql = "delete from lista_atividades where nome = ?";
        try (Connection conn = ConexaoDB.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar lista: " + e.getMessage());
        }
    }
}
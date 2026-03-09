package caio4breu.taskgrimoire.repositories;

import caio4breu.taskgrimoire.infra.ConexaoDB;
import caio4breu.taskgrimoire.model.Atividade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Caio 4breu
 */

public class AtividadeRepositoryDB implements AtividadeRepository {

    @Override
    public void salvar(Atividade atividade, int listaId) {
        String sql = "INSERT INTO atividade (titulo, descricao, data_registro, lista_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, atividade.getTitulo());
            stmt.setString(2, atividade.getDescricao());
            stmt.setTimestamp(3, Timestamp.valueOf(atividade.getDataRegistro()));
            stmt.setInt(4, listaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar atividade: " + e.getMessage());
        }

    }

    @Override
    public Atividade buscarProxima(int listaId) {
        String sql = "SELECT titulo, descricao, data_registro FROM atividade WHERE lista_id = ? ORDER BY id ASC LIMIT 1";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, listaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Atividade atividade = new Atividade(rs.getString("titulo"), rs.getString("descricao"));
                atividade.setDataRegistro(rs.getTimestamp("data_registro").toLocalDateTime());
                return atividade;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar proxima atividade: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Atividade removerProxima(int listaId, String tipoEstrutura) {
        String ordem = tipoEstrutura.equals("FILA") ? "ASC" : "DESC";
        String sqlBuscar = "SELECT id, titulo, descricao, data_registro FROM atividade WHERE lista_id = ? ORDER BY id " + ordem + " LIMIT 1";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sqlBuscar)) {
            stmt.setInt(1, listaId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                Atividade atividade = new Atividade(rs.getString("titulo"), rs.getString("descricao"));
                atividade.setDataRegistro(rs.getTimestamp("data_registro").toLocalDateTime());

                String sqlDeletar = "DELETE FROM atividade WHERE id = ?";
                try (PreparedStatement stmtDel = conn.prepareStatement(sqlDeletar)) {
                    stmtDel.setInt(1, id);
                    stmtDel.executeUpdate();
                }
                return atividade;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover atividade: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Atividade> buscarTodas(int listaId) {
        List<Atividade> atividades = new ArrayList<>();
        String sql = "SELECT titulo, descricao, data_registro FROM atividade WHERE lista_id = ? ORDER BY id ASC";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, listaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Atividade atividade = new Atividade(rs.getString("titulo"), rs.getString("descricao"));
                atividade.setDataRegistro(rs.getTimestamp("data_registro").toLocalDateTime());
                atividades.add(atividade);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar atividades: " + e.getMessage());
        }
        return atividades;
    }
}

package caio4breu.taskgrimoire.repositories;

import caio4breu.taskgrimoire.model.Atividade;
import java.util.List;

/**
 *
 * @author Caio 4breu
 */
public interface AtividadeRepository {
    void salvar(Atividade atividade, int listaId);
    Atividade buscarProxima(int listaId);
    Atividade removerProxima(int listaId, String tipoEstrutura);
    List<Atividade> buscarTodas(int listaId);
}
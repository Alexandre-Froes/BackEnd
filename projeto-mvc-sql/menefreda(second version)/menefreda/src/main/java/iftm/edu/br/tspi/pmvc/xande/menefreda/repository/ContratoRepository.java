package iftm.edu.br.tspi.pmvc.xande.menefreda.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import iftm.edu.br.tspi.pmvc.xande.menefreda.domain.Contrato;
import iftm.edu.br.tspi.pmvc.xande.menefreda.domain.Paciente;
import iftm.edu.br.tspi.pmvc.xande.menefreda.domain.Plano;

@Repository
public class ContratoRepository {
    private final JdbcTemplate conexao;

    public ContratoRepository(JdbcTemplate conexao) {
        this.conexao = conexao;
    }

    public Contrato setContrato(ResultSet rs) throws SQLException {
        Contrato contrato = new Contrato();
        contrato.setCodigo(rs.getInt("cod_cont"));
        contrato.setDataContratacao(rs.getString("dt_inicio"));
        contrato.setDataValidade(rs.getString("validade"));
        contrato.setValor(rs.getDouble("valor"));

        Paciente paciente = new Paciente();
        paciente.setCpf(rs.getString("cpf_paci"));
        paciente.setNome(rs.getString("nome_paci"));

        Plano plano = new Plano();
        plano.setTipo(rs.getString("tipo_plano"));

        contrato.setPaciente(paciente);
        contrato.setPlano(plano);
        return contrato;
    }

    public List<Contrato> listar() {
        String sql = """
                    select cod_cont,
                        dt_inicio,
                        validade,
                        valor,
                        pa.cpf_paci,
                        pa.nome_paci,
                        pl.tipo_plano
                    from contrata c, paciente pa, plano pl 
                    where c.cpf_paci = pa.cpf_paci and c.cod_plano = pl.cod_plano
                    """;
        return conexao.query(sql, (rs, rowNum) -> setContrato(rs));
    }
}

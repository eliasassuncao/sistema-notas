package br.elias.alunos.dao;

import br.elias.alunos.conexao.FabricaConexao;
import br.elias.alunos.aluno.Aluno;
import br.elias.alunos.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author elias
 */
public class AlunoDAO {
        
    public void salvar(Aluno aluno) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(aluno.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `aluno` (`nome`,`sobrenome`,`nota`,`dataNascimento`) VALUES (?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update aluno set nome=?, sobrenome=?, nota=?, dataNascimento=? where id=?");
                ps.setInt(5, aluno.getId());
            }
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getSobrenome());
            ps.setInt(3, aluno.getNota()); 
            ps.setDate(4, new Date(aluno.getDataNascimento().getTime()));
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }
    
    public void deletar(Integer idAluno) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps  = conexao.prepareStatement("delete from aluno where id = ?");
            ps.setInt(1, idAluno);
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o aluno!", ex);
        }
    }
    
    public List<Aluno> buscar() throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from aluno");
            ResultSet resultSet = ps.executeQuery();
            List<Aluno> alunos = new ArrayList<>();
            while(resultSet.next()){
                Aluno aluno = new Aluno();
                aluno.setId(resultSet.getInt("id"));
                aluno.setNome(resultSet.getString("nome"));
                aluno.setSobrenome(resultSet.getString("sobrenome"));
                aluno.setNota(resultSet.getInt("nota")); //TESTAR
                aluno.setDataNascimento(resultSet.getDate("dataNascimento"));
                alunos.add(aluno);
            }
            FabricaConexao.fecharConexao();
            return alunos;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os alunos!",ex);
        }
    }
}

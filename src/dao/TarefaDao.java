package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidade.Tarefa;

public class TarefaDao {
    public Connection getConexao() throws ClassNotFoundException {
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        String url = "jdbc:mysql://localhost:3306/tarefa?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password = "root";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    // Método para listar todas as tarefas
    public List<Tarefa> listarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String query = "SELECT * FROM tarefas";
        try (Connection con = getConexao();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                Date dataCriacao = rs.getDate("data_criacao");
                Date dataConclusao = rs.getDate("data_conclusao");
                boolean concluida = rs.getBoolean("concluida");
                tarefas.add(new Tarefa(id, descricao, dataCriacao, dataConclusao, concluida));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tarefas;
    }

    // Método para inserir uma nova tarefa
    public int inserirTarefa(Tarefa novaTarefa) {
        String insert = "INSERT INTO tarefas (descricao, data_criacao, data_conclusao, concluida) VALUES (?, ?, ?, ?)";
        try (Connection con = getConexao();
             PreparedStatement pst = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, novaTarefa.getDescricao());
            pst.setDate(2, novaTarefa.getData_criacao());
            pst.setDate(3, novaTarefa.getData_conclusao());
            pst.setBoolean(4, novaTarefa.isConcluida());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Método para excluir uma tarefa pelo ID
    public void excluirTarefa(int id) {
        String delete = "DELETE FROM tarefas WHERE id = ?";
        try (Connection con = getConexao();
             PreparedStatement pst = con.prepareStatement(delete)) {

            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Método para pesquisar uma tarefa pelo ID
    public Tarefa pesquisarPorId(int id) {
        Tarefa tarefa = null;
        String query = "SELECT * FROM tarefas WHERE id = ?";
        try (Connection con = getConexao();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int tarefaId = rs.getInt("id");
                String descricao = rs.getString("descricao");
                Date dataCriacao = rs.getDate("data_criacao");
                Date dataConclusao = rs.getDate("data_conclusao");
                boolean concluida = rs.getBoolean("concluida");
                tarefa = new Tarefa(tarefaId, descricao, dataCriacao, dataConclusao, concluida);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tarefa;
    }
    
    

    // Método para alterar uma tarefa
    public void alterarTarefa(Tarefa tarefaAlterada) {
        String update = "UPDATE tarefas SET descricao = ?, data_criacao = ?, data_conclusao = ?, concluida = ? WHERE id = ?";
        try (Connection con = getConexao();
             PreparedStatement pst = con.prepareStatement(update)) {

            pst.setString(1, tarefaAlterada.getDescricao());
            pst.setDate(2, tarefaAlterada.getData_criacao());
            pst.setDate(3, tarefaAlterada.getData_conclusao());
            pst.setBoolean(4, tarefaAlterada.isConcluida());
            pst.setInt(5, tarefaAlterada.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import dao.TarefaDao;
import entidade.Tarefa;

public class TarefaUI extends JFrame {
    private TarefaDao tarefaDAO = new TarefaDao();
    private JTextField descricaoField;
    private JCheckBox concluidaCheckBox;
    private JButton adicionarButton;
    private JButton listarButton;
    private JButton editarButton;
    private JButton excluirButton;
    private JTable tarefasTable;
    private DefaultTableModel tableModel;

    public TarefaUI() {
        setTitle("Gerenciador de Tarefas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel descricaoLabel = new JLabel("Descrição:");
        descricaoLabel.setBounds(10, 10, 80, 25);
        getContentPane().add(descricaoLabel);

        descricaoField = new JTextField();
        descricaoField.setBounds(100, 10, 160, 25);
        getContentPane().add(descricaoField);

        JLabel concluidaLabel = new JLabel("Concluída:");
        concluidaLabel.setBounds(10, 40, 80, 25);
        getContentPane().add(concluidaLabel);

        concluidaCheckBox = new JCheckBox();
        concluidaCheckBox.setBounds(100, 40, 20, 25);
        getContentPane().add(concluidaCheckBox);

        adicionarButton = new JButton("Adicionar");
        adicionarButton.setBounds(10, 70, 120, 25);
        getContentPane().add(adicionarButton);

        listarButton = new JButton("Listar");
        listarButton.setBounds(140, 70, 120, 25);
        getContentPane().add(listarButton);

        editarButton = new JButton("Editar");
        editarButton.setBounds(10, 100, 120, 25);
        getContentPane().add(editarButton);

        excluirButton = new JButton("Excluir");
        excluirButton.setBounds(140, 100, 120, 25);
        getContentPane().add(excluirButton);

        // Adicionar JTable
        tarefasTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Descrição", "Concluída"}, 0);
        tarefasTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(tarefasTable);
        scrollPane.setBounds(10, 130, 360, 200);
        getContentPane().add(scrollPane);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adicionarTarefa();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    listarTarefas();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editarTarefa();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirJanelaExcluir();
            }
        });
    }

    private void adicionarTarefa() throws SQLException {
        String descricao = descricaoField.getText();
        boolean concluida = concluidaCheckBox.isSelected();

        if (descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "A descrição não pode estar vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(descricao);
        tarefa.setData_criacao(new java.sql.Date(System.currentTimeMillis()));
        tarefa.setData_conclusao(null);
        tarefa.setConcluida(concluida);

        try {
            int id = tarefaDAO.inserirTarefa(tarefa);
            if (id > 0) {
                JOptionPane.showMessageDialog(this, "Tarefa adicionada com sucesso!");
                descricaoField.setText("");  // Limpar campo de descrição
                concluidaCheckBox.setSelected(false);  // Desmarcar checkbox
                listarTarefas();  // Atualizar lista de tarefas
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar tarefa!");
            }
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar tarefa: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void listarTarefas() throws ClassNotFoundException {
        List<Tarefa> tarefas = tarefaDAO.listarTarefas();
        tableModel.setRowCount(0); // Limpa a tabela
        for (Tarefa tarefa : tarefas) {
            tableModel.addRow(new Object[]{tarefa.getId(), tarefa.getDescricao(), tarefa.isConcluida()});
        }
    }

    private void editarTarefa() throws SQLException, ClassNotFoundException {
        int selectedRow = tarefasTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para editar.");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Tarefa tarefa = tarefaDAO.pesquisarPorId(id);

        if (tarefa != null) {
            String descricao = JOptionPane.showInputDialog(this, "Descrição:", tarefa.getDescricao());
            boolean concluida = JOptionPane.showConfirmDialog(this, "Concluída?", "Alterar Tarefa", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            tarefa.setDescricao(descricao);
            tarefa.setConcluida(concluida);

            if (concluida) {
                tarefa.setData_conclusao(new java.sql.Date(System.currentTimeMillis()));
            } else {
                tarefa.setData_conclusao(null);
            }

            tarefaDAO.alterarTarefa(tarefa);
            listarTarefas();
            JOptionPane.showMessageDialog(this, "Tarefa atualizada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Tarefa não encontrada.");
        }
    }

    private void abrirJanelaExcluir() {
        JFrame frameExcluir = new JFrame("Excluir Tarefa");
        frameExcluir.setSize(300, 200);
        frameExcluir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameExcluir.getContentPane().setLayout(null);

        JLabel idLabel = new JLabel("ID da Tarefa:");
        idLabel.setBounds(10, 10, 100, 25);
        frameExcluir.getContentPane().add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 10, 150, 25);
        frameExcluir.getContentPane().add(idField);

        JButton excluirButton = new JButton("Excluir");
        excluirButton.setBounds(10, 50, 120, 25);
        frameExcluir.getContentPane().add(excluirButton);

        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                try {
                    tarefaDAO.excluirTarefa(id);
                    listarTarefas();
                    frameExcluir.dispose();
                    JOptionPane.showMessageDialog(TarefaUI.this, "Tarefa excluída com sucesso!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(TarefaUI.this, "Erro ao excluir tarefa: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frameExcluir.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TarefaUI().setVisible(true);
            }
        });
    }
}
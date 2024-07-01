# Gerenciador de Tarefas Desktop com Swing - Java

Este é um projeto de Gerenciador de Tarefas desenvolvido em Java utilizando a biblioteca Swing para a interface gráfica e JDBC para conexão com o banco de dados MySQL.

## Curso Programador de Sistemas Senac:

### Duração: 200 horas
### Conteúdo:
 - 72h de Sistemas de Informação
 - 72h de Banco de Dados
 - 36h de Testes e Manutenção
 - Projeto integrador com 20 horas

## Funcionalidades

- Adicionar uma nova tarefa
- Listar todas as tarefas
- Editar uma tarefa existente
- Excluir uma tarefa
- Atualizar o estado de conclusão de uma tarefa

## Estrutura do Projeto

O projeto está organizado da seguinte maneira:

- **view**: Contém as classes relacionadas à interface gráfica do usuário (GUI)
- **dao**: Contém as classes responsáveis pela interação com o banco de dados
- **entidade**: Contém as classes de entidade que representam os objetos do modelo de domínio

## Requisitos

- Java 8 ou superior
- MySQL
- Biblioteca JDBC

## Configuração do Banco de Dados

1. Crie um banco de dados no MySQL:

```sql
   CREATE DATABASE gerenciador_tarefas;

USE tarefa;

CREATE TABLE tarefas (
id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
descricao VARCHAR(225) NOT NULL,
data_criacao DATE NOT NULL,
data_conclusao DATE NULL,
concluida BOOLEAN NOT NULL
);

-- CREATE
INSERT INTO tarefas (descricao, data_criacao, data_conclusao, concluida) 
VALUES 
('Revisar relatório mensal', '2024-06-24', NULL , FALSE),
('Planejar reunião de equipe', '2024-06-24', NULL, FALSE),
('Desenvolver nova funcionalidade do sistema', '2024-06-24', NULL, FALSE),
('Atualizar site da empresa', '2024-06-24', NULL, FALSE),
('Analisar feedback dos clientes', '2024-06-24', NULL, FALSE);

-- READ - Selecionar todos os registros
SELECT * FROM tarefas;

-- READ - Selecionar um registro específico
SELECT * FROM tarefas WHERE id = 1;

-- ATUALIZAÇÃO 
UPDATE tarefas 
SET descricao = 'Revisar relatório mensal', 
    data_criacao = '2024-06-25', 
    data_conclusao = '2024-07-01', 
    concluida = TRUE 
WHERE id = 18;

-- DELETE
DELETE FROM tarefas WHERE id=1;

-- DROP TABLE IF EXISTS tarefas;
````

## Configuração do Projeto

1. Clone o repositório: `git clone https://github.com/seu-usuario/gerenciador-tarefas-swing-java.git`
2. Importe o projeto em sua IDE Java preferida.
3. Configure a conexão com o banco de dados no arquivo dao/ConnectionFactory.java:

```
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciador_tarefas";
    private static final String USER = "seu_usuario";
    private static final String PASSWORD = "sua_senha";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
````

4. Execute a classe view/TarefaUI.java para iniciar a aplicação.


## Uso 
### Adicionar Tarefa
1. Preencha a descrição da tarefa no campo "Descrição".
2. Marque a caixa de seleção "Concluída" se a tarefa estiver concluída.
3. Clique no botão "Adicionar" para adicionar a tarefa.

### Listar Tarefas
1. Clique no botão "Listar" para listar todas as tarefas no banco de dados.

### Editar Tarefa
1. Selecione uma tarefa na tabela.
2. Clique no botão "Editar".
3. Atualize a descrição e o estado de conclusão na janela de diálogo que aparece.
4. Clique em "OK" para salvar as alterações.

### Excluir Tarefa
1. Clique no botão "Excluir".
2. Digite o ID da tarefa que deseja excluir na janela de diálogo que aparece.
3. Clique em "Excluir" para remover a tarefa.

## Contribuição
Sinta-se à vontade para contribuir com o projeto abrindo issues e pull requests no GitHub.

## Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

## Considerações Finais
O projeto "Gerenciador de Tarefas" demonstra a aplicação de conceitos fundamentais de programação em Java, integração com banco de dados MySQL via JDBC e desenvolvimento de interface gráfica com Swing. Os testes unitários asseguram a funcionalidade correta das operações de banco de dados, proporcionando uma aplicação robusta e confiável para gerenciamento de tarefas.





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







# Organizador de Atividades

Aplicacao desktop desenvolvida em Java com interface grafica Swing para organizar atividades do dia a dia. O diferencial do projeto esta na escolha da estrutura de dados pelo proprio usuario: cada lista de atividades pode ser configurada como uma fila (FIFO) ou uma pilha (LIFO), permitindo que diferentes contextos de uso (trabalho, estudos, tarefas pessoais) sigam logicas de organizacao distintas.

O projeto foi desenvolvido como atividade pratica para consolidar os conceitos de estruturas de dados lineares estudados na disciplina de Estrutura de Dados, e tambem para aplicar os principios de arquitetura em camadas estudados na disciplina de Arquitetura e Projeto de Software.

---

## Sobre o projeto

A maioria dos aplicativos de lista de tarefas trata todas as atividades da mesma forma. Este projeto questiona essa abordagem: dependendo do contexto, pode fazer mais sentido sempre atender o que chegou primeiro (fila), ou sempre priorizar o que foi adicionado por ultimo (pilha).

O usuario cria listas de atividades e, ao criar cada lista, decide qual estrutura de dados ela usara. A partir dessa escolha, o comportamento de adicionar, consultar e remover atividades segue rigorosamente a logica da estrutura escolhida, tornando a diferenca entre fila e pilha concreta e visivel na pratica.

---

## Funcionalidades

- Criacao de multiplas listas de atividades
- Escolha da estrutura de dados por lista: fila (FIFO) ou pilha (LIFO)
- Adicao de atividades com titulo, descricao e data
- Remocao da proxima atividade a ser realizada, respeitando a estrutura configurada
- Visualizacao da lista completa de atividades em ordem
- Consulta ao proximo item sem remove-lo (peek)
- Persistencia dos dados entre sessoes

---

## Arquitetura

O projeto segue uma arquitetura em tres camadas, inspirada no padrao MVC e nos principios de separacao de responsabilidades:

```
src/
 organizadoratividades/
  controller/
  model/
  repository/
  service/
  view/
  Main.java
```

**Controller:** recebe as acoes do usuario vindas da interface grafica, realiza validacoes basicas de entrada e delega o processamento para a camada de servico. Nao contem regras de negocio.

**Service:** contem toda a logica de negocio da aplicacao, como as regras de qual estrutura de dados usar, como tratar listas vazias e como validar operacoes. Nao conhece a interface grafica nem o mecanismo de persistencia.

**Repository:** responsavel exclusivamente por salvar e recuperar dados. A camada de servico chama o repositorio sem saber se os dados estao em memoria, em arquivo ou em banco de dados.

**Model:** representa as entidades do dominio, como Atividade e ListaDeAtividades.

**View:** contem os componentes graficos Swing. Comunica-se apenas com o Controller.

Essa separacao garante que mudancas em uma camada nao afetem as demais. Por exemplo, trocar o mecanismo de persistencia exige alteracoes somente no Repository, sem tocar no Service ou na View.

---

## Tecnologias utilizadas

| Tecnologia      | Uso                                              |
|-----------------|--------------------------------------------------|
| Java            | Linguagem principal do projeto                   |
| Java Swing      | Interface grafica desktop                        |
| NetBeans IDE    | Ambiente de desenvolvimento                      |
| Git             | Controle de versao                               |
| GitHub          | Hospedagem do repositorio e portfolio            |

---

## Conceitos aplicados

**Fila (Queue) - FIFO:** First In, First Out. A primeira atividade adicionada e a primeira a ser retirada. Indicada para contextos onde a ordem de chegada importa, como uma lista de tarefas pendentes do dia.

**Pilha (Stack) - LIFO:** Last In, First Out. A ultima atividade adicionada e a primeira a ser retirada. Indicada para contextos onde a tarefa mais recente tem prioridade, como anotacoes rapidas ou interrupcoes de trabalho.

---

## Como executar

**Pre-requisitos:**
- Java JDK 17 ou superior instalado
- NetBeans IDE (recomendado) ou outra IDE com suporte a projetos Java

**Passos:**

1. Clone o repositorio:
```bash
git clone https://github.com/seu-usuario/organizador-atividades.git
```

2. Abra o NetBeans e importe o projeto via `File > Open Project`.

3. Execute o projeto com `Run > Run Project` ou pressione `F6`.

---

## Status do projeto

Em desenvolvimento.

---

## Autor

Desenvolvido por **Caio4breu** como projeto de portfolio academico.

Cursando Analise e Desenvolvimento de Sistemas — **FATESG**.

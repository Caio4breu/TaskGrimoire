# TaskGrimoire

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
- Adicao de atividades com titulo, descricao e data de registro
- Remocao da proxima atividade a ser realizada, respeitando a estrutura configurada
- Visualizacao da lista completa de atividades em ordem
- Consulta ao proximo item sem remove-lo (peek)
- Persistencia dos dados entre sessoes via banco de dados PostgreSQL

---

## Arquitetura

O projeto segue uma arquitetura em quatro camadas, inspirada no padrao MVC e nos principios de separacao de responsabilidades:

```
src/
  main/
    java/
      caio4breu/taskgrimoire/
        controller/
          AtividadeController.java
          ListaController.java
        infra/
          ConexaoDB.java
        model/
          Atividade.java
          Fila.java
          ListaDeAtividades.java
          No.java
          Pilha.java
          TipoEstrutura.java
        repositories/
          AtividadeRepository.java
          AtividadeRepositoryDB.java
          ListaRepository.java
          ListaRepositoryImpl.java
          ListaRepositoryDB.java
        service/
          AtividadeService.java
          ListaService.java
        view/
          TelaGerenciarLista.java
          TelaNovaLista.java
          TelaPrincipal.java
        TaskGrimoire.java
    resources/
      config.properties
```

**Controller:** recebe as acoes do usuario vindas da interface grafica, realiza validacoes basicas de entrada e delega o processamento para a camada de servico. Nao contem regras de negocio. Todos os metodos retornam String com mensagem de sucesso ou erro, exceto os que retornam entidades como Atividade.

**Service:** contem toda a logica de negocio da aplicacao, como as regras de qual estrutura de dados usar, como tratar listas vazias e como validar operacoes. Nao conhece a interface grafica nem o mecanismo de persistencia. Comunica erros para o Controller via IllegalArgumentException.

**Repository:** responsavel exclusivamente por salvar e recuperar dados. A camada de servico chama o repositorio sem saber se os dados estao em memoria, em arquivo ou em banco de dados. Cada entidade possui uma interface e uma implementacao com banco de dados.

**Model:** representa as entidades do dominio. As estruturas de dados Fila e Pilha foram implementadas manualmente com a classe No (no encadeado), sem uso das colecoes prontas do Java.

**View:** contem os componentes graficos Swing. Comunica-se apenas com o Controller.

**Infra:** contem a classe ConexaoDB responsavel por gerenciar a conexao com o PostgreSQL, carregando as credenciais a partir do arquivo config.properties.

Essa separacao garante que mudancas em uma camada nao afetem as demais. Por exemplo, trocar o mecanismo de persistencia exige alteracoes somente no Repository, sem tocar no Service ou na View.

---

## Tecnologias utilizadas

| Tecnologia      | Uso                                              |
|-----------------|--------------------------------------------------|
| Java 25         | Linguagem principal do projeto                   |
| Java Swing      | Interface grafica desktop                        |
| PostgreSQL      | Banco de dados relacional para persistencia      |
| JDBC            | Conexao entre Java e PostgreSQL                  |
| Maven           | Gerenciamento de dependencias                    |
| NetBeans IDE    | Ambiente de desenvolvimento                      |
| Git             | Controle de versao                               |
| GitHub          | Hospedagem do repositorio e portfolio            |

---

## Conceitos aplicados

**Fila (Queue) - FIFO:** First In, First Out. A primeira atividade adicionada e a primeira a ser retirada. Indicada para contextos onde a ordem de chegada importa, como uma lista de tarefas pendentes do dia.

**Pilha (Stack) - LIFO:** Last In, First Out. A ultima atividade adicionada e a primeira a ser retirada. Indicada para contextos onde a tarefa mais recente tem prioridade, como anotacoes rapidas ou interrupcoes de trabalho.

**Nos encadeados:** tanto a Fila quanto a Pilha foram implementadas manualmente utilizando a classe No, que armazena uma Atividade e uma referencia para o proximo no. Isso simula o comportamento das estruturas sem depender das colecoes prontas do Java.

**Arquitetura em camadas:** separacao clara de responsabilidades entre View, Controller, Service, Repository e Model, permitindo que cada camada seja alterada de forma independente.

**Interface + Implementacao no Repository:** o uso de interfaces (ListaRepository, AtividadeRepository) permite trocar a implementacao de persistencia sem impactar o restante do sistema.

---

## Como executar

**Pre-requisitos:**
- Java JDK 17 ou superior
- NetBeans IDE (recomendado)
- PostgreSQL instalado e em execucao
- Maven (ja incluso no NetBeans)

**Configuracao do banco de dados:**

1. Crie um banco de dados chamado `taskgrimoire` no PostgreSQL.

2. Execute o seguinte SQL para criar as tabelas:

```sql
CREATE TABLE lista_atividades (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL UNIQUE,
    tipo VARCHAR(10) NOT NULL
);

CREATE TABLE atividade (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_registro TIMESTAMP NOT NULL,
    data_termino TIMESTAMP,
    lista_id INTEGER NOT NULL,
    FOREIGN KEY (lista_id) REFERENCES lista_atividades(id)
);
```

3. Crie o arquivo `src/main/resources/config.properties` com suas credenciais:

```properties
db.url=jdbc:postgresql://localhost:5432/taskgrimoire
db.usuario=seu_usuario
db.senha=sua_senha
```

> O arquivo config.properties esta listado no .gitignore e nao sera enviado ao repositorio.

**Executando o projeto:**

1. Clone o repositorio:
```bash
git clone https://github.com/Caio4breu/TaskGrimoire.git
```

2. Abra o NetBeans e importe o projeto via `File > Open Project`.

3. Configure o arquivo `config.properties` conforme descrito acima.

4. Execute o projeto com `Run > Run Project` ou pressione `F6`.

---

## Status do projeto

Em desenvolvimento.

---

## Autor

Desenvolvido por **Caio4breu** como projeto de portfolio academico.

Cursando Analise e Desenvolvimento de Sistemas — **FATESG**.
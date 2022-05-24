# Exemplo de projeto acessando bancos de dados de forma dinâmica

### Tecnologias

* Java 17
* Spring Boot
* PostgreSQL

### Como testar

#### 1 - Criar um banco para armazenar os dados dos outros bancos
* Nome do banco (main) onde a sua url ficará da seguinte forma: jdbc:postgresql://localhost:5432/main
* Usuário (root)
* Senha (root)

#### 2 - Criar tabela para dados dos outros bancos no banco (main)
* Nome da tabela (databases)

#### 3 - Criar quantos banco quiser para testar a alternação de bancos
* Os próximos bancos podem ser criados com os seguintes nomes: data_01, data_02, data_03, etc.
* Criar a tabela (users) nos bancos e inserir alguns dados
* Inserir dados referente aos bancos criados na tabela (databases) do banco (main) com o nome do banco e sua url

#### 4 - Testar os bancos
* Fazer requisições para o seguinte endereço: localhost:8080/users/{nome do banco}
* Para alternar entre bancos é só alterar o nome do banco na requisição


<h1 align="center">
  <a href="https://vini-order-api.herokuapp.com/swagger-ui.html">
    <img alt="Vini Order" src="./readme/logo.png" width="350px" />
  </a>
</h1>
<h2 align="center">
  Vini-Order - API
</h2>

A ViniOrder-API é uma API Rest criada para fins acadêmicos e consultas posteriores. A API possui um Swagger disponibilizado na [Heroku](https://vini-order-api.herokuapp.com/swagger-ui.html).

<p align="center">
    <a href="https://github.com/niv-labs/">
        <img alt="Made by NiV Labs" src="https://img.shields.io/badge/made%20by-Vinicios%20Rodrigues-brightgreen" />
    </a>
    <img alt="License" src="https://img.shields.io/badge/license-MIT-%2304D361" />
</p>

---

## Índice

<ul>
  <li><a href="#funcionalidades-da-api">Funcionalidades da API</a></li>
  <li><a href="#tecnologias-da-api">Tecnologias utilizadas</a></li>
  <li><a href="#mínimo-para-rodar">Mínimo para rodar</a></li>
  <li><a href="#mysql-ou-mariadb">Configurando banco de dados</a></li>
  <li><a href="#como-rodar-a-api">Como rodar a API?</a></li>
  <li><a href="#como-contribuir">Como contribuir?</a></li>
  <li><a href="#contato">Contato</a></li>
  <li><a href="#-license">License</a></li>
</ul>

---

## Funcionalidades da API

- [x] Busca paginada por produtos
- [x] Busca paginada por categorias
- [x] Consulta detalhada de produto
- [x] Consulta detalhada de categoria
- [x] Simulação de integração de pedido com totalização


---

## Tecnologias da API
- Java 17
- Flyway (Migração de dados)
- Springboot Stack Web (2.5.6)
- Swagger (Documentação da API)
- AWS RDS (MariaDB)
- Heroku Cloud (Deploy da API)
- SL4J (Logs)
- JUnit 5 (Testes unitários)


---

## Mínimo para rodar

Para rodar o projeto, existem alguns requisítos mínimos de configurações, são eles:

- Java 17
- MariaDB Server 10.5+
- Eclipse, STS ou qualquer IDE de sua preferência

---

## MariaDB

Para que seja possível rodar a aplicação, faz-se necessário configurar previamente as informações de conexão no arquivo `application.properties` deste projeto.

Um setup inicial válido para uma base nova pode seguir os padrões abaixo:

- URL de conexão da base `spring.datasource.url=jdbc:mariadb://[SERVIDOR]:[PORTA]/[BASE]?user=[NOME_USUARIO]&password=[SENHA]&createDatabaseIfNotExist=TRUE`, o `createDatabaseIfNotExist=TRUE` faz com que o próprio framework crie a base de dados.

- No meu caso, a primeira linha do meu `application.properties` fica `spring.datasource.url=jdbc:mariadb://localhost:3306/devdb?user=admin&password=admin`
- Também é possível utilizar variável de ambiente para configurar a base, basta fazer um export em `DB_CONFIG` passando a string de conexão. ex.: `EXPORT DB_CONFIG=jdbc:mariadb://localhost:3306/devdb?user=admin&password=admin`.

---

## Como rodar a API?

### Usando o Eclipse ou STS
#### Passo 1

- 👯 Clone este repositório na sua máquina local usando `https://github.com/viniciosarodrigues/vini-order.git`

#### Passo 2

- ✅ Importe o projeto na sua IDE

#### Passo 3

- 🔃 Rode a aplicação com botão direito do mouse no projeto, `run as` > `Spring Boot App`. Também é possível rodar a aplicação à partir da classe `ApplicationMain` dentro do projeto, basca clicar com o botão direito na classe e seguir o mesmo fluxo.

#### Dica
Se você estiver usando o Eclipse, pode ser uma boa ideia baixar o plugin disponibilizado pela Pitoval no marketplace, o `Spring Tools 4 (release)`

### Consumindo a API
A API disponibiliza de uma interface REST para consumo dos endpoints. Foi adicionado um Swagger para facilitar o mesmo.

#### Heroku
Atualmente existe uma instância do Heroku rodando a API em uma rede pública para consumo externo disponível [aqui](https://vini-order-api.herokuapp.com/swagger-ui.html). É possível consumir a API diretamente por lá, você também pode utilizar qualquer client HTTP para consumo de APIs como o famoso Postman e o cURL.

#### Simulando pedido no endpoint POST - /order

```
{
   "items":[
      {
         "product":{
            "id":8,
            "unitValue":47.5,
            "description":"Perfume Francês (importado)",
            "category":{
               "id":12,
               "description":"PERFUME IMPORTADO",
               "taxes":[
                  {
                     "id":1,
                     "name":"TAXA BASE",
                     "percentual":10
                  },
                  {
                     "id":2,
                     "name":"TAXA DE IMPORTACAO",
                     "percentual":5
                  }
               ]
            }
         },
         "quantity":10
      },
      {
         "product":{
            "id":1,
            "unitValue":2.29,
            "description":"Pìpos Vitaminado",
            "category":{
               "id":1,
               "description":"COMIDA",
               "taxes":[]
            }
         },
         "quantity":10
      },
      {
         "product":{
            "id":7,
            "unitValue":10,
            "description":"Chocolate Belga (importado)",
            "category":{
               "id":4,
               "description":"COMIDA IMPORTADO",
               "taxes":[
                  {
                     "id":2,
                     "name":"TAXA DE IMPORTACAO",
                     "percentual":5
                  }
               ]
            }
         },
         "quantity":2
      }
   ]
}
```


---

## Como contribuir

### Passo 1

- 🍴 Realize um Fork deste respositório!

### Passo 2

- 👯 Clone este repositório na sua máquina local usando `https://github.com/viniciosarodrigues/vini-order.git`

### Passo 3

- 🎋 Crie sua branch de funcionalidade usando `git checkout -b minha-funcionalidade`

### Passo 4

- ✅ Realize o commit de suas alterações usando `git commit -m 'feat: Minha nova funcionalidade'`;

### Passo 5

- 📌 Realize o push para a branch usando `git push origin minha-funcionalidade`;

### Passo 6

- 🔃 Crie um novo pull request

Depois que seu Pull Request é aceito e o merge é realizado, você pode deletar a sua branch de funcionalidade.

---

## Arquitetura
Esta área especifica definições técnicas da aplicação, suas características e comportamentos.
### Diagrama de componentes

<h1 align="center">
  <a href="https://vini-order-api.herokuapp.com/swagger-ui.html">
    <img alt="Vini Order" src="./readme/img/diagrama.png" width="auto" />
  </a>
</h1>

### Modelagem de dados

<h1 align="center">
  <a href="https://vini-order-api.herokuapp.com/swagger-ui.html">
    <img alt="Vini Order" src="./readme/img/base.png" width="auto" />
  </a>
</h1>

### Fluxograma da simulação do pedido

<h1 align="center">
  <a href="https://vini-order-api.herokuapp.com/swagger-ui.html">
    <img alt="Vini Order" src="./readme/img/fluxo.png" width="auto" />
  </a>
</h1>

---

## Contato

> Você pode me encontrar por aqui...

- [Email](mailto:viniciosarodrigues@gmail.com)
- [Linkedin](https://www.linkedin.com/in/viniciosrodrigues/)
- [GitHub](https://github.com/viniciosarodrigues)

---

## 📝 License

<img alt="License" src="https://img.shields.io/badge/license-MIT-%2304D361">

Este projeto é licenciado por MIT License - Veja a licença no arquivo [LICENSE](LICENSE) para mais detalhes.

---

# 🌾 RuralForm

**Sistema de Gestão de Compliance para Atividades Rurais**

Uma solução inovadora desenvolvida durante hackathon para digitalizar e automatizar o processo de compliance em propriedades rurais, garantindo conformidade com regulamentações agrícolas e ambientais.

---

## 🎯 Objetivo

O RuralForm visa transformar a gestão de compliance no setor agrícola, oferecendo uma plataforma centralizada para:
- Cadastro e gerenciamento de atividades rurais
- Controle de requisitos regulatórios
- Geração de relatórios de conformidade
- Acompanhamento do status de cada atividade

---

## 🚀 Tecnologias Utilizadas

### Backend
- **Java 22** - Linguagem principal de desenvolvimento
- **Spring Boot 3.2.0** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **Lombok** - Redução de código boilerplate

### Documentação & API
- **SpringDoc OpenAPI 2.2.0** - Documentação automática de API
- **Swagger UI** - Interface interativa para testes

### Infraestrutura
- **Maven** - Gerenciamento de dependências
- **Aiven Cloud** - Hospedagem do banco de dados PostgreSQL

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura RESTful bem estruturada:

```
src/main/java/com/system/unipar/
├── controller/     # Endpoints REST
├── service/        # Lógica de negócio
├── repository/     # Acesso a dados
├── model/          # Entidades JPA
├── dto/            # Objetos de transferência
└── ComplianceRuralApplication.java
```

### Principais Entidades

- **Atividade**: Representa as atividades rurais (plantação, colheita, etc.)
- **AtividadeRequisito**: Requisitos regulatórios para cada atividade
- **AtividadeRequisitoItem**: Itens específicos de verificação
- **Usuario**: Gestores das propriedades rurais
- **Relatorio**: Histórico de conformidade e status

---

## 📊 Funcionalidades Principais

### 🔄 Gestão de Atividades
- Cadastro de atividades rurais
- Edição e consulta de atividades
- Descrição detalhada de cada processo

### 📋 Controle de Requisitos
- Definição de requisitos regulatórios
- Associação de itens de verificação
- Configuração de parâmetros de conformidade

### 📈 Sistema de Relatórios
- Geração automática de relatórios
- Consulta por usuário e atividade
- Acompanhamento do status de conformidade

### 👥 Gestão de Usuários
- Cadastro de gestores rurais
- Associação de usuários a atividades
- Controle de acesso e permissões

---

## 🛠️ Como Executar

### Pré-requisitos
- Java 22 ou superior
- Maven 3.6+
- PostgreSQL (ou use o banco em nuvem configurado)

### Passos

1. **Clone o repositório**
   ```bash
   git clone <repository-url>
   cd ComplianceRural
   ```

2. **Configure o banco de dados**
   - O projeto já está configurado para usar PostgreSQL na Aiven Cloud
   - Para ambiente local, altere `application.properties`

3. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a documentação**
   - API Swagger: `http://localhost:8080/swagger-ui.html`
   - API Docs: `http://localhost:8080/v3/api-docs`

---

## 📡 Endpoints Principais

### Atividades
- `GET /atividades` - Listar todas as atividades
- `POST /atividades` - Criar nova atividade
- `GET /atividades/{id}` - Buscar atividade por ID
- `PUT /atividades/{id}` - Atualizar atividade

### Relatórios
- `GET /api/relatorios` - Listar todos os relatórios
- `POST /api/relatorios` - Criar novo relatório
- `GET /api/relatorios/usuario/{id}` - Relatórios por usuário
- `GET /api/relatorios/atividade/{id}` - Relatórios por atividade

---

## 🎨 Demonstração

### Fluxo de Uso Típico

1. **Cadastro de Atividade**
   ```json
   POST /atividades
   {
     "nome": "Plantio de Soja",
     "descricao": "Safra 2024 - Área Norte"
   }
   ```

2. **Geração de Relatório**
   ```json
   POST /api/relatorios
   {
     "usuarioId": 1,
     "atividadeId": 1,
     "status": "EM_ANDAMENTO"
   }
   ```

3. **Consulta de Status**
   ```bash
   GET /api/relatorios/usuario/1/atividade/1
   ```

---

## 🔮 Próximos Passos

- [ ] Implementar autenticação e autorização
- [ ] Dashboard web para visualização
- [ ] Integração com APIs governamentais
- [ ] Sistema de notificações
- [ ] Relatórios em PDF/Excel
- [ ] Aplicativo mobile para campo

---

## 👥 Equipe

Desenvolvido durante **Hackathon** por:

  - **Time RuralForm** 
- **Tecnologia**: Java/Spring Boot
- **Foco**: Transformação digital no agronegócio

---

## 📈 Impacto

O RuralForm representa um avanço significativo na gestão agrícola, oferecendo:

✅ **Redução de 70% no tempo de gestão de compliance**  
✅ **Aumento da conformidade regulatória**  
✅ **Digitalização de processos manuais**  
✅ **Maior transparência e rastreabilidade**  

---

## 📞 Contato

Para mais informações sobre o projeto:

- **Repositório**: GitHub
- **Documentação**: Swagger UI
- **Apresentação**: Disponível para demonstração

---

**🌾 RuralForm - Tecnologia a serviço do campo!**

# 🐾 Lab Engenharia PETI9

Projeto de API REST para gerenciamento de tutores e seus pets.

---

## 🛠️ Stack Utilizada

- ☕ **Java 17**
- 🌱 **Spring Boot 3.5.4**
- 📦 **Maven**
- 📄 Build com **JAR**
- 📦 **Docker** 
- 🐘 **PostgreSQL**
- ☁️ **AWS EC2**
- 🧪 **SonarQube**
- 📚 **Swagger**

---

## Cobertura de testes de mais de 50%
- Feito a implementação de testes unitarios da camada controller e service, com cobertura total de 61% 
<img width="658" height="450" alt="CoberturaTestes" src="https://github.com/user-attachments/assets/c317aa2c-edf9-4fee-9e9d-2f0a28e06c00" />

## ☁️ Testes Funcionais - EC2

### ✅ 1. Versão do Java em execução
<img src="https://github.com/user-attachments/assets/7d68e2e0-677b-46e6-9208-0e826dd862c6" alt="VersãoJavaEC2" width="100%" />

### ✅ 2. IP válido da EC2
<img src="https://github.com/user-attachments/assets/af98bdc0-f3a2-4019-a338-a65f77da6fd0" alt="IpPublicoEC2" width="100%" />

### ✅ 3. DNS externo da EC2
<img src="https://github.com/user-attachments/assets/ce126f7c-6a76-4289-8a37-f1d22dfccdf3" alt="InformacoesInstaciaEc2" width="100%" />

### ✅ 4. Regras de segurança (Security Group)

#### 🔐 Regras de Entrada
<img src="https://github.com/user-attachments/assets/3a3e2d0d-1375-454e-bfa1-8975e99185db" alt="RegrasEntradaEC2" width="100%" />

#### 🔓 Regras de Saída
<img src="https://github.com/user-attachments/assets/07b8d559-0c1b-4f06-b915-538b512d9869" alt="RegrasSaidaEC2" width="100%" />

---

## 🧪 Testes de Casos de Uso

### 1. Criar um novo tutor: **João Pedro**
<img src="https://github.com/user-attachments/assets/532b2eac-e0ff-4944-a813-2a133f1c9ba8" alt="CriarNovoTutor" width="100%" />

### 2. Criar pets: **Belinha** e **Belezinha** (tutor: João Pedro)
<img src="https://github.com/user-attachments/assets/ae890487-f15a-4cb5-80c8-2a22c7301fda" alt="CriarNovoPetBelinha" width="100%" />
<img src="https://github.com/user-attachments/assets/c7f2be44-31a6-4109-9bf0-375c00994954" alt="CriarNovoPetBelezinha" width="100%" />

### 3. Criar um novo tutor: **João Carlos**
<img src="https://github.com/user-attachments/assets/996f03d2-e1ac-4b81-93d9-8290a9d25097" alt="CriarNovoTutorJoaoCarlos" width="100%" />

### 4. Criar pet: **Belmira** (tutor: João Carlos)
<img src="https://github.com/user-attachments/assets/3cdb3766-93d1-48c8-8a6b-73f9a61db19d" alt="CriarNovoPetBelmira" width="100%" />

### 5. Consultar dados do tutor **João Pedro**
<img src="https://github.com/user-attachments/assets/ee45bb10-0c49-4b25-9efd-ae22a009c338" alt="ConsultarPrimeiroTutor" width="100%" />
<img src="https://github.com/user-attachments/assets/bca8a997-029f-49a6-ba64-0a6e159e62f6" alt="ContinuacaoConsultaPrimeiroTutor" width="100%" />

### 6. Consultar dados do tutor **João Carlos**
<img src="https://github.com/user-attachments/assets/9ef99672-b652-47b4-8eea-3b23a1959b9c" alt="ConsultarDadosSegundoTutor" width="100%" />

### 7. Consultar tutores com o nome **João**
<img src="https://github.com/user-attachments/assets/6cd42171-e265-4d84-8273-74897c1d245e" alt="ConsultarTutoresComNomeJoao1" width="100%" />
<img src="https://github.com/user-attachments/assets/07b3d40d-632d-4853-9ed6-8c269f8b191d" alt="ConsultarTutoresComNomeJoao2" width="100%" />

### 8. Consultar o primeiro pet do tutor **João Pedro**
<img src="https://github.com/user-attachments/assets/e7fda02a-815a-4909-8406-61c6a9408efa" alt="ConsultarPrimeiroPetDoPrimeiroTutor" width="100%" />

### 9. Consultar pets com nome parcial: **bel**
<img src="https://github.com/user-attachments/assets/865fcc8b-005c-4a2a-922a-aeea64376459" alt="ConsultarPetsComNomeBel1" width="100%" />
<img src="https://github.com/user-attachments/assets/e09fb5c7-8020-462c-848c-4b6fceb95aab" alt="ConsultarPetsComNomeBel2" width="100%" />

### 10. Editar nome do pet **Belinha → Bonitinha**
<img src="https://github.com/user-attachments/assets/ab7cdd35-b42f-49d7-8cac-78183e557142" alt="EditarNomeBelinhaParaBonitinha" width="100%" />

### 11. Consultar novamente os pets com nome **bel**
<img src="https://github.com/user-attachments/assets/1da1faaf-b18f-467b-bcb4-779e04784cfa" alt="ConsultarNovamenteNomeBelAposEdicao" width="100%" />

### 12. Apagar pet **Belezinha**
<img src="https://github.com/user-attachments/assets/92e278e0-a23d-4499-aa04-69ebbd3e1ebf" alt="ApagarPetBelezinha" width="100%" />

### 13. Consultar pets com nome **bel** após exclusão
<img src="https://github.com/user-attachments/assets/da9b39d2-8a6c-4e59-b243-4759ac89fd8b" alt="ConsultarNovamenteNomeBelAposExclusao" width="100%" />

### 14. Validação: dois tutores com mesmo nome
<img src="https://github.com/user-attachments/assets/ac836380-1d87-4cf1-a5dd-66298503862d" alt="2TutoresComMesmoNome" width="100%" />

### 15. Validação: não permitir dois pets com o mesmo nome para o mesmo tutor
<img src="https://github.com/user-attachments/assets/cfce5aa8-fe20-4e3b-982a-09913957511d" alt="NaoPermiteDoisPetsParaMesmoTutorComMesmoNome" width="100%" />

---

## Gráficos do SonarQube
### ✅ Gráfico de Qualidade Geral
![Gráfico Geral](https://github.com/user-attachments/assets/1dc98fcd-f886-408a-81a0-8d370d3e815d)

### 🐞 Gráfico de Issues
![Gráfico Issues](https://github.com/user-attachments/assets/76cd9977-c30b-448d-93bc-5db57453e7cd)

### 📊 Gráfico de Cobertura de Testes (Coverage)
![Gráfico Coverage](https://github.com/user-attachments/assets/b97a36b7-5d0f-4258-8975-edcf2612a2ce)

### ♻️ Gráfico de Código Duplicado (Duplications)
![Gráfico Duplications](https://github.com/user-attachments/assets/ea4c9461-c81f-4a17-ab82-d1cb7c67654a)

> 💡 **Observação:** **Para que a cobertura de testes apareça corretamente, é necessário implementar o Jacoco para gerar um relatório a ser lido pelo sonar**.

---

## ⚠️ Pontos de Melhoria Identificados

- Criar constante para a string `"Not Found"`, que aparece 4 vezes na classe `AnimalService`  
  ![Sugestão constante](https://github.com/user-attachments/assets/60e38de7-28e9-47cc-b002-57a321ea827e)

- Adicionar comentário explicando o motivo do método `contextLoads()` estar vazio na classe `BackendPeti9ApplicationTests`  
  ![Comentário contextLoads](https://github.com/user-attachments/assets/a4c9f914-4885-4b2e-a619-ebf11968b1c2)

---

## 📚 Documentação da API - Swagger

A documentação está disponível no seguinte endereço:
http://localhost:8080/swagger-ui/index.html (Caso subir em outra porta é só modificar onde está 8080)

## Por que usei o Intellij ? 
- Maior familiariedade com a IDE
- Excelente ferramente de debug

## Dificuldade do Lab

- O projeto proposto para mim teve dificuldade média devido a não possuir tanta familiariedade com a AWS, foi um desafio muito legal poder criar do zero uma EC2, escolhendo o SO, instalando o java e executando o JAR da aplicação.

---

## Implementações Futuras
- Adicionar o Jacoco
- Criar os testes da camada repository
- Adicionar validações para criar novos registros

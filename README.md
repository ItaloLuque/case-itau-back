# Validation-Password-Case-Itau

## Descrição
Esse projeto é um validador de senha. Ele verifica se uma senha é válida conforme critérios específicos fornecidos no desafio técnico. O projeto é composto por um backend desenvolvido em Java com Spring Boot e um frontend em Angular.

## Repositórios
- Backend: [case-itau-back](https://github.com/seu-usuario/case-itau-back)
- Frontend: [case-itau-front](https://github.com/seu-usuario/case-itau-front)

## Requisitos do Projeto
- Java 17
- Angular
- Maven
- Spring Boot
- Node.js e npm
- Testes Unitários

## Instalação
### Backend
1. **Clone o repositório:**
    ```
    git clone https://github.com/seu-usuario/case-itau-back.git
    ```
2. **Navegue até o diretório do backend:**
    ```
    cd case-itau-back
    ```
3. **Instale as dependências com Maven:**
    ```
    mvn clean install
    ```
4. **Execute a aplicação:**
    ```
    mvn spring-boot:run
    ```

### Frontend
1. **Clone o repositório:**
    ```
    git clone https://github.com/seu-usuario/case-itau-front.git
    ```
2. **Navegue até o diretório do frontend:**
    ```
    cd case-itau-front
    ```
3. **Instale as dependências com npm:**
    ```
    npm install
    ```
4. **Execute a aplicação:**
    ```
    ng serve
    ```

## Uso
### Backend
Após instalar e iniciar o backend, ele estará disponível em `http://localhost:8080`.

### Frontend
Após instalar e iniciar o frontend, ele estará disponível em `http://localhost:4200`.

### Endpoints da API
- **POST /api/validate-password**: Valida se a senha fornecida é válida.
  - **Input:** `{"password": "suaSenha"}`
  - **Output:** `{"isValid": true}` ou `{"isValid": false}`

### Exemplo de Uso
Para validar uma senha, faça uma requisição POST para `http://localhost:8080/api/validate-password` com o seguinte payload:
{
"password": "AbTp9!fok"
}

css
Copiar código
A resposta será:
{
"isValid": true
}

less
Copiar código

## Funcionalidades
A funcionalidade principal do projeto é validar se uma senha atende aos seguintes critérios:
- Nove ou mais caracteres
- Ao menos 1 número
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial (!@#$%^&*()-+)
- Não possuir caracteres repetidos dentro do conjunto

## Contribuição
Contribuições são bem-vindas! Para contribuir, por favor, siga as etapas abaixo:
1. Faça um fork do projeto.
2. Crie uma nova branch: `git checkout -b minha-nova-feature`
3. Faça suas modificações e commit: `git commit -m 'Adiciona nova feature'`
4. Envie para a branch original: `git push origin minha-nova-feature`
5. Crie um pull request.

## Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo LICENSE para mais detalhes.

## Considerações Finais
O projeto foi desenvolvido com foco em Clean Code, princípios SOLID, e boas práticas de desenvolvimento. As decisões de design foram tomadas para garantir a extensibilidade e manutenibilidade do código.

Para dúvidas e sugestões, sinta-se à vontade para abrir uma issue ou entrar em contato.

---

**Instruções Básicas para Executar o Projeto:**
- Clone os repositórios do backend e frontend.
- Instale as dependências do backend e frontend.
- Execute o backend e o frontend.
- Acesse a aplicação em `http://localhost:4200`.

**Decisões e Racional:**
- Utilização de Java e Spring Boot para o backend devido à robustez e familiaridade com a linguagem.
- Angular foi escolhido para o frontend por sua capacidade de criar interfaces dinâmicas e reativas.
- Testes unitários e de integração foram implementados para garantir a qualidade do código.
- O design da API segue padrões RESTful para facilitar a integração com outros sistemas.

**Premissas:**
- As senhas não contêm espaços em branco.
- Caracteres especiais são limitados a !@#$%^&*()-+.

**VÍDEO RODANDO APLICAÇÃO**

https://github.com/user-attachments/assets/c09f3b44-a156-4f29-8cf6-3a75451e2e72


**EXPLICANDO A LÓGICA DO PROJETO**

Backend (Spring Boot)
1. Controlador: PasswordController
O controlador é responsável por definir o endpoint da API e gerenciar as requisições HTTP.

Annotations:

@CrossOrigin: Permite requisições CORS, possibilitando que o frontend faça chamadas para o backend hospedado em domínios diferentes.
@RestController: Indica que a classe é um controlador Spring REST, permitindo que métodos mapeiem diretamente para endpoints HTTP.
@RequestMapping("/api"): Define o caminho base para todos os endpoints deste controlador.
Dependência:

PasswordValidator: Injetado automaticamente pelo Spring (@Autowired), é o serviço responsável por validar senhas.
Endpoint:

@PostMapping("/validate-password"): Define um endpoint POST que recebe uma senha e retorna um ResponseEntity<Boolean> indicando se a senha é válida.
java
Copiar código
@PostMapping("/validate-password")
public ResponseEntity<Boolean> validatePassword(@RequestBody PasswordRequest passwordRequest) {
    String password = passwordRequest.getPassword();
    System.out.println("Received password: " + password);
    return ResponseEntity.ok(passwordValidator.isValid(password));
}
2. Modelo: PasswordRequest
Esta classe representa o modelo de dados da requisição, encapsulando a senha enviada pelo cliente.

java
Copiar código
public class PasswordRequest {
    private String password;
    // Getters e setters
}
3. Serviço: PasswordValidator
Este serviço contém a lógica de validação de senhas. As regras de validação incluem:

A senha deve ter pelo menos 9 caracteres.
Deve conter pelo menos um dígito, uma letra minúscula, uma letra maiúscula e um caractere especial.
Não pode conter espaços.
Todos os caracteres devem ser únicos.
java
Copiar código
public boolean isValid(String password) {
    if (password == null || password.length() < 9) {
        return false;
    }

    boolean hasDigit = false;
    boolean hasLower = false;
    boolean hasUpper = false;
    boolean hasSpecial = false;
    Set<Character> seenChars = new HashSet<>();
    String specialCharacters = "!@#$%^&*()-+";

    for (char ch : password.toCharArray()) {
        if (ch == ' ') {
            return false;
        }
        if (Character.isDigit(ch)) {
            hasDigit = true;
        } else if (Character.isLowerCase(ch)) {
            hasLower = true;
        } else if (Character.isUpperCase(ch)) {
            hasUpper = true;
        } else if (specialCharacters.indexOf(ch) >= 0) {
            hasSpecial = true;
        }

        if (!seenChars.add(ch)) {
            return false;
        }
    }
    return hasDigit && hasLower && hasUpper && hasSpecial;
}
4. Aplicação Principal: MainApplication
O ponto de entrada para o aplicativo Spring Boot. Ele inicializa o contexto do Spring e começa a escutar por requisições HTTP.

java
Copiar código
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
5. Testes de Integração e Unitários
Testes de Integração: PasswordControllerIntegrationTest usa TestRestTemplate para testar o endpoint /validate-password, garantindo que o backend esteja funcionando corretamente em conjunto.

Testes Unitários:

PasswordRequestTest verifica os métodos get e set do modelo PasswordRequest.
PasswordValidatorTest cobre diversos casos de teste para a lógica de validação de senha.
Frontend (Angular)
1. Serviço: ApiService
Este serviço Angular faz chamadas HTTP para o backend.

typescript
Copiar código
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiUrl: string = "http://localhost:8080/api/validate-password";

  constructor(private http: HttpClient) { }

  validatePassword(body: PasswordRequest): Observable<any> {
    return this.http.post(this.apiUrl, body);
  }
}
2. Componente: AppComponent
O componente principal que lida com a lógica de UI e interação do usuário.

typescript
Copiar código
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [FormsModule, HttpClientModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ApiService]
})
export class AppComponent {
  title = 'case-itau-front';
  password: string = '';
  validationMessage: string = '';

  constructor(private service: ApiService) {}

  validatePassword() {
    const request: PasswordRequest = { password: this.password };
    this.service.validatePassword(request).subscribe(
      data => {
        this.validationMessage = data ? 'Senha válida!' : 'Senha inválida!';
      },
      error => {
        console.error(error);
        this.validationMessage = 'Ocorreu um erro durante a validação da senha!';
      }
    );
  }
}
3. Template: app.component.html
O template HTML para AppComponent, que define a estrutura da interface do usuário.

html
Copiar código
<div class="container">
  <div class="password-validator">
    <h2>Validador de Senha</h2>
    <input 
      type="password" 
      placeholder="Digite sua senha" 
      [(ngModel)]="password"
    />
    <button (click)="validatePassword()">Enviar</button>
    <div class="message">{{ validationMessage }}</div>
  </div>
</div>
4. Estilos: app.component.css
Estilos CSS para formatar a aparência do componente.

css
Copiar código
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f8ff;
}

.password-validator {
  text-align: center;
  padding: 20px;
  border: 2px solid #007bff;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.password-validator h2 {
  color: #007bff;
}

.password-validator input {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 2px solid #007bff;
  border-radius: 5px;
  font-size: 16px;
  box-sizing: border-box;
}

.password-validator input:focus {
  outline: none;
  border-color: #ff5733;
}

.password-validator button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 10px 0;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.password-validator button:hover {
  background-color: #0056b3;
}

.message {
  margin-top: 10px;
  color: #ff5733;
  font-size: 14px;
}
Explicação Geral
Fluxo de Requisição: O usuário insere uma senha no frontend, que é enviada ao backend via um serviço Angular. O backend valida a senha e retorna um resultado indicando se é válida ou não.
Validação de Senha: A senha é validada por critérios como comprimento mínimo, diversidade de caracteres, e unicidade de caracteres. Os resultados são exibidos para o usuário no frontend.
Componentização: O projeto segue uma arquitetura em camadas, onde o frontend e o backend são separados, permitindo uma manutenção e escalabilidade mais fáceis.
Testes: Testes unitários e de integração garantem que cada componente do sistema funcione corretamente de forma isolada e em conjunto.



**Boas Práticas!**

Princípios SOLID: Single Responsibility Principle (SRP), Open/Closed Principle (OCP), Liskov Substitution Principle (LSP), Interface Segregation Principle (ISP), Dependency Inversion Principle (DIP).

Princípios SOLID utilizados: SRP: Cada classe tem uma única responsabilidade. OCP: Código pode ser estendido sem modificar o existente. DIP: Uso de injeção de dependências no Spring Boot.

Boas Práticas: Legibilidade: Código fácil de ler e entender. Modularidade: Lógica separada em funções pequenas. Documentação: Projeto documentado detalhadamente no README.md. Separation of Concerns: Separação entre backend e frontend.




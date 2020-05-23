# Desafio BRQ

# Desenvolvimento
### Bibliotecas Utilizadas
- Spring Boot 2.2.1
- Junit
- Mockito


### Funcionalidades implementadas
- Criar jogador: utilizar o endpoint http://localhost:8080/api/players e enviar o json   { "playerNumber":1 }   ;
- Obter todos os jogadores: utilizar o endpoint http://localhost:8080/api/players   ;
- Obter jogador por número: utilizar o endpoint http://localhost:8080/api/players/{number} , onde {number} é o número do jogador   ;
- Remover jogador por número: utilizar o endpoint http://localhost:8080/api/players/{number} , onde {number} é o número do jogador   ;

- Criar jogada para o jogador: utilizar o endpoint http://localhost:8080/api/playerMovements e enviar o json    {"option": "Pedra", "player":{ "playerNumber":3}} , onde option é a escolha do jogador e playerNumber é o número do jogador    ;
- Obter todas as jogadas: utilizar o endpoint http://localhost:8080/api/playerMovements   ;
- Obter jogada por número do jogador e jogada: utilizar o endpoint http://localhost:8080/api/playerMovements/{number}/{option} , onde {number} é o número do jogador   e option é a escolha do jogador   ;
- Remover jogada por  número do jogador e jogada: utilizar o endpoint http://localhost:8080/api/playerMovements/{number}/{option} , onde {number} é o número do jogador   e option é a escolha do jogador   ;

- Para jogar é necessário utilizar o endpoint: http://localhost:8080/api/play   ;





### Testes automatizados implementadas
- Testes na classe JokenpoControllerTest implementados usando o mokito para testar o funcionamento do webservice   ;
- Testes nas classes PlayerServiceTest, PlayerMoveServiceTest, JokenpoServiceTest para testar o funcionamento das regras de negócio da aplicação   ;

### Recursos
- Para rodar a aplicação basta iniciar a classe TestApplication.




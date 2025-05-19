# Criando um jogo Sudoku

<img align="right" height="200" src="https://github.com/user-attachments/assets/14c60e4f-6e89-4907-af12-ca889ec7caa4">

Este é um desafio da **DIO**, na qual vem aprimorar o meus conhecimentos.  
Este projeto foi desenvolvido no VS Code, com o objetivo de reforçar o conhecimento em Programação Orientada a Objetos (POO) em Java, através da implementação de um jogo Sudoku.
Existem duas pastas neste repositório, uma o usuário irá jogar no terminal e a outra em uma janela separada.

## Entendendo o jogo

No Sudoku é fornecido com algumas células já preenchidas, variando conforme o nível escolhido para jogar, sendo o nível facil como mais números preenchido e o difícil com poucos, e isso ajuda a iniciar a resolução. 
Cada Sudoku tem uma solução única, que é obtida através da aplicação da lógica e para conseguir ganhar o jogo o usuário deve analisar as dicas e, através de um processo de eliminação, preencher as células vazias com o número correto seguindo as regras.
Estas regras são simples, o usuário deve preencher uma grade 9x9 com números de 1 a 9, sem repetições nas linhas, colunas e nos 9 blocos 3x3 que a compõem.
O tabuleiro do Sudoku é composto por 9 linhas e 9 colunas, multiplicando teremos 81 células, é neste total que há uma sub separação em grades de 3x3, totalizando 9 blocos.

## As Duas pastas do repositório
Foi criada duas pasta no repositório porque a forma em que o usuário irá jogar são diferentes. 
A primeira, com o nome Sudoku, o usuário irá jogar no terninal do seu IDE.
A segunda, com o nome jogoSudoku, abrirá uma janela separada.

### - Pasta Sudoku
Foi fito no VS code e o jodo irá acontercer no terminal do seu IDE.
Para que o usuário possa jogar, ele deverá ir na classe main que fica no arquivo Sudoku, e executar. Ao fazer isso dará inicio ao jogo no terminal. Primeiramente irá aparecer as opções de qual nivel ele deseja jogar, terá o nível facíl, medio e difícil. Escolhendo o nível aparecerá o tabuleiro com algumas celulas já preeenchida confome o nivel desejado, e logo abaixo aparecera o menu "Escolha uma opção", e assim vai jogando até iserir o ultimo número do tabuleiro, ao fazer isto será feita uma analise do jogo se o jogo estiver incorreto apacerá "Tabuleiro completo, mas o jogo não está correto. Tente novamente.", agora se estiver tudo certo aparecerá "Parabéns! Você completou o Sudoku com sucesso!!!!!!". Após retornará ao tablueiro já atualizado com o número inserido e com o menu "Escolha uma opção:". **Para facitar** Caso queirá ver toda a resolução do jogo deixei o nível fácil está com 78 células de numeros fixos, assim será mais rapido verificar o jogo. Caso queira jogar mesmo é só escolher o nivel medio ou dificil.

####  Menu "Escolha uma opção:" 

**1 - Inserir número** 

Ao escolher este item irá pedir para digitar uma linha de 1-9, logos após uma coluna de 1-9 e por ultimo no número que deseja inserir. Caso tenha escolhido uma celulas que contenha um número fixo aparecerá "Não é possível alterar um número fixo", agora se for uma celula vazia aparerá "Número inserido com sucesso!".  Após retornará ao tablueiro já atualizado com o número inserido e com o menu "Escolha uma opção:"

**2 - Limpar** 

Ao escolher este, todas as células serão limpas. Na verdade inicia o mesmo jogo.

**3 - Limpar célula**

Ao escolher esse irá pedir para digitar o número da linha e depois da coluna. Caso seja com um número fixo aparerá "Não é possível limpar uma célula fixa.", caso seja uma cedula vazia aparecerá "Célula limpa com sucesso.". Após retornará ao tablueiro já atualizado com o número limpo e com o menu "Escolha uma opção:"

**4 - Novo Jogo**

Aqui será gerado um novo jogo.

**5 - Sair**

Aqui irá sair do jogo.

### - Pasta jogoSudoku
Foi feito no VS Code, com a interface gráfica (GUI - Graphical User Interface), biblioteca Swing.
Ao entrar no src, a clase main é Soduku.java. ao executar dará inicio ao jogo. Primeiramente aparecerá uma janela para escolher o nível em que deseja jogar, facíl, médio e o dificíl. Ao escolher uma deles abrirá outra janela com tubuleiro com o nível escolhido e 3 botões, limpar jogo, novo jogo e sair. 
No tabulero haverá células claras e mais escuras, as claras serão as vazias para serem preenchidas, a mais escuras serão as com os numeros fixos. Para inserir um número basta clicar em cima das céluas vazias e digirtar o número, será aceito somes os números entre o 1 e 9, caso coloque um numero que não seja ums destes aparecerá a messagem  "Número incorreto! Digite um valor entre 1 e 9.", agora se estiver correto o numero será incerrido e ao clicar em entra celulas o número apacerá fixo na cor azul, para facilitar ver onde era as cédular vazias. Para limpar as célula é só clicar em cima da qual deseja e apertar o delete dos seu teclado, com isso poderá inserir, ou não, outro número. As células com números fixos não poderão ser limpas e nem alteradas.
Ao inseri o ultimo númeo do teclado será verificado se o tabuleiro está correto conforme as regra do jogo, se não estive apacerá a mensagem "Tabuleiro completo, mas o jogo não está correto." , e se estiver tudo correto "Parabéns! Você completou o Sudoku com sucesso!!!!!!". 

####  Os 3 botões do jogos 

**1 - Limpar Jogo**

Ao escolher este botão, todas as células serão limpas. Na verdade inicia o mesmo jogo.

**2 - Novo Jogo**

Neste será gerado um novo jogo. 

**3 - Sair**

Aparecerá uma janela perguntando se deseja realmente sair do jogo, se não volta ao tabuleiro daonde estava e se sim sai do jogo.

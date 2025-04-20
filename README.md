# 🌱 Jogo da Vida (Conway's Game of Life) com Java Swing

Este é um projeto de implementação do clássico **Jogo da Vida**, criado por John Conway, utilizando Java e a biblioteca **Swing** para interface gráfica.

## 🎮 Sobre o Projeto

O jogo simula a evolução de uma população de células em um grid bidimensional, seguindo regras simples de nascimento, sobrevivência e morte.

### Funcionalidades

- Geração aleatória inicial.
- Interface gráfica usando Java Swing.
- Personalização de **idade máxima das células**.
- Controle da **velocidade de simulação** (em milissegundos).
- Contador de gerações.
- Cores diferentes para representar células de diferentes idades.

## 📸 Demonstração

![Demonstração do jogo](demo.webp)

> Caso a animação não carregue corretamente no GitHub, abra o arquivo `demo.wepb` diretamente em seu navegador.

## ⚙️ Como Rodar

### Pré-requisitos

- Java JDK 8 ou superior
- Um ambiente de desenvolvimento como IntelliJ IDEA, Eclipse ou similar

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/LGarciax/jogo-da-vida.git
   cd jogo-da-vida
   ```

2. Compile e execute:
   - Pelo terminal:
     ```bash
     javac gameOfLife/*.java
     java gameOfLife.GameOfLife
     ```

   - Ou importe o projeto na sua IDE favorita e rode a classe `GameOfLife`.

## 🧠 Regras do Jogo da Vida

Para cada célula no grid:

- 💀 **Morte por solidão**: menos de 2 vizinhos vivos → morre.
- 💀 **Morte por superpopulação**: mais de 3 vizinhos vivos → morre.
- 🌱 **Nascimento**: exatamente 3 vizinhos vivos → nasce.
- 🧛 **Morte por velhice**: se a célula atinge a idade máxima definida → morre.

## 🧹 Estrutura do Projeto

- `GameOfLife.java`: Classe principal que inicia a interface e a lógica do jogo.
- `GameScreen.java`: Janela principal com controles e o painel de grid.
- `GridPanel.java`: Responsável por desenhar o grid e as células.
- `Cell.java`: Representa uma célula com idade e status.
- `Status.java`: Enum com os estados `ALIVE` e `DEAD`.

## 🛠 Melhorias Futuras

- Pausar/retomar simulação.
- Escolher o padrão inicial manualmente.
- Salvar/abrir grids personalizados.
- Exportar estatísticas das gerações.

## 👨‍💼 Autor

Desenvolvido por **Luan Garcia** – [htttps://github.com/LGarciax].

---

Sinta-se à vontade para contribuir ou sugerir melhorias!


package gameOfLife;

import javax.swing.*;

public class GameOfLife {

	private static int DELAY = 100;
	private static Timer timer = null;
	private static boolean jogoIniciado = false;
	private static int idadeMaxima = 10;
	private static int generationCount = 0;

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {
			GameScreen screen = new GameScreen();
			GridPanel panel = screen.getGridPanel();
			

			panel.setGridListener(() -> {
				
				if (jogoIniciado) return; 
				jogoIniciado = true;
				int linhas = panel.getGridHeight();
			    int colunas = panel.getGridWidth();
			     
			    

				Cell[][] grid = new Cell[linhas][colunas];

				for (int i = 0; i < linhas; i++) {
					for (int j = 0; j < colunas; j++) {
						grid[i][j] = new Cell(Status.randomStatus(), idadeMaxima);
					}
				}
				updateGridPanel(panel, grid);
				timer = new Timer(DELAY, e -> {	
					if (timer.isRunning()) {
			        update(grid);
			        updateGridPanel(panel, grid);
			        generationCount++;
			        screen.getGenerationLabel().setText(""+generationCount);
					}
			    });				
				
			    timer.start();
			    
			    JButton reiniciar = screen.getPlayPauseButton();
			    reiniciar.addActionListener(e -> {
			        if (timer != null && timer.isRunning()) {
			        	generationCount = 0;
				        screen.getGenerationLabel().setText("0");
				        
				        DELAY = screen.getDelay();
				        idadeMaxima = screen.getMaxAge(); 
			            timer.stop();
			           
			        }
			    
			        

			        Cell[][] newGrid = new Cell[linhas][colunas];
			        for (int i = 0; i < linhas; i++) {
			            for (int j = 0; j < colunas; j++) {
			                newGrid[i][j] = new Cell(Status.randomStatus(), idadeMaxima);
			            }
			        }

			        updateGridPanel(panel, newGrid);
			        

			        timer = new Timer(DELAY, e2 -> {
			            update(newGrid);
			            updateGridPanel(panel, newGrid);
			            generationCount++;
				        screen.getGenerationLabel().setText(""+generationCount);
			        });

			        timer.start();
			    });
			    
				

			});
		});
	}

	public static void updateGridPanel(GridPanel panel, Cell[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				Cell cell = grid[i][j];

				
				int state = 0;
				if (cell.isAlive()) {
				    int age = cell.getAge();
				    if (idadeMaxima <= 1) {
				        state = 1; 
				    } else {
				        
				        state = Math.min(age, idadeMaxima);
				    }
				} else {
				    state = 0; 
				}

				panel.setCell(state, j, i);

			}
		}
		
		panel.update();
		
	}

	public static void update(Cell[][] grid) {
		int linhas = grid.length;
		int colunas = grid[0].length;

		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				checkNeighbors(grid, i, j);

			}
		}
		nextGeneration(grid);

	}

	public static void checkNeighbors(Cell[][] grid, int x, int y) {
		int linhas = grid.length;
		int colunas = grid[0].length;

		Cell cell = grid[x][y];
		int aliveNeighbors = 0;

		int linha = x;
		int coluna = y;

		int up = (linha - 1 + linhas) % linhas;
		int down = (linha + 1) % linhas;
		int left = (coluna - 1 + colunas) % colunas;
		int right = (coluna + 1) % colunas;

		// Linha acima
		aliveNeighbors += grid[up][left].isAlive() ? 1 : 0;
		aliveNeighbors += grid[up][coluna].isAlive() ? 1 : 0;
		aliveNeighbors += grid[up][right].isAlive() ? 1 : 0;
		// Mesma linha
		aliveNeighbors += grid[linha][left].isAlive() ? 1 : 0;
		aliveNeighbors += grid[linha][right].isAlive() ? 1 : 0;
		// Linha abaixo
		aliveNeighbors += grid[down][left].isAlive() ? 1 : 0;
		aliveNeighbors += grid[down][coluna].isAlive() ? 1 : 0;
		aliveNeighbors += grid[down][right].isAlive() ? 1 : 0;

		if (cell.isAlive() && (aliveNeighbors > 3 || aliveNeighbors < 2)) {
			cell.setNextStatus(Status.DEAD);
		}
		if (!cell.isAlive() && aliveNeighbors == 3) {
			cell.setNextStatus(Status.ALIVE);
		}

	}

	public static void nextGeneration(Cell[][] grid) {
		int linhas = grid.length;
		int colunas = grid[0].length;

		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				grid[i][j].update();
			}
		}

	}
}

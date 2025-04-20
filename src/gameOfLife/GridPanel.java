package gameOfLife;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

    public interface GridListener {

        void gridReady();

    }

    public void setGridListener(GridListener gridListener) {
        this.gridListener = gridListener;
    }

    private GridListener gridListener;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final Font font = new Font("Courier", Font.BOLD, 25);

    private static final int CELLSIZE = 10;
    private int gridWidth;
    private int gridHeight;
    private int leftMargin;
    private int topMargin;

    private Map<Integer, BufferedImage> statusMap = new HashMap<>();
    private Integer[][] states;

    public GridPanel() {
        setBackground(Color.gray);

         addState(0, Color.black); 
         addState(1, new Color(204, 255, 255)); 
         addState(2, Color.cyan);             
         addState(3, new Color(153, 255, 153)); 
         addState(4, Color.green);             
         addState(5, new Color(255, 204, 153)); 
         addState(6, Color.orange);            
         addState(7, new Color(255, 153, 153)); 
         addState(8, Color.red);               
         addState(9, new Color(169, 169, 169)); 
         addState(10, Color.darkGray);           
     
    }

    public void update() {
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        gridWidth = (width / CELLSIZE) - 1;
        gridHeight = (height / CELLSIZE) - 1;

        initCells(gridWidth, gridHeight);

        int xSpare = width - (gridWidth * CELLSIZE);
        int ySpare = height - (gridHeight * CELLSIZE);

        leftMargin = xSpare / 2;
        topMargin = ySpare / 2;

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(leftMargin, topMargin, width + 1 - xSpare, height + 1 - ySpare);

        g2.setColor(Color.white);
        for (int gridy = 0; gridy < gridHeight; gridy++) {
            for (int gridx = 0; gridx < gridWidth; gridx++) {
                int x = gridx * CELLSIZE + leftMargin;
                int y = gridy * CELLSIZE + topMargin;

                Integer state = states[gridy][gridx];

                BufferedImage bi = statusMap.getOrDefault(state, statusMap.get(0));
                g2.drawImage(bi, x + 1, y + 1, null);
            }
        }

    }

    private void initCells(int gridWidth, int gridHeight) {

        if (states == null || states.length != gridHeight || states[0].length != gridWidth) {

            states = new Integer[gridHeight][gridWidth];
            for (int i = 0; i < gridHeight; i++) {
                Arrays.fill(states[i], 0);
            }
        }

        if (gridListener != null) {
            gridListener.gridReady();
        }
    }

    public void addState(Integer state, Color background) {
        addState(state, Color.white, background, "");
    }

    public void addState(Integer state, Color foreground, Color background, String character) {
        BufferedImage bi = new BufferedImage(CELLSIZE - 1, CELLSIZE - 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setColor(background);
        g.fillRect(0, 0, CELLSIZE - 1, CELLSIZE - 1);

        if (character.length() != 0) {
            g.setColor(foreground);
            g.setFont(font);

            FontRenderContext frc = g.getFontRenderContext();
            TextLayout tl = new TextLayout(character, font, frc);
            Rectangle2D bounds = tl.getBounds();

            float x = CELLSIZE / 2 - (float) bounds.getCenterX();
            float y = CELLSIZE / 2 - (float) bounds.getCenterY();

            tl.draw(g, x, y);
        }

        g.dispose();

        statusMap.put(state, bi);
    }

    public void setCell(int state, int x, int y) {

        states[y][x] = state;

    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }
}

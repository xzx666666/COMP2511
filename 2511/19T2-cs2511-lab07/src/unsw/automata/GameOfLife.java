/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {
    private BooleanProperty[][] cells;

    public GameOfLife() {
        // TODO At the start all cells are dead
        cells = new BooleanProperty[10][10];

        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < 10; j ++) {
                cells[i][j] = new SimpleBooleanProperty(false);
            }
        }
    }

    public void ensureAlive(int x, int y) {
        // TODO Set the cell at (x,y) as alive
        cells[x][y].set(true);
    }

    public void ensureDead(int x, int y) {
        // TODO Set the cell at (x,y) as dead
        cells[x][y].set(false);
    }

    public boolean isAlive(int x, int y) {
        // TODO Return true if the cell is alive
        return cells[x][y].get();
    }

    public void tick() {
        ArrayList<BooleanProperty> alive = new ArrayList<BooleanProperty>();
        ArrayList<BooleanProperty> dies = new ArrayList<BooleanProperty>();

        int res = 0;
        for (int i  = 0; i < 10; i ++) {
            for (int j = 0; j < 10; j ++) {
                res = getNeighbours(i,j);

                if (res < 2) {
                    dies.add(cells[i][j]);
                } else if (res > 3) {
                    dies.add(cells[i][j]);
                } else if (res == 3) {
                    alive.add(cells[i][j]);
                }
            }
        }

        for (BooleanProperty b:alive) {
            b.set(true);
        }

        for (BooleanProperty b:dies) {
            b.set(false);
        }
    }

    public int getNeighbours(int row, int col) {

        int sum=0;

        // right
        if (col - 1 >= 0) {
            if (isAlive(row, col-1)) sum++;
        } else {
            if (isAlive(row, 9)) sum++;
        }

        //left
        if (col + 1 < 10) {
            if (isAlive(row, col+1)) sum++;
        } else {
            if (isAlive(row, 0)) sum ++;
        }

        // up
        if (row - 1 >= 0) {
            if (isAlive(row-1,col)) sum++;
        } else {
            if (isAlive(9, col)) sum++;
        }


        // down
        if (row + 1 < 10) {
            if (isAlive(row+1,col)) sum++;
        } else {
            if (isAlive(0,col)) sum ++;
        }



        // lc
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (isAlive(row-1,col-1)) sum++;
        } else {

            int tempR = row;
            int temC = col;
            if (row - 1 < 0 ) tempR = 9;
            else tempR = row - 1;
            if (col - 1 < 0) temC = 9;
            else temC = col - 1;

            if (isAlive(tempR, temC)) sum ++;
        }


        // ld
        if (row + 1 < 10 && col + 1 < 10) {
            if (isAlive(row + 1, col + 1)) sum++;
        } else {
            int tempR = row;
            int temC = col;
            if (row + 1 >= 10 ) tempR = 0;
            else tempR = row+1;
            if (col + 1 >= 10) temC = 0;
            else temC = col+1;
            if (isAlive(tempR, temC)) sum ++;

        }

        // rc
        if (row - 1 >= 0 && col + 1 < 10) {
            if (isAlive(row - 1, col + 1)) sum++;
        } else {
            int tempR = row;
            int temC = col;
            if (row - 1 < 0 ) tempR = 9;
            else tempR = row-1;
            if (col + 1 >= 10) temC = 0;
            else temC = col + 1;
            if (isAlive(tempR, temC)) sum ++;
        }
        // rd
        if (row + 1 < 10 && col - 1 >= 0) {
            if (isAlive(row + 1, col - 1)) sum++;
        } else {
            int tempR = row;
            int temC = col;
            if (row + 1 >= 10 ) tempR = 0;
            else tempR = row+1;
            if (col - 1 < 0) temC = 9;
            else temC = col-1;
            if (isAlive(tempR, temC)) sum ++;
        }

        return sum;
    }

    public BooleanProperty cellProperty(int x, int y) {
        return cells[x][y];
    }

    public void setCells(int x, int y, BooleanProperty res) {
        cells[x][y] = res;
    }

    public boolean lose() {
        for (int i = 0; i < 10; i ++ ){
            for (int j = 0; j < 10; j ++) {
                if (isAlive(i,j)) return false;
            }
        }
        return true;
    }
}

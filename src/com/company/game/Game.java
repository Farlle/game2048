package com.company.game;

import com.company.board.Board;
import com.company.direction.Direction;

public interface Game {
    void init();
    boolean canMove();
    boolean move(Direction direction );
    void addItem();
    Board getGameBoard();
    boolean hasWin();
}

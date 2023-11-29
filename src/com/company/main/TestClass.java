package com.company.main;

import com.company.board.Board;
import com.company.board.SquareBoard;
import com.company.game.Game;
import com.company.game.Game2048;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048(board);
        System.out.println(game2048.canMove());
    }
}

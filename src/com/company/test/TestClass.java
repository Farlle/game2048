package com.company.test;

import com.company.board.Board;
import com.company.board.SquareBoard;
import com.company.direction.Direction;
import com.company.game.Game;
import com.company.game.Game2048;

import static java.util.Arrays.asList;

public class TestClass {
    public static void main(String[] args) {
        Board board = new SquareBoard(4);
        Game game2048 = new Game2048(board);

        board.fillBoard(asList(
                2, null, 2, 2,
                2, null, null, 2,
                2, 4, null, 4,
                2, 2, null, 2));
        Game game = new Game2048(board);

        System.out.println(game.getGameBoard());

        game.move(Direction.BACK);
        System.out.println(game.getGameBoard());
        System.out.println(game2048.canMove());
    }
}

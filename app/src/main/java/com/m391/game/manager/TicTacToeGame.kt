package com.m391.game.manager

class TicTacToeGame {
    private val board = Array(3) { Array<Player?>(3) { null } }
    private var currentPlayer = Player.X
    var gameState = GameState.IN_PROGRESS
        private set
    fun makeMove(row: Int, col: Int): Boolean {
        if (row !in 0..2 || col !in 0..2 || board[row][col] != null || gameState != GameState.IN_PROGRESS) {
            return false
        }

        board[row][col] = currentPlayer
        if (checkForWin(row, col)) {
            gameState = if (currentPlayer == Player.X) GameState.PLAYER_X_WON else GameState.PLAYER_O_WON
        } else if (isBoardFull()) {
            gameState = GameState.DRAW
        } else {
            currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        }
        return true
    }

    private fun checkForWin(row: Int, col: Int): Boolean {

        return false
    }
    private fun isBoardFull(): Boolean {
        return board.all { row -> row.all { it != null } }
    }

    fun resetGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = null
            }
        }
        currentPlayer = Player.X
        gameState = GameState.IN_PROGRESS
    }

    fun getCurrentPlayer(): Player = currentPlayer

    fun getCell(row: Int, col: Int): Player? = board[row][col]
}
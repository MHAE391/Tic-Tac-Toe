package com.m391.game.manager

class TicTacToeGame {
    private val board = Array(3) { Array<Player?>(3) { null } }
    private var currentPlayer = Player.X
    var winningRow: Int? = null
    var winningCol: Int? = null
    var winningDiagonal: Int? = null
    var gameState = GameState.IN_PROGRESS
        private set

    fun makeMove(row: Int, col: Int): Boolean {
        if (row !in 0..2 || col !in 0..2 || board[row][col] != null || gameState != GameState.IN_PROGRESS) {
            return false
        }

        board[row][col] = currentPlayer

        if (checkForWin(row, col)) {
            gameState =
                if (currentPlayer == Player.X) GameState.PLAYER_X_WON else GameState.PLAYER_O_WON
        } else if (isBoardFull()) {
            gameState = GameState.DRAW
        } else {
            currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
        }
        return true
    }

    private fun checkForWin(row: Int, col: Int): Boolean {
        if (checkRow(row) || checkColumn(col) || checkFirstDiagonals() || checkSecondDiagonals()) return true
        return false
    }

    private fun checkRow(row: Int): Boolean {
        for (i in 0..2)
            if (board[row][i] != currentPlayer) return false
        winningRow = row
        return true
    }

    private fun checkColumn(col: Int): Boolean {
        for (i in 0..2)
            if (board[i][col] != currentPlayer) return false
        winningCol = col
        return true
    }

    private fun checkFirstDiagonals(): Boolean {
        for (i in 0..2)
            if (board[i][i] != currentPlayer) return false
        winningDiagonal = 1
        return true
    }

    private fun checkSecondDiagonals(): Boolean {
        for (i in 0..2)
            if (board[i][2 - i] != currentPlayer) return false
        winningDiagonal = 2
        return true
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
        winningRow = null
        winningDiagonal = null
        winningCol = null
    }

    fun getCurrentPlayer(): Player = currentPlayer

    fun getCell(row: Int, col: Int): Player? = board[row][col]

    fun getWinningCells(): List<Pair<Int, Int>> {
        val winingList = ArrayList<Pair<Int, Int>>()
        if (winningRow != null)
            for (i in 0..2)
                winingList.add(Pair(winningRow!!, i))
        if (winningCol != null)
            for (i in 0..2)
                winingList.add(Pair(i, winningCol!!))
        if (winningDiagonal == 1)
            for (i in 0..2)
                winingList.add(Pair(i, i))
        if (winningDiagonal == 2)
            for (i in 0..2)
                winingList.add(Pair(i, 2 - i))
        return winingList
    }
}
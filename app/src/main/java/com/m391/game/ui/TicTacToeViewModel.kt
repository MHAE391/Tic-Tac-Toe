package com.m391.game.ui

// TicTacToeViewModel.kt (ViewModel)

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.m391.game.manager.GameState
import com.m391.game.manager.Player
import com.m391.game.manager.TicTacToeGame

class TicTacToeViewModel : ViewModel() {
    private val _xScore = MutableLiveData<Int>()
    val xScore: LiveData<Int> = _xScore

    private val _oScore = MutableLiveData<Int>()
    val oScore: LiveData<Int> = _oScore

    init {
        _xScore.postValue(0)
        _oScore.postValue(0)
    }

    private val game = TicTacToeGame()

    fun makeMove(row: Int, col: Int): Boolean {
        return game.makeMove(row, col)
    }

    fun resetGame() {
        game.resetGame()
    }

    fun getCurrentPlayer(): Player = game.getCurrentPlayer()

    fun getGameState(): GameState = game.gameState

    fun getWinningList(): List<Pair<Int, Int>> {
        return game.getWinningCells()
    }

    fun xWon() {
        _xScore.postValue(xScore.value!!.plus(1))
    }

    fun oWon() {
        _oScore.postValue(_oScore.value!!.plus(1))
    }

}

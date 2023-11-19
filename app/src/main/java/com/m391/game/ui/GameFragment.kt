package com.m391.game.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.m391.game.R
import com.m391.game.databinding.FragmentGameBinding
import com.m391.game.manager.GameState
import com.m391.game.manager.Player

class GameFragment : Fragment() {
    private val viewModel: TicTacToeViewModel by viewModels()
    private lateinit var binding: FragmentGameBinding
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_game, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setNewPlay(binding.cell11)
        setNewPlay(binding.cell12)
        setNewPlay(binding.cell13)
        setNewPlay(binding.cell21)
        setNewPlay(binding.cell22)
        setNewPlay(binding.cell23)
        setNewPlay(binding.cell31)
        setNewPlay(binding.cell32)
        setNewPlay(binding.cell33)

    }

    private fun setNewPlay(clickedImage: ImageView) {
        clickedImage.setOnClickListener {
            if (viewModel.getGameState() == GameState.IN_PROGRESS) {
                when (clickedImage.tag) {
                    getString(R.string.sell_1_1) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(0, 0)
                    }

                    getString(R.string.sell_1_2) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(0, 1)
                    }

                    getString(R.string.sell_1_3) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(0, 2)
                    }

                    getString(R.string.sell_2_1) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(1, 0)
                    }

                    getString(R.string.sell_2_2) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(1, 1)
                    }

                    getString(R.string.sell_2_3) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(1, 2)
                    }

                    getString(R.string.sell_3_1) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(2, 0)
                    }

                    getString(R.string.sell_3_2) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(2, 1)
                    }

                    getString(R.string.sell_3_3) -> {
                        setImageResource(clickedImage)
                        viewModel.makeMove(2, 2)
                    }
                }
                clickedImage.isEnabled = false
                val gameState = viewModel.getGameState()
                if (gameState != GameState.IN_PROGRESS) {
                    when (gameState) {
                        GameState.PLAYER_O_WON -> {
                            setWinningCells()
                            viewModel.oWon()
                            Toast.makeText(context, GameState.PLAYER_O_WON.name, Toast.LENGTH_SHORT)
                                .show()

                        }

                        GameState.PLAYER_X_WON -> {
                            setWinningCells()
                            viewModel.xWon()
                            Toast.makeText(context, GameState.PLAYER_X_WON.name, Toast.LENGTH_SHORT)
                                .show()
                        }

                        else -> {
                            Toast.makeText(context, GameState.DRAW.name, Toast.LENGTH_SHORT).show()
                        }
                    }
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            resetGame()
                        }, 1400
                    )
                }
            }
        }
    }

    private fun setWinningCells() {
        var loop: Long = 100
        for (cell in viewModel.getWinningList()) {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    winningCell(cell)
                }, loop
            )
            loop += 400
        }
    }

    private fun setImageResource(imageView: ImageView) {
        if (viewModel.getCurrentPlayer().name == Player.X.name) {
            imageView.setImageResource(R.drawable.x)
        } else {
            imageView.setImageResource(R.drawable.o)
        }
    }

    private fun resetGame() {
        viewModel.resetGame()
        restCell(binding.cell11)
        restCell(binding.cell12)
        restCell(binding.cell13)
        restCell(binding.cell21)
        restCell(binding.cell22)
        restCell(binding.cell23)
        restCell(binding.cell31)
        restCell(binding.cell32)
        restCell(binding.cell33)
    }

    private fun restCell(imageView: ImageView) {
        imageView.isEnabled = true
        imageView.setImageResource(0)
        imageView.background = null
    }

    private fun winningCell(cell: Pair<Int, Int>) {
        when (cell) {
            Pair(0, 0) -> binding.cell11.makeWinning()
            Pair(0, 1) -> binding.cell12.makeWinning()
            Pair(0, 2) -> binding.cell13.makeWinning()
            Pair(1, 0) -> binding.cell21.makeWinning()
            Pair(1, 1) -> binding.cell22.makeWinning()
            Pair(1, 2) -> binding.cell23.makeWinning()
            Pair(2, 0) -> binding.cell31.makeWinning()
            Pair(2, 1) -> binding.cell32.makeWinning()
            Pair(2, 2) -> binding.cell33.makeWinning()
        }
    }

    private fun ImageView.makeWinning() {
        this.background = ContextCompat.getDrawable(requireContext(), R.color.winning_line_color)
    }
}
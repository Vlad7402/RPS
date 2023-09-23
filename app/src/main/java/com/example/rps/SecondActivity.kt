package com.example.rps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        subskribeButtons()
    }

    private fun subskribeButtons() {
        val buttons = mutableListOf<Button>()
        buttons.run {
            add(findViewById(R.id.back_button))
        }
        for (button in buttons)
            button.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        val bundle = intent.extras
        val choiseOrdinal = bundle?.getInt(PlayerChoise::class.java.simpleName)
        choiseOrdinal ?: return
        val playerChoise: PlayerChoise = PlayerChoise.values()[choiseOrdinal]
        val computerChoise = PlayerChoise.values()[Random.nextInt(PlayerChoise.values().size)]
        var win = false
        var draw = false
        when (playerChoise) {
            PlayerChoise.ROCK ->
                if (computerChoise == PlayerChoise.ROCK)
                    draw = true
                else if (computerChoise == PlayerChoise.SCISSORS)
                    win = true

            PlayerChoise.SCISSORS ->
                if (computerChoise == PlayerChoise.SCISSORS)
                draw = true
            else if (computerChoise == PlayerChoise.PAPER)
                win = true

            PlayerChoise.PAPER ->
                if (computerChoise == PlayerChoise.PAPER)
                draw = true
            else if (computerChoise == PlayerChoise.ROCK)
                win = true
        }
        val textView = findViewById<TextView>(R.id.WinTextView)
        textView.text = "Проиграл"
        if (win)
            textView.text = "Победа"
        else if (draw)
            textView.text = "Ничья"
    }
    override fun onClick(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
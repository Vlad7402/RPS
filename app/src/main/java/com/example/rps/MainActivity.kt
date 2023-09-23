package com.example.rps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subskribeButtons()
    }

    private fun subskribeButtons() {
        val buttons = mutableListOf<Button>()
        buttons.run {
            add(findViewById(PlayerChoise.PAPER.resourceId))
            add(findViewById(PlayerChoise.ROCK.resourceId))
            add(findViewById(PlayerChoise.SCISSORS.resourceId))
        }
        for (button in buttons)
            button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val button = view as? Button
        button ?: return
        val choise: PlayerChoise? = PlayerChoise.values().find { it.resourceId == button.id }
        val intent = Intent(this, SecondActivity::class.java).apply {
            val bundle = Bundle().apply {
                choise ?: return
                putInt(PlayerChoise::class.java.simpleName, choise.ordinal)
            }
            putExtras(bundle)
        }
        startActivity(intent)
    }
}

package com.phaytran.swiththeme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    private var switch:SwitchCompat? = null
    private lateinit var saveData:SaveData

    override fun onCreate(savedInstanceState: Bundle?) {
        saveData = SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }else{
            setTheme(R.style.AppTheme)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        switch = findViewById(R.id.On_Off) as SwitchCompat?
        if(saveData.loadDarkModeState() == true){
            switch!!.isChecked = true
        }
        switch!!.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                    saveData.setDarkModeState(true)
                    restartApplication()
                }else{
                    saveData.setDarkModeState(false)
                    restartApplication()
                }
            }

        })

        val button = findViewById<Button>(R.id.Jump)
        button.setOnClickListener{
            val intent = Intent(this@MainActivity,SecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun restartApplication() {
        val i = Intent(applicationContext,MainActivity::class.java)
        startActivity(i)
        finish()
    }
}
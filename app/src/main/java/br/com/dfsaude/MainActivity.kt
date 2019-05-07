package br.com.dfsaude

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.dfsaude.recover.RecoverActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textView_EsqueciSenha.setOnClickListener {

            val intent = Intent(this, RecoverActivity::class.java)
            this.startActivity(intent)
        }
    }
}

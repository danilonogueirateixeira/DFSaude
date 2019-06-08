package br.com.dfsaude.splash

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.dfsaude.R
import android.os.Handler
import br.com.dfsaude.login.LoginActivity
import br.com.dfsaude.perfil.PerfilActivity

class SplashActivity : AppCompatActivity(), SplashView {


    override fun temporizador(tempo: Long) {
        super.temporizador(tempo)
        Handler().postDelayed({

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, tempo)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        temporizador(2000)
    }
}

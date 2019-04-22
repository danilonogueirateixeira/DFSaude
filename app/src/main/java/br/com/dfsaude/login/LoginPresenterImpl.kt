package br.com.dfsaude.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import br.com.dfsaude.RedefinirSenha

class LoginPresenterImpl(val loginView: LoginView,
                         val context: Context) : LoginPresenter {
    override fun performRecoverPassword()
    {

        (context as AppCompatActivity).startActivity(Intent(context, RedefinirSenha::class.java))
    }
}
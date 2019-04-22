package br.com.dfsaude.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import br.com.dfsaude.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity(), LoginView, View.OnClickListener
{


    lateinit var btnRecoverPassword: TextView


    lateinit var presenterLogin: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setView()

        presenterLogin = getPresenter()

        btnRecoverPassword.setOnClickListener(this)



    }

    fun setView()
    {
        btnRecoverPassword = btn_recover
    }

    fun getPresenter(): LoginPresenter
    {
        return LoginPresenterImpl(this,this)
    }

    override fun onClick(v: View?)
    {

         when(v!!.id)
         {
             R.id.btn_recover ->
             {
                 presenterLogin.performRecoverPassword()
             }
         }
    }
}
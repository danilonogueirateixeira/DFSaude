package br.com.dfsaude.login

import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import br.com.dfsaude.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity(), LoginView, View.OnClickListener
{
    override fun setPregressBar(isVisible: Boolean)
    {

        if(isVisible)
        {
            progressBar.visibility = View.VISIBLE
            return
        }

        progressBar.visibility = View.GONE

    }

    override fun errorEmail(message: String)
    {
        inputEmail.error = if(message.isEmpty()) null else message
    }

    override fun errorPassword(message: String)
    {
        inputPassword.error = if(message.isEmpty()) null else message
    }

    override fun clearInputs() {

        inputEmail.editText?.setText("")
        inputPassword.editText?.setText("")

    }


    lateinit var btnRecoverPassword: TextView
    lateinit var btnLogin: Button

    lateinit var inputEmail: TextInputLayout

    lateinit var inputPassword: TextInputLayout


    lateinit var presenterLogin: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setView()

        presenterLogin = getPresenter()

        btnRecoverPassword.setOnClickListener(this)
        btnLogin.setOnClickListener(this)



    }

    fun setView()
    {
        btnRecoverPassword = btn_recover
        btnLogin = btn_enter

        inputEmail = input_email
        inputPassword = input_password
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

             R.id.btn_enter ->
             {
                 presenterLogin.performLogin(
                         email = inputEmail.editText?.text.toString(),
                         password = inputPassword.editText?.text.toString())
             }
         }
    }
}
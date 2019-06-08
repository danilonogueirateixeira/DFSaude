package br.com.dfsaude.register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.Button
import br.com.dfsaude.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView, View.OnClickListener {



    override fun setPregressBar(isVisible: Boolean) {

        if(isVisible) {
            progressBar.visibility = View.VISIBLE
            return
        }

        progressBar.visibility = View.GONE
    }


    override fun errorEmail(message: String) {
        inputEmail.error = if(message.isEmpty()) null else message
    }

    override fun errorPassword(message: String) {
        inputPassword.error = if(message.isEmpty()) null else message
    }

    override fun errorConfirmPassword(message: String) {
        inputConfirmPassword.error = if(message.isEmpty()) null else message
    }



    lateinit var btnRegister: Button

    lateinit var inputEmail: TextInputLayout

    lateinit var inputPassword: TextInputLayout
    lateinit var inputConfirmPassword: TextInputLayout

    lateinit var presenterRegister: RegisterPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



        setView()

        presenterRegister = getPresenter()

        btnRegister.setOnClickListener(this)
    }


    fun setView()
    {
        btnRegister = btn_register

        inputEmail = input_email
        inputPassword = input_password
        inputConfirmPassword = input_confirm_password

    }


    fun getPresenter(): RegisterPresenter
    {
        return RegisterPresenterImpl(this,this)
    }

    override fun onClick(v: View?)
    {

        when(v!!.id)
        {

            R.id.btn_register ->{
                presenterRegister.performRegisterUser(
                        email = inputEmail.editText?.text.toString(),
                        password = inputPassword.editText?.text.toString(),
                        confirmPassword = inputConfirmPassword.editText?.text.toString()
                )

            }
        }
    }

}

package br.com.dfsaude.login

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import br.com.dfsaude.R
import br.com.dfsaude.recover.RecoverActivity
import br.com.dfsaude.util.isValidEmail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.widget.Toast
import br.com.dfsaude.perfil.PerfilActivity
import br.com.dfsaude.register.RegisterActivity
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.OnCompleteListener


class LoginPresenterImpl(val loginView: LoginView, val context: Context) : LoginPresenter
{

    val mAuth = FirebaseAuth.getInstance()

    val TAG = javaClass.simpleName

    override fun performLogin(email: String, password: String)
    {

        if (!email.isValidEmail())
        {
            loginView.errorEmail(context.getString(R.string.error_input_email))
            return
        }

        loginView.errorEmail("")


        if (password.length < 8)
        {
            loginView.errorPassword(context.getString(R.string.error_input_password))
            return
        }

        loginView.errorPassword("")

        login(email,password)



    }

    override fun performRegister() {
        (context as AppCompatActivity).startActivity(Intent(context, RegisterActivity::class.java))

    }

    override fun performRecoverPassword()
    {

        (context as AppCompatActivity).startActivity(Intent(context, RecoverActivity::class.java))
    }

    fun login(email: String, password: String)
    {

        loginView.setPregressBar(true)
        val currentUser: FirebaseUser? = mAuth.currentUser

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener ( (context as AppCompatActivity), OnCompleteListener<AuthResult>
                { task ->

                    if (task.isSuccessful)
                    {
                        Toast.makeText(context, "Successfully Logged in :)", Toast.LENGTH_LONG).show()

                        (context).startActivity(Intent(context, PerfilActivity::class.java))
                        (context).finish()

                    }
                    else
                    {
                        Toast.makeText(context, "Error Logging in :(", Toast.LENGTH_SHORT).show()
                    }

                    loginView.setPregressBar(false)

                })


    }
}
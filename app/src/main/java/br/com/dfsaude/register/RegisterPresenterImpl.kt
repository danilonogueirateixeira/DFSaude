package br.com.dfsaude.register

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.dfsaude.R
import br.com.dfsaude.login.LoginActivity
import br.com.dfsaude.perfil.PerfilActivity
import br.com.dfsaude.util.isValidEmail
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegisterPresenterImpl(val registerView: RegisterView, val context: Context) : RegisterPresenter{

    val mAuth = FirebaseAuth.getInstance()



    override fun performRegisterUser(email: String, password: String, confirmPassword: String) {

            if (!email.isValidEmail())
            {
                registerView.errorEmail(context.getString(R.string.error_input_email))
                return
            }

            registerView.errorEmail("")


            if (password.length < 8)
            {
                registerView.errorPassword(context.getString(R.string.error_input_password))
                return
            }

            registerView.errorPassword("")

            if (confirmPassword != password)
            {
                registerView.errorConfirmPassword(context.getString(R.string.error_input_confirm_password))
                return
            }
            registerView.errorConfirmPassword("")





            register(email,password)





    }


    fun register(email: String, password: String)
    {

        registerView.setPregressBar(true)
        val currentUser: FirebaseUser? = mAuth.currentUser

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener ( (context as AppCompatActivity), OnCompleteListener<AuthResult>
                { task ->

                    if (task.isSuccessful)
                    {
                        Toast.makeText(context, "Successfully User Created:)", Toast.LENGTH_LONG).show()

                        (context).startActivity(Intent(context, LoginActivity::class.java))
                        (context).finish()

                    }
                    else
                    {
                        Toast.makeText(context, "Error User not Created :(", Toast.LENGTH_SHORT).show()
                    }

                    registerView.setPregressBar(false)

                })


    }


}

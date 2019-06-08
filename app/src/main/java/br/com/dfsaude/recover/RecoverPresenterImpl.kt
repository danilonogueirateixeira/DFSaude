package br.com.dfsaude.recover

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import br.com.dfsaude.R
import br.com.dfsaude.util.isValidEmail
import com.google.firebase.auth.FirebaseAuth


class RecoverPresenterImpl(val recoverView: RecoverView, val context: AppCompatActivity) : RecoverPresenter {


    val mAuth = FirebaseAuth.getInstance()


    override fun performRecoverPassword(email: String) {



        if (!email.isValidEmail())
        {
            recoverView.errorEmail(context.getString(R.string.error_input_email))
            return
        }

        recoverView.errorEmail("")

        recoverPassword(email, context)


    }


    fun recoverPassword(email: String, activity: AppCompatActivity) {

        recoverView.setPregressBar(true)


        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {

                        Toast.makeText(activity, R.string.RedefinirSucesso, Toast.LENGTH_LONG).show()

                        activity.finish()

                    } else {

                        Toast.makeText(activity, R.string.RedefinirFalha, Toast.LENGTH_LONG).show()
                    }

                    recoverView.setPregressBar(false)

                }
        }

    }



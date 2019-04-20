package br.com.dfsaude

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_redefinir_senha.*

class RedefinirSenha : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redefinir_senha)


        button_RedefinirSenha.setOnClickListener {

            redefinirSenha(editText_RedefinirSenha_Email.text.toString(), this)

        }
    }


    val mAuth = FirebaseAuth.getInstance()

    private fun redefinirSenha(email: String?, activity: AppCompatActivity){

        if (email != ""  && email != null){
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(activity){task ->
                        if (task.isSuccessful) {

                            // Se a verificação ocorrer com sucesso.
                            Toast.makeText(
                                    activity,
                                    R.string.RedefinirSucesso,
                                    Toast.LENGTH_LONG
                            ).show()

                            finish()

                        } else {

                            // Se a verificação falhar
                            Toast.makeText(
                                    activity,
                                    R.string.RedefinirFalha,
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }

        } else  {

            // Se a verificação falhar
            Toast.makeText(
                    activity,
                    R.string.RedefinirFalha,
                    Toast.LENGTH_LONG
            ).show()
        }

    }

}

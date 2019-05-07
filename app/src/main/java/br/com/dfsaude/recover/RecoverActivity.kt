package br.com.dfsaude.recover

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.Button
import br.com.dfsaude.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_recover.*
import kotlinx.android.synthetic.main.activity_recover.progressBar

class RecoverActivity : AppCompatActivity(), RecoverView,  View.OnClickListener {

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
        input_email_recover.error = if(message.isEmpty()) null else message
    }



    lateinit var btn_recover: Button

    lateinit var input_email: TextInputLayout

    lateinit var presenterRecover: RecoverPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)


        setView()

        presenterRecover = getPresenter()

        btnRecoverPassword.setOnClickListener(this)

    }


    fun setView()
    {
        btn_recover = btnRecoverPassword
        input_email = input_email_recover
    }

    fun getPresenter(): RecoverPresenter
    {
        return RecoverPresenterImpl(this,this)
    }

    override fun onClick(v: View?)
    {

        when(v!!.id)
        {
            R.id.btnRecoverPassword ->
            {
                presenterRecover.performRecoverPassword(email = input_email_recover.editText?.text.toString())
            }

        }
    }


}

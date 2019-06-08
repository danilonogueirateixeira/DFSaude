package br.com.dfsaude.perfil

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import br.com.dfsaude.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_perfil.*


val PERMISSION_REQUEST_CODE = 1001
val PICK_IMAGE_REQUEST = 900;
lateinit var filePath : Uri


@TargetApi(Build.VERSION_CODES.M)


class PerfilActivity : AppCompatActivity() , PerfilView, View.OnClickListener {

    lateinit var presenterPerfil: PerfilPresenter

    lateinit var btnSalvar: Button


    override fun errorNome(message: String) {
        input_nome.error = if(message.isEmpty()) null else message
    }

    override fun errorDDD(message: String) {
        input_ddd.error = if(message.isEmpty()) null else message
    }

    override fun errorTelefone(message: String) {
        input_telefone.error = if(message.isEmpty()) null else message
    }

    override fun errorEndereco(message: String) {
        input_endereco.error = if(message.isEmpty()) null else message
    }

    override fun errorNumero(message: String) {
        input_numero.error = if(message.isEmpty()) null else message
    }

    override fun errorComplemento(message: String) {
        input_complemento.error = if(message.isEmpty()) null else message
    }

    override fun errorCEP(message: String) {
        input_cep.error = if(message.isEmpty()) null else message
    }

    override fun errorCidade(message: String) {
        input_cidade.error = if(message.isEmpty()) null else message
    }

    override fun errorUF(message: String) {
        input_uf.error = if(message.isEmpty()) null else message
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)


        setView()

        presenterPerfil = getPresenter()

        btnSalvar.setOnClickListener(this)

        imageView_Perfil.setOnClickListener {
            when {
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) -> {
                    if (ContextCompat.checkSelfPermission(this@PerfilActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
                    }else{
                        presenterPerfil.chooseFile(this)

                    }
                }
                else -> presenterPerfil.chooseFile(this)

            }

        }


        presenterPerfil.dadosExistentes(
                this,
                imageView_Perfil,
                edit_nome,
                edit_ddd,
                edit_telefone,
                edit_endereco,
                edit_numero,
                edit_complemento,
                edit_cep,
                edit_cidade,
                edit_uf
                )

    }




























    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            PERMISSION_REQUEST_CODE -> {
                if(grantResults.isEmpty() || grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this@PerfilActivity, "Permissão Negada!!",Toast.LENGTH_SHORT).show()
                else
                    presenterPerfil.chooseFile(this)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        when (requestCode){
            PICK_IMAGE_REQUEST -> {

                filePath = data?.data!!
                presenterPerfil.uploadFile(this,imageView_Perfil)

            }
        }
    }


    fun setView()
    {
        btnSalvar = btn_salvar

    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.btn_salvar -> {


                val user = Usuario(
                        presenterPerfil.userAtual(),
                        input_nome.editText?.text.toString(),
                        input_ddd.editText?.text.toString(),
                        input_telefone.editText?.text.toString(),
                        input_endereco.editText?.text.toString(),
                        input_numero.editText?.text.toString(),
                        input_complemento.editText?.text.toString(),
                        input_cep.editText?.text.toString(),
                        input_cidade.editText?.text.toString(),
                        input_uf.editText?.text.toString())

                presenterPerfil.salvarPerfil(user)

            }

        }
    }

    fun getPresenter(): PerfilPresenter {
        return PerfilPresenterImpl(this,this)
    }

}


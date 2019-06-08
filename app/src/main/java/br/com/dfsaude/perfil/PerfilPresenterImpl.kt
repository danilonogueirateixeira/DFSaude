package br.com.dfsaude.perfil

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import br.com.dfsaude.R
import br.com.dfsaude.util.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_perfil.*



class PerfilPresenterImpl(val perfilView: PerfilView, val context: Context) : PerfilPresenter {


    val mAuth = FirebaseAuth.getInstance()
    val userLogged = mAuth.currentUser
    val database = FirebaseDatabase.getInstance()
    val userLogger = database.getReference(userLogged!!.uid)





    override fun salvarPerfil(usuario: Usuario) {
        if (!usuario.nome.isValidNome())
        {
            perfilView.errorNome(context.getString(R.string.error_input_nome))
            return
        }
        perfilView.errorNome("")

        if (!usuario.ddd.isValidDDD())
        {
            perfilView.errorDDD(context.getString(R.string.error_input_ddd))
            return
        }
        perfilView.errorDDD("")

        if (!usuario.telefone.isValidTelefone())
        {
            perfilView.errorTelefone(context.getString(R.string.error_input_telefone))
            return
        }
        perfilView.errorTelefone("")

        if (!usuario.endereco.isValidEndereco())
        {
            perfilView.errorEndereco(context.getString(R.string.error_input_endereco))
            return
        }
        perfilView.errorEndereco("")

        if (!usuario.numero.isValidNumero())
        {
            perfilView.errorNumero(context.getString(R.string.error_input_numero))
            return
        }
        perfilView.errorNumero("")

        if (!usuario.complemento.isValidComplemento())
        {
            perfilView.errorComplemento(context.getString(R.string.error_input_complemento))
            return
        }
        perfilView.errorComplemento("")

        if (!usuario.cep.isValidCEP())
        {
            perfilView.errorCEP(context.getString(R.string.error_input_cep))
            return
        }
        perfilView.errorCEP("")

        if (!usuario.cidade.isValidCidade())
        {
            perfilView.errorCidade(context.getString(R.string.error_input_cidade))
            return
        }
        perfilView.errorCidade("")

        if (!usuario.uf.isValidUF())
        {
            perfilView.errorUF(context.getString(R.string.error_input_uf))
            return
        }
        perfilView.errorUF("")



        val teste = database.getReference(userLogged!!.uid)
        teste.setValue(usuario)


        Toast.makeText(context, "Saved successfully :)", Toast.LENGTH_LONG).show()


        Log.i("TESTE" , userLogged!!.email)
        Log.i("TESTE" , userLogged!!.uid)
    }

    override fun atualizaFoto(foto: ImageView) {
        FirebaseStorage.getInstance().reference.child(userLogged?.email.toString() + ".jpg").downloadUrl.addOnSuccessListener {
            foto.background = null
            Glide.with(context).load(it).into(foto)

        }
    }

    override fun uploadFile(context: Context, imageView: ImageView) {
        val progress = ProgressDialog(context).apply {
            setTitle("Carregando Foto...")
            setCancelable(true)
            setCanceledOnTouchOutside(false)
            show()
        }

        val data = FirebaseStorage.getInstance()
        var value = 0.0
        data.getReference().child(userLogged!!.email.toString()+".jpg").putFile(filePath)
                .addOnProgressListener { taskSnapshot ->
                    value = (100.0 * taskSnapshot.bytesTransferred) / taskSnapshot.totalByteCount
                    progress.setMessage("Carregado.. " + value.toInt() + "%")
                }
                .addOnSuccessListener { taskSnapshot -> progress.dismiss()
                    atualizaFoto( imageView)
                }
                .addOnFailureListener{
                    exception -> exception.printStackTrace()
                }

    }


    override fun chooseFile(activity: Activity) {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(activity,intent, PICK_IMAGE_REQUEST, null)
    }

    override fun userAtual() : String{


        Log.i("USER", userLogged!!.email.toString())

        return userLogged.email.toString()
    }


    override fun dadosExistentes(context: Context, foto: ImageView, nome: EditText, ddd: EditText, telefone: EditText, endereco: EditText, numero: EditText, complemento: EditText, cep: EditText, cidade: EditText, uf: EditText) {

        atualizaFoto(foto)

                userLogger.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        val user = Usuario(
                                dataSnapshot.child("email").value.toString(),
                                dataSnapshot.child("nome").value.toString(),
                                dataSnapshot.child("ddd").value.toString(),
                                dataSnapshot.child("telefone").value.toString(),
                                dataSnapshot.child("endereco").value.toString(),
                                dataSnapshot.child("numero").value.toString(),
                                dataSnapshot.child("complemento").value.toString(),
                                dataSnapshot.child("cep").value.toString(),
                                dataSnapshot.child("cidade").value.toString(),
                                dataSnapshot.child("uf").value.toString())


                        if (user.nome != "null")
                            nome.setText(user.nome)

                        if (user.ddd != "null")
                            ddd.setText(user.ddd)

                        if (user.telefone != "null")
                            telefone.setText(user.telefone)

                        if (user.endereco != "null")
                            endereco.setText(user.endereco)

                        if (user.numero != "null")
                            numero.setText(user.numero)

                        if (user.complemento != "null")
                            complemento.setText(user.complemento)

                        if (user.cep != "null")
                            cep.setText(user.cep)

                        if (user.cidade != "null")
                            cidade.setText(user.cidade)

                        if (user.uf != "null")
                            uf.setText(user.uf)

                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        Log.i("TESTE", "Failed to read value.", error.toException())
                    }
                })
        }
    }





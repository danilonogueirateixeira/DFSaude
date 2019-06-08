package br.com.dfsaude.perfil

import android.app.Activity
import android.content.Context
import android.widget.EditText
import android.widget.ImageView

interface PerfilPresenter
{

    fun salvarPerfil(usuario: Usuario)

    fun uploadFile(context: Context, imageView: ImageView)

    fun chooseFile(activity: Activity)

    fun dadosExistentes(context: Context, foto: ImageView, nome: EditText, ddd: EditText, telefone: EditText, endereco: EditText, numero: EditText, complemento: EditText, cep: EditText, cidade: EditText, uf: EditText)

    fun atualizaFoto (imageView: ImageView)

    fun userAtual () : String

}
package br.com.dfsaude.login

interface LoginView
{
    fun errorEmail(message: String)
    fun errorPassword(message: String)
    fun clearInputs()
    fun setPregressBar(isVisible: Boolean)


}
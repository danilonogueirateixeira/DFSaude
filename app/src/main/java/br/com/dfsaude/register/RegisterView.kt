package br.com.dfsaude.register

interface RegisterView
{
    fun errorEmail(message: String)
    fun errorPassword(message: String)
    fun errorConfirmPassword(message: String)
    fun setPregressBar(isVisible: Boolean)

}
package br.com.dfsaude.login

interface LoginPresenter
{
    fun performRecoverPassword()

    fun performLogin(email: String, password: String)

    fun performRegister()


}
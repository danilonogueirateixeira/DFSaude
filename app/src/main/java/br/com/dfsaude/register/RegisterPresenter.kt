package br.com.dfsaude.register

interface RegisterPresenter
{

    fun performRegisterUser(email: String, password: String, confirmPassword: String)

}
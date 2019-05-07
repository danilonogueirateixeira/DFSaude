package br.com.dfsaude.recover

interface RecoverView
{
    fun errorEmail(message: String)
    fun setPregressBar(isVisible: Boolean)

}
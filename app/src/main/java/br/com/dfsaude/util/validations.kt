package br.com.dfsaude.util

import android.util.Patterns
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidNome(): Boolean = this.isNotEmpty()

fun String.isValidDDD(): Boolean = this.isNotEmpty() && this.length == 2

fun String.isValidTelefone(): Boolean = this.isNotEmpty() && Patterns.PHONE.matcher(this).matches() && this.length == 9

fun String.isValidEndereco(): Boolean = this.isNotEmpty()

fun String.isValidNumero(): Boolean = this.isNotEmpty()

fun String.isValidComplemento(): Boolean = this.isNotEmpty()

fun String.isValidCEP(): Boolean = this.isNotEmpty() && this.length == 8

fun String.isValidCidade(): Boolean =  this.isNotEmpty()

fun String.isValidUF(): Boolean = this.isNotEmpty() && this.length == 2




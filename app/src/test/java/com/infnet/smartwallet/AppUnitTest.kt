package com.infnet.smartwallet

import com.infnet.smartwallet.database.ObjetoUtil
import com.infnet.smartwallet.model.Ticket
import com.infnet.smartwallet.ui.ticket.details.DetailsTicketFragment
import com.infnet.smartwallet.ui.ticket.form.FormTicketFragment
import com.infnet.smartwallet.ui.user.cadastro.CadastroFragment
import com.infnet.smartwallet.ui.user.login.LoginFragment
import com.infnet.smartwallet.ui.user.update.UpdateUserFragment
import org.junit.Assert
import org.junit.Test

class AppUnitTest {

    //Testes de unidade para usuário

    @Test
    fun validarEmailESenhaVazios() {
        val result = LoginFragment().validarEmailESenha("","")
        Assert.assertEquals(false, result)
    }

    @Test
    fun validarEmailESenha() {
        val result = LoginFragment().validarEmailESenha("bruno@gmail.com","123456")
        Assert.assertEquals(true, result)
    }

    @Test
    fun validarCamposCadastrarUsuarioVazios() {
        val result = CadastroFragment().validarCamposCadastrarUsuario("","", "", "", "")
        Assert.assertEquals(false, result)
    }

    @Test
    fun validarCamposCadastrarUsuario() {
        val result = CadastroFragment().validarCamposCadastrarUsuario("bruno@gmail.com","123456", "123456", "Bruno", "Verdan")
        Assert.assertEquals(true, result)
    }

    @Test
    fun verificarTamanhoSenhaMenorQue6() {
        val result = CadastroFragment().verificarTamanhoSenha("12345")
        Assert.assertEquals(false, result)
    }

    @Test
    fun verificarTamanhoSenha() {
        val result = CadastroFragment().verificarTamanhoSenha("123456")
        Assert.assertEquals(true, result)
    }

    @Test
    fun verificarSenhasDiferentes() {
        val result = CadastroFragment().verificarSenhasIguais("123456", "12345")
        Assert.assertEquals(false, result)
    }

    @Test
    fun verificarSenhasIguais() {
        val result = CadastroFragment().verificarSenhasIguais("123456", "123456")
        Assert.assertEquals(true, result)
    }

    @Test
    fun verificarNomeSobrenomeVazios() {
        val result = UpdateUserFragment().verificarNomeSobrenomeVazios("", "")
        Assert.assertEquals(false, result)
    }

    @Test
    fun verificarNomeSobrenomePreenchidos() {
        val result = UpdateUserFragment().verificarNomeSobrenomeVazios("bruno", "verdan")
        Assert.assertEquals(true, result)
    }

    //Teste de unidade para ticket

    @Test
    fun verificarCamposVazios() {
        val result = FormTicketFragment().verificarCamposVazios("", "", "", "")
        Assert.assertEquals(false, result)
    }

    @Test
    fun verificarCamposPreenchidos() {
        val result = FormTicketFragment().verificarCamposVazios("Rua da minha casa", "Show do metallica", "22/02/20", "18:00")
        Assert.assertEquals(true, result)
    }

    @Test
    fun verificarCategoriaPadrao() {
        val result = FormTicketFragment().verificarCategoriaPadrao("Selecionar Categoria")
        Assert.assertEquals(true, result)
    }

    @Test
    fun verificarCategoriaDiferenteDaPadrao() {
        val result = FormTicketFragment().verificarCategoriaPadrao("Cinema")
        Assert.assertEquals(false, result)
    }

    @Test
    fun verificarTicketSelecionadoNulo() {
        ObjetoUtil.ticketSelecionado = null
        val result = DetailsTicketFragment().verificarTicketSelecionado()
        Assert.assertEquals(false, result)
    }

    @Test
    fun verificarTicketSelecionado() {
        ObjetoUtil.ticketSelecionado = Ticket("Show do metallica", "Rua do show", "22/02/20", "20:00", "Show", "123456789", "2")
        val result = DetailsTicketFragment().verificarTicketSelecionado()
        Assert.assertEquals(true, result)
    }
}
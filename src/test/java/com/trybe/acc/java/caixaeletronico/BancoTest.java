package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Banco test.
 */
@DisplayName("Testes para a classe Banco")
class BancoTest {

  /**
   * Gerar numero nova conta test.
   */
  @Test
  @DisplayName("20 - Testa o gerador de número único para nova conta.")
  void gerarNumeroNovaContaTest() {
    Banco mockBank = new Banco();
    assertNotNull(mockBank.gerarNumeroNovaConta());
    assertEquals(10, mockBank.gerarNumeroNovaConta().length());
  }

  /**
   * Adicionar pessoa cliente test.
   */
  @Test
  @DisplayName("21 - Testa o método adicionar pessoa cliente retorna o objeto pessoa cliente.")
  void adicionarPessoaClienteTest() {
    String mockCpf = "012.345.678-90";
    String mockName = "Pessoa Cliente Teste 21";
    String mockPassword = "SenhaSuperFacil";

    Banco mockBank = new Banco();
    assertEquals(0, mockBank.getPessoasClientes().size());
    PessoaCliente mockClient = mockBank.adicionarPessoaCliente(mockName, mockCpf, mockPassword);
    assertTrue(mockClient instanceof PessoaCliente);
    assertEquals(1, mockBank.getPessoasClientes().size());
  }

  /**
   * Pessoa cliente login test.
   */
  @Test
  @DisplayName("22 - Testa o método login da pessoa cliente retorna o objeto pessoa cliente corretamente.")
  void pessoaClienteLoginTest() {
    fail("Não implementado");

  }

  /**
   * Depositar test transferir fundos testmostrar extrato test.
   */
  @Test
  @DisplayName("23 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    fail("Não implementado");

  }

  /**
   * Depositar test sacar test mostrar extrato test.
   */
  @Test
  @DisplayName("24 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    fail("Não implementado");

  }

}

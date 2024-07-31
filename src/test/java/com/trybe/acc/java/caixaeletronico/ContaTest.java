package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Conta test.
 */
@DisplayName("Teste da classe Conta")
class ContaTest {

  /**
   * The Mocked client.
   */
  PessoaCliente mockedClient = new PessoaCliente(
      "Pessoa 1",
      "012.345.678-90",
      "senhaMuitoDificil"
  );

  /**
   * The Mocked bank.
   */
  Banco mockedBank = new Banco();

  /**
   * The Mocked account.
   */
  Conta mockedAccount = new Conta("corrente", mockedClient, mockedBank);

  /**
   * Construtor test.
   */
  @Test
  @DisplayName("5 - Testa o construtor da classe conta.")
  void construtorTest() {
    assertNotNull(mockedAccount);
    assertNotNull(mockedAccount.getIdConta());
    assertEquals(0.00, mockedAccount.retornarSaldo());
  }

  /**
   * Adicionar transacao test retornar saldo test.
   */
  @Test
  @DisplayName("6 - Testa o método adicionar transação e retornar saldo da conta.")
  void adicionarTransacaoTestRetornarSaldoTest() {
    assertEquals(0.00, mockedAccount.retornarSaldo());
    mockedAccount.adicionarTransacao(10.00, "Teste1-adiciona 10.00");
    assertEquals(10.00, mockedAccount.retornarSaldo());
    mockedAccount.adicionarTransacao(11.00, "Teste2-adiciona 11.00");
    assertEquals(21.00, mockedAccount.retornarSaldo());
  }

  /**
   * Retornar resumo conta test.
   */
  @Test
  @DisplayName("7 - Testa o método retornar resumo está retornando uma string com os valores corretamente.")
  void retornarResumoContaTest() {
    assertEquals(
        mockedAccount.getIdConta() + " : " + "R$0.00" + " : corrente",
        mockedAccount.retornarResumoConta()
    );

    mockedAccount.adicionarTransacao(10.55, "Depósito para o teste de resumo.");
    assertEquals(
        mockedAccount.getIdConta() + " : " + "R$10.55" + " : corrente",
        mockedAccount.retornarResumoConta()
    );

    mockedAccount.adicionarTransacao((-10.56), "Teste de resumo saldo negativo");
    assertEquals(
        String.format(
            "%s : R$(%.02f) : %s",
            mockedAccount.getIdConta(),
            mockedAccount.retornarSaldo(),
            "corrente"
        ),
        mockedAccount.retornarResumoConta()
    );
  }

  /**
   * Retornar extrato test.
   */
  @Test
  @DisplayName("8 - Testa o método retornar extrato está imprimindo os valores corretamente.")
  void retornarExtratoTest() {
    String instantFormat = "dd/MM/yyyy HH:mm:ss";
    DateTimeFormatter instantFormatter = DateTimeFormatter.ofPattern(instantFormat);
    LocalDateTime auxInstant = LocalDateTime.now();

    String instantTransaction1 = instantFormatter.format(auxInstant);
    mockedAccount.adicionarTransacao(25.00, "Depósito 1 para teste de extrato.");
    String instantTransaction2 = instantFormatter.format(auxInstant);
    mockedAccount.adicionarTransacao(15.00, "Depósito 2 para teste de extrato.");
    String instantTransaction3 = instantFormatter.format(auxInstant);
    mockedAccount.adicionarTransacao(-15.00, "Depósito 3 para teste de extrato.");

    String mockedExtract = "\nExtrato da conta "
        + mockedAccount.getIdConta()
        + "\n"
        + instantTransaction3 + " -------- "
        + "Depósito 3 para teste de extrato." + ": " + "R$ 15.00" + " -\n"
        + instantTransaction2 + " -------- "
        + "Depósito 2 para teste de extrato." + ": " + "R$ 15.00" + " +\n"
        + instantTransaction1 + " -------- "
        + "Depósito 1 para teste de extrato." + ": " + "R$ 25.00" + " +\n";

    assertEquals(mockedExtract, mockedAccount.retornarExtrato());
  }

  /**
   * Gets id conta test.
   */
  @Test
  @DisplayName("9 - Testa o método Getter do atributo idConta está retornando.")
  void getIdContaTest() {
    String mockedId = mockedAccount.getIdConta();
    assertNotNull(mockedAccount.getIdConta());
    assertEquals(mockedAccount.getIdConta(), mockedId);
  }

  /**
   * Gets pessoa cliente test.
   */
  @Test
  @DisplayName("10 - Testa o método método Getter do atributo pessoaCliente está retornando.")
  void getPessoaClienteTest() {
    assertNotNull(mockedAccount.getPessoaCliente());
    assertEquals(mockedClient, mockedAccount.getPessoaCliente());
  }

}

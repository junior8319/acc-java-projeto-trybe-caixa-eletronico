package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Pessoa cliente test.
 */
@DisplayName("Teste dos métodos da classe PessoaCliente")
class PessoaClienteTest {

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
  Conta mockedAccount1 = new Conta("corrente", mockedClient, mockedBank);
  /**
   * The Mocked account 2.
   */
  Conta mockedAccount2 = new Conta("poupança", mockedClient, mockedBank);

  /**
   * The Mocked account 3.
   */
  Conta mockedAccount3 = new Conta("fundo investimento", mockedClient, mockedBank);

  /**
   * Construtor test.
   */
  @Test
  @DisplayName("11 - Testa o construtor da classe Pessoa Cliente.")
  void construtorTest() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    new PessoaCliente("Pessoa Teste Um", "123.456.789-01", "SenhaSuperSegura");

    String output = outputStream.toString();

    assertEquals(
        output,
        "Nova pessoa cliente Pessoa Teste Um com CPF: 123.456.789-01 criada!\n"
    );
  }

  /**
   * Adicionar conta test retorna numero de contas test.
   */
  @Test
  @DisplayName("12 - Testa o método adicionar conta e o método retornar número de contas.")
  void adicionarContaTestRetornaNumeroDeContasTest() {
    assertEquals(0, mockedClient.retornaNumeroDeContas());
    mockedClient.adicionarConta(mockedAccount1);
    assertEquals(1, mockedClient.retornaNumeroDeContas());
    mockedClient.adicionarConta(mockedAccount2);
    assertEquals(2, mockedClient.retornaNumeroDeContas());
  }

  /**
   * Retornar saldo conta especifica test.
   */
  @Test
  @DisplayName("13 - Testa o método retornar saldo de uma conta específica da pessoa cliente.")
  void retornarSaldoContaEspecificaTest() {
    mockedClient.adicionarConta(mockedAccount1);
    mockedClient.adicionarConta(mockedAccount2);
    mockedClient.adicionarConta(mockedAccount3);
    mockedAccount1.adicionarTransacao(
        10.00,
        "Depósito para teste de saldo específico"
    );
    mockedAccount2.adicionarTransacao(
        12.00,
        "Depósito para teste de saldo específico"
    );
    mockedAccount3.adicionarTransacao(
        100.00,
        "Depósito para teste de saldo específico"
    );

    assertEquals(100.00, mockedClient.retornarSaldoContaEspecifica(2));
    assertEquals(10.00, mockedClient.retornarSaldoContaEspecifica(0));
    assertEquals(12.00, mockedClient.retornarSaldoContaEspecifica(1));
  }


  /**
   * Retornar id conta especifica test.
   */
  @Test
  @DisplayName("14 - Testa o método retornar id de uma conta específica da pessoa cliente.")
  void retornarIdContaEspecificaTest() {
    mockedClient.adicionarConta(mockedAccount1);
    mockedClient.adicionarConta(mockedAccount2);
    mockedClient.adicionarConta(mockedAccount3);

    assertEquals(mockedAccount1.getIdConta(), mockedClient.retornarIdContaEspecifica(0));
    assertEquals(mockedAccount3.getIdConta(), mockedClient.retornarIdContaEspecifica(2));
    assertEquals(mockedAccount2.getIdConta(), mockedClient.retornarIdContaEspecifica(1));
  }

  /**
   * Retornar extrato conta especifica test.
   */
  @Test
  @DisplayName("15 - Testa o método retornar o extrato de uma conta específica da pessoa cliente.")
  void retornarExtratoContaEspecificaTest() {
    fail("Não implementado");

  }

  /**
   * Adicionar transacao conta especifica test.
   */
  @Test
  @DisplayName("16 - Testa o método adiciona transação de uma conta específica da pessoa cliente.")
  void adicionarTransacaoContaEspecificaTest() {
    fail("Não implementado");

  }

  /**
   * Validar senha test.
   */
  @Test
  @DisplayName("17 - Testa o método validar senha.")
  void validarSenhaTest() {
    fail("Não implementado");

  }

  /**
   * Retornar resumo contas test.
   */
  @Test
  @DisplayName("18 - Testa o método retornar resumo contas.")
  void retornarResumoContasTest() {
    fail("Não implementado");


  }

  /**
   * Gets cpf test.
   */
  @Test
  @DisplayName("19 - Testa o método Getter do atributo cpf está retornando.")
  void getCpfTest() {
    fail("Não implementado");

  }

}

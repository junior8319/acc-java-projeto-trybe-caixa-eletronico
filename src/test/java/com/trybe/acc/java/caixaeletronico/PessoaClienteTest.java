package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Pessoa cliente test.
 */
@DisplayName("Teste dos métodos da classe PessoaCliente")
class PessoaClienteTest {

  /**
   * The mock client.
   */
  PessoaCliente mockClient = new PessoaCliente(
      "Pessoa 1",
      "012.345.678-90",
      "senhaMuitoDificil"
  );

  /**
   * The mock bank.
   */
  Banco mockBank = new Banco();

  /**
   * The mock account.
   */
  Conta mockAccount1 = new Conta("corrente", mockClient, mockBank);
  /**
   * The mock account 2.
   */
  Conta mockAccount2 = new Conta("poupança", mockClient, mockBank);

  /**
   * The mock account 3.
   */
  Conta mockAccount3 = new Conta("fundo investimento", mockClient, mockBank);

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
    assertEquals(0, mockClient.retornaNumeroDeContas());
    mockClient.adicionarConta(mockAccount1);
    assertEquals(1, mockClient.retornaNumeroDeContas());
    mockClient.adicionarConta(mockAccount2);
    assertEquals(2, mockClient.retornaNumeroDeContas());
  }

  /**
   * Retornar saldo conta especifica test.
   */
  @Test
  @DisplayName("13 - Testa o método retornar saldo de uma conta específica da pessoa cliente.")
  void retornarSaldoContaEspecificaTest() {
    mockClient.adicionarConta(mockAccount1);
    mockClient.adicionarConta(mockAccount2);
    mockClient.adicionarConta(mockAccount3);
    mockAccount1.adicionarTransacao(
        10.00,
        "Depósito para teste de saldo específico"
    );
    mockAccount2.adicionarTransacao(
        12.00,
        "Depósito para teste de saldo específico"
    );
    mockAccount3.adicionarTransacao(
        100.00,
        "Depósito para teste de saldo específico"
    );

    assertEquals(100.00, mockClient.retornarSaldoContaEspecifica(2));
    assertEquals(10.00, mockClient.retornarSaldoContaEspecifica(0));
    assertEquals(12.00, mockClient.retornarSaldoContaEspecifica(1));
  }


  /**
   * Retornar id conta especifica test.
   */
  @Test
  @DisplayName("14 - Testa o método retornar id de uma conta específica da pessoa cliente.")
  void retornarIdContaEspecificaTest() {
    mockClient.adicionarConta(mockAccount1);
    mockClient.adicionarConta(mockAccount2);
    mockClient.adicionarConta(mockAccount3);

    assertEquals(mockAccount1.getIdConta(), mockClient.retornarIdContaEspecifica(0));
    assertEquals(mockAccount3.getIdConta(), mockClient.retornarIdContaEspecifica(2));
    assertEquals(mockAccount2.getIdConta(), mockClient.retornarIdContaEspecifica(1));
  }

  /**
   * Retornar extrato conta especifica test.
   */
  @Test
  @DisplayName("15 - Testa o método retornar o extrato de uma conta específica da pessoa cliente.")
  void retornarExtratoContaEspecificaTest() {
    String instantFormat = "dd/MM/yyyy HH:mm:ss";
    DateTimeFormatter instantFormatter = DateTimeFormatter.ofPattern(instantFormat);
    LocalDateTime auxInstant = LocalDateTime.now();

    mockClient.adicionarConta(mockAccount1);
    mockClient.adicionarConta(mockAccount2);
    mockClient.adicionarConta(mockAccount3);

    String instantTransaction1 = instantFormatter.format(auxInstant);
    mockAccount1.adicionarTransacao(
        10.00,
        "Depósito para teste de extrato específico"
    );

    String instantTransaction2 = instantFormatter.format(auxInstant);
    mockAccount2.adicionarTransacao(
        12.00,
        "Depósito para teste de extrato específico"
    );

    String instantTransaction3 = instantFormatter.format(auxInstant);
    mockAccount3.adicionarTransacao(
        100.00,
        "Depósito para teste de extrato específico"
    );

    String instantTransaction4 = instantFormatter.format(auxInstant);
    mockAccount3.adicionarTransacao(
        50.00,
        "Depósito para teste de extrato específico"
    );

    String mockExtract1 = "\nExtrato da conta "
        + mockAccount1.getIdConta()
        + "\n"
        + instantTransaction1 + " -------- "
        + "Depósito para teste de extrato específico" + ": " + "R$ 10.00" + " +\n";

    String mockExtract2 = "\nExtrato da conta "
        + mockAccount2.getIdConta()
        + "\n"
        + instantTransaction2 + " -------- "
        + "Depósito para teste de extrato específico" + ": " + "R$ 12.00" + " +\n";

    String mockExtract3 = "\nExtrato da conta "
        + mockAccount3.getIdConta()
        + "\n"
        + instantTransaction4 + " -------- "
        + "Depósito para teste de extrato específico" + ": " + "R$ 50.00" + " +\n"
        + instantTransaction3 + " -------- "
        + "Depósito para teste de extrato específico" + ": " + "R$ 100.00" + " +\n";

    assertEquals(mockExtract1, mockClient.retornarExtratoContaEspecifica(0));
    assertEquals(mockExtract2, mockClient.retornarExtratoContaEspecifica(1));
    assertEquals(mockExtract3, mockClient.retornarExtratoContaEspecifica(2));
  }

  /**
   * Adicionar transacao conta especifica test.
   */
  @Test
  @DisplayName("16 - Testa o método adiciona transação de uma conta específica da pessoa cliente.")
  void adicionarTransacaoContaEspecificaTest() {
    mockClient.adicionarConta(mockAccount1);
    mockClient.adicionarConta(mockAccount2);
    mockClient.adicionarConta(mockAccount3);

    mockClient.adicionarTransacaoContaEspecifica(
        0,
        10.00,
        "Depósito para teste de extrato específico"
    );

    assertEquals(10.00, mockAccount1.retornarSaldo());
  }

  /**
   * Validar senha test.
   */
  @Test
  @DisplayName("17 - Testa o método validar senha.")
  void validarSenhaTest() {
    String truePassword = "senhaMuitoDificil";
    String falsePassword = "senhaFácil";

    assertFalse(mockClient.validarSenha(falsePassword));
    assertTrue(mockClient.validarSenha(truePassword));
  }

  /**
   * Retornar resumo contas test.
   */
  @Test
  @DisplayName("18 - Testa o método retornar resumo contas.")
  void retornarResumoContasTest() {
    Banco mockBank2 = new Banco();
    PessoaCliente mockClient2 = new PessoaCliente(
        "Pessoa Cliente Dois",
        "987.654.321-09",
        "SenhaMegaForte"
    );
    Conta mockAccount4 = new Conta("corrente", mockClient2, mockBank2);
    mockAccount4.adicionarTransacao(15.00, "Depósito para teste 18.");
    mockClient2.adicionarConta(mockAccount4);

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    mockClient2.retornarResumoContas();
    String output = outputStream.toString();

    String mockOutput = "\n\nResumo das Contas da pessoa Pessoa Cliente Dois:\n\n"
        + "1) " + mockAccount4.getIdConta() + " : R$15.00 : corrente" + "\n\n\n";

    assertEquals(output, mockOutput);
  }

  /**
   * Gets cpf test.
   */
  @Test
  @DisplayName("19 - Testa o método Getter do atributo cpf está retornando.")
  void getCpfTest() {
    String mockClientCpf = "012.345.678-90";
    String clientCpfReturn = mockClient.getCpf();

    assertEquals(mockClientCpf, clientCpfReturn);
  }

}

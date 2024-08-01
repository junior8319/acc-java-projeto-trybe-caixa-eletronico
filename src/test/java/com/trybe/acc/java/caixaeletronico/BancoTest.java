package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    String mockCpf = "012.345.678-90";
    String mockName = "Pessoa Cliente Teste 21";
    String mockPassword = "SenhaSuperFacil";

    Banco mockBank = new Banco();
    assertEquals(0, mockBank.getPessoasClientes().size());
    PessoaCliente mockClient = mockBank.adicionarPessoaCliente(mockName, mockCpf, mockPassword);
    assertEquals(1, mockBank.getPessoasClientes().size());
    assertNotNull(mockBank.pessoaClienteLogin(mockCpf, mockPassword));
    assertTrue(mockClient instanceof PessoaCliente);
  }

  /**
   * Depositar test transferir fundos testmostrar extrato test.
   */
  @Test
  @DisplayName("23 - Testa se o método transferir fundos está transferindo corretamente.")
  void depositarTestTransferirFundosTestmostrarExtratoTest() {
    Banco mockBank = new Banco();

    PessoaCliente mockClient1 = new PessoaCliente(
        "Cliente Tst23 Um",
        "000.000.001-01",
        "SenhaFácil"
    );

    Conta mockAccount1 = new Conta("corrente", mockClient1, mockBank);
    mockClient1.adicionarConta(mockAccount1);
    Conta mockAccount2 = new Conta("poupança", mockClient1, mockBank);
    mockClient1.adicionarConta(mockAccount2);

    mockBank.adicionarConta("corrente", mockClient1);
    mockBank.adicionarConta("poupança", mockClient1);
    //Refatorar o método construtor de Conta para já executar o método adicionarConta
    //Motivo: ao criar uma conta bancária, o banco já está mencionado/vinculado.
    //Também, a execução de adicionarConta é repetitiva e passível de erro dada a assinatura deste
    //método.

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    String instantFormat = "dd/MM/yyyy HH:mm:ss";
    DateTimeFormatter instantFormatter = DateTimeFormatter.ofPattern(instantFormat);
    LocalDateTime auxInstant = LocalDateTime.now();

    String instantTransaction1 = instantFormatter.format(auxInstant);
    mockBank.depositar(mockClient1, 0 , 50.00);
    mockAccount1.retornarExtrato();
    String depositarOutput = outputStream.toString();

    String mockExtract1 = "\nExtrato da conta "
        + mockAccount1.getIdConta()
        + "\n\n"
        + instantTransaction1 + " -------- "
        + "Depósito recebido" + ": " + "R$ 50,00" + " +\n\n";

    assertEquals(mockExtract1, depositarOutput);

    mockBank.transferirFundos(mockClient1, 0, 1, 25.00);

    String instantTransaction2 = instantFormatter.format(auxInstant);
    String mockExtract2 = "\nExtrato da conta "
        + mockAccount2.getIdConta()
        + "\n"
        + instantTransaction2 + " -------- "
        + "Transferência recebida" + ": " + "R$ 25,00" + " +\n";

    String mockExtract3 = "\nExtrato da conta "
        + mockAccount1.getIdConta()
        + "\n"
        + instantTransaction2 + " -------- "
        + "Transferência enviada" + ": " + "R$ 25,00" + " -\n"
        + instantTransaction1 + " -------- "
        + "Depósito recebido" + ": " + "R$ 50,00" + " +\n";

    String extract2 = mockAccount1.retornarExtrato();
    assertEquals(mockExtract3, extract2);

    String extract3 = mockAccount2.retornarExtrato();
    assertEquals(mockExtract2, extract3);

  }

  /**
   * Depositar test sacar test mostrar extrato test.
   */
  @Test
  @DisplayName("24 - Testa se o método sacar está funcionando corretamente.")
  void depositarTestSacarTestMostrarExtratoTest() {
    Banco mockBank = new Banco();

    PessoaCliente mockClient1 = new PessoaCliente(
        "Cliente Tst23 Um",
        "000.000.001-01",
        "SenhaFácil"
    );

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    String instantFormat = "dd/MM/yyyy HH:mm:ss";
    DateTimeFormatter instantFormatter = DateTimeFormatter.ofPattern(instantFormat);
    LocalDateTime auxInstant = LocalDateTime.now();
    
    Conta mockAccount1 = new Conta("corrente", mockClient1, mockBank);
    mockClient1.adicionarConta(mockAccount1);

    String instantTransaction1 = instantFormatter.format(auxInstant);
    mockBank.depositar(mockClient1, 0, 100.00);
    String instantTransaction2 = instantFormatter.format(auxInstant);
    mockBank.sacar(mockClient1, 0, 25.00);

    String mockExtract1 = "\nExtrato da conta "
        + mockAccount1.getIdConta()
        + "\n\n"
        + instantTransaction2 + " -------- "
        + "Saque efetuado" + ": " + "R$ 25,00" + " -\n"
        + instantTransaction1 + " -------- "
        + "Depósito recebido" + ": " + "R$ 100,00" + " +\n\n";

    mockBank.mostrarExtrato(mockClient1, 0);
    String extractOutput = outputStream.toString();

    assertEquals(mockExtract1, extractOutput);
  }

}

package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * The type Transacao test.
 */
@DisplayName("Testes dos métodos da classe Transação")
class TransacaoTest {


  /**
   * Construtor test.
   */
  @Test
  @DisplayName("1 - Testa o método construtor da classe Transação.")
  void construtorTest() {
    Transacao transacao = new Transacao(10.00, "Depósito inicial.");
    assertNotNull(transacao);
    assertEquals(10.00, transacao.getQuantia());
  }


  /**
   * Gets quantia test.
   */
  @Test
  @DisplayName("2 - Testa o método Getter do atributo quantia.")
  void getQuantiaTest() {
    Transacao transacao = new Transacao(25.00, "Depósito inicial.");
    assertEquals(25.00, transacao.getQuantia());
  }

  /**
   * Retornar resumo transacao test.
   */
  @Test
  @DisplayName("3 - Testa o método retornar resumo transação.")
  void retornarResumoTransacaoTest() {
    String mockInstantFormat = "dd/MM/yyyy HH:mm:ss";
    DateTimeFormatter mockInstantFormatter = DateTimeFormatter.ofPattern(mockInstantFormat);
    LocalDateTime mockAuxInstant = LocalDateTime.now();
    String mockedInstantReturn = mockInstantFormatter.format(mockAuxInstant);
    double mockedQuantia = 25.00d;
    String mockedDescricao = "Depósito inicial.";

    Transacao transacaoTst1 = new Transacao(25.00, "Depósito inicial.");

    assertEquals(
        String.format(
        "%s -------- %s: R$ %.02f +",
        mockedInstantReturn,
        mockedDescricao,
        mockedQuantia),
        transacaoTst1.retornarResumoTransacao()
    );
  }

  /**
   * Retornar instante test.
   */
  @Test
  @DisplayName("4 - Testa o método instante está gerando o instante corretamente.")
  void retornarInstanteTest() {
    String mockInstantFormat = "dd/MM/yyyy HH:mm:ss";
    DateTimeFormatter mockInstantFormatter = DateTimeFormatter.ofPattern(mockInstantFormat);
    LocalDateTime mockAuxInstant = LocalDateTime.now();
    String mockedInstantReturn = mockInstantFormatter.format(mockAuxInstant);

    Transacao transacaoTst = new Transacao(25.00, "Depósito de nova conta");
    assertEquals(mockedInstantReturn, transacaoTst.retornarInstante());
  }

}

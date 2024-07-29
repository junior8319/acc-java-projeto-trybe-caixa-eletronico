package com.trybe.acc.java.caixaeletronico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
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
    fail("Não implementado");

  }

  /**
   * Retornar resumo transacao test.
   */
  @Test
  @DisplayName("3 - Testa o método retornar resumo transação.")
  void retornarResumoTransacaoTest() {
    fail("Não implementado");

  }

  /**
   * Retornar instante test.
   */
  @Test
  @DisplayName("4 - Testa o método instante está gerando o instante corretamente.")
  void retornarInstanteTest() {
    fail("Não implementado");

  }

}

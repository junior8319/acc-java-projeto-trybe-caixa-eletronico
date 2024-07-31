package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Account.
 */
public class Conta {

  private String tipoConta;
  private String idConta;
  private PessoaCliente pessoaCliente;
  private ArrayList<Transacao> transacoes;

  /**
   * Construtor para a classe Conta.
   *
   * @param tipoConta     the tipo conta
   * @param pessoaCliente the pessoa cliente
   * @param banco         the banco
   */
  public Conta(String tipoConta, PessoaCliente pessoaCliente, Banco banco) {

    this.tipoConta = tipoConta;
    this.pessoaCliente = pessoaCliente;
    this.idConta = banco.gerarNumeroNovaConta();
    this.transacoes = new ArrayList<Transacao>();

  }

  /**
   * Adiciona nova transação na conta.
   *
   * @param quantia   the quantia
   * @param descricao the descricao
   */
  public void adicionarTransacao(double quantia, String descricao) {

    // cria nova transacao e adiciona na lista de transacoes da conta
    Transacao novaTransacao = new Transacao(quantia, descricao);
    this.transacoes.add(novaTransacao);

  }


  /**
   * Retorna o saldo da conta somando as transacoes. Obs:depositos sao transacoes com valores
   * positivos e saques sao transacoes com valores negativos.
   *
   * @return the double
   */
  public double retornarSaldo() {
    double saldo = 0;
    for (Transacao transacao : this.transacoes) {
      saldo += transacao.getQuantia();
    }

    return saldo;
  }

  /**
   * Retorna o resumo da conta em uma linha.
   *
   * @return the string
   */
  public String retornarResumoConta() {
    Locale.setDefault(Locale.US);

    double saldo = this.retornarSaldo();

    if (saldo >= 0) {
      return String.format("%s : R$%.02f : %s", this.idConta, saldo, this.tipoConta);
    } else {
      return String.format("%s : R$(%.02f) : %s", this.idConta, saldo, this.tipoConta);
    }

  }

  /**
   * Retorna o extrato da conta.
   *
   * @return the string
   */
  public String retornarExtrato() {
    String extract = "\nExtrato da conta " + this.idConta + "\n";

    System.out.println("\nExtrato da conta " + this.idConta + "\n");
    for (int i = this.transacoes.size() - 1; i >= 0; i--) {
      System.out.println(this.transacoes.get(i).retornarResumoTransacao());
      extract += this.transacoes.get(i).retornarResumoTransacao() + "\n";
    }

    System.out.println();
    return extract;
  }

  /**
   * Gets id conta.
   *
   * @return the id conta
   */
  public String getIdConta() {
    return this.idConta;
  }

  /**
   * Gets pessoa cliente.
   *
   * @return the pessoa cliente
   */
  public PessoaCliente getPessoaCliente() {
    return this.pessoaCliente;
  }

}
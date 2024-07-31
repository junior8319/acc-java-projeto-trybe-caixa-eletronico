package com.trybe.acc.java.caixaeletronico;

import java.util.ArrayList;

/**
 * Client.
 */
public class PessoaCliente {

  private String nomeCompleto;
  private String cpf;
  private String senha;
  private ArrayList<Conta> contas;

  /**
   * Define uma pessoa cliente.
   *
   * @param nome  the nome
   * @param cpf   the cpf
   * @param senha the senha
   */
  public PessoaCliente(String nome, String cpf, String senha) {

    this.nomeCompleto = nome;
    this.cpf = cpf;
    this.senha = senha;

    this.contas = new ArrayList<Conta>();

    System.out.println(
        "Nova pessoa cliente " + this.nomeCompleto + " com CPF: " + this.cpf + " criada!");
  }

  /**
   * Adiciona nova conta a pessoa cliente.
   *
   * @param conta the conta
   */
  public void adicionarConta(Conta conta) {
    this.contas.add(conta);

  }

  /**
   * Retorna o número de contas que a pessoa cliente tem.
   *
   * @return the int
   */
  public int retornaNumeroDeContas() {
    return this.contas.size();
  }

  /**
   * Retornar saldo conta especifica double.
   *
   * @param indice the indice
   * @return the double
   */
  public double retornarSaldoContaEspecifica(int indice) {
    return this.contas.get(indice).retornarSaldo();
  }

  /**
   * Retornar id conta especifica string.
   *
   * @param indice the indice
   * @return the string
   */
  public String retornarIdContaEspecifica(int indice) {
    return this.contas.get(indice).getIdConta();
  }

  /**
   * Retornar extrato conta especifica string.
   *
   * @param indice the indice
   * @return the string
   */
  public String retornarExtratoContaEspecifica(int indice) {
    return this.contas.get(indice).retornarExtrato();
  }

  /**
   * Adicionar transacao conta especifica.
   *
   * @param indice    the indice
   * @param quantia   the quantia
   * @param descricao the descricao
   */
  public void adicionarTransacaoContaEspecifica(int indice, double quantia, String descricao) {
    this.contas.get(indice).adicionarTransacao(quantia, descricao);
  }

  /**
   * Valida a senha de uma PessoaCliente.
   *
   * @param senha the senha
   * @return true se a senha validada é a senha cadastrada, false em caso contrário.
   */
  public boolean validarSenha(String senha) {
    if (this.senha.equals(senha)) {
      return true;
    }
    return false;
  }

  /**
   * Retorna o resumo das contas.
   */
  public void retornarResumoContas() {

    System.out.println("\n\nResumo das Contas da pessoa " + this.nomeCompleto + ":\n");
    for (int a = 0; a < this.contas.size(); a++) {
      System.out.println((a + 1) + ") " + this.contas.get(a).retornarResumoConta() + "\n");
    }
    System.out.println();
  }

  /**
   * Gets cpf.
   *
   * @return the cpf
   */
  public String getCpf() {
    return this.cpf;
  }
}
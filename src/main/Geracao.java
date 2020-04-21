package main;

import java.util.ArrayList;

public class Geracao {
public ArrayList<String> populacao;
public ArrayList<Aptidao> aptidoes;
public String elite;
public Aptidao aptidao_elite;

public Geracao(ArrayList<String> populacao, ArrayList<Aptidao> aptidoes, String elite, Aptidao aptidao_elite) {
	this.populacao = populacao;
	this.aptidoes = aptidoes;
	this.elite = elite;
	this.aptidao_elite = aptidao_elite;
}

}

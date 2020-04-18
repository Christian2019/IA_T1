package main;

import java.util.ArrayList;

public class Geracao {
ArrayList<String> populacao;
ArrayList<Aptidao> aptidoes;
String elite;
Aptidao aptidao_elite;

public Geracao(ArrayList<String> populacao, ArrayList<Aptidao> aptidoes, String elite, Aptidao aptidao_elite) {
	this.populacao = populacao;
	this.aptidoes = aptidoes;
	this.elite = elite;
	this.aptidao_elite = aptidao_elite;
}

}

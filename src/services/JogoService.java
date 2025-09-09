package services;

import entities.Conteudo;
import entities.Jogo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class JogoService {
    public void AdicionarJogo(Scanner scan, List<Conteudo> colecao){
        System.out.println("Digite o nome do jogo:");
        var nome = scan.nextLine();
        System.out.println("Digite a plataforma do jogo:");
        var plataforma = scan.nextLine();
        System.out.println("Digite a data de lançamento do jogo (YYYY-MM-DD)");
        var dataLancamento = scan.nextLine();

        var jogo = new Jogo();
        jogo.nome = nome;
        jogo.plataforma = plataforma;
        jogo.dataLancamento = LocalDate.parse(dataLancamento);

        colecao.add(jogo);
    }
}

package services;

import entities.Conteudo;
import entities.Jogo;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static utils.StringUtils.print;

public class JogoService {

    public final String MENU_JOGOS = """
                        ---- Escolha uma opção: ----
                        1 - Adicionar jogo
                        2 - Remover jogo
                        3 - Listar jogos
                        0 - Voltar
                        ----------------------------
                        """;

    public void AdicionarJogo(Scanner scan, List<Conteudo> colecao){
        print("Digite o nome do jogo:");
        var nome = scan.nextLine();
        print("Digite a plataforma do jogo:");
        var plataforma = scan.nextLine();
        print("Digite a data de lançamento do jogo (YYYY-MM-DD)");
        var dataLancamento = scan.nextLine();

        var jogo = new Jogo();
        jogo.nome = nome;
        jogo.plataforma = plataforma;
        jogo.dataLancamento = LocalDate.parse(dataLancamento);

        colecao.add(jogo);
    }

    public void RemoverJogo(Scanner scan, List<Conteudo> colecao){
        print("Digite o numero do jogo a ser removido:");
        var index = scan.nextInt();
        if (index >= 0 && index < colecao.size())
            colecao.remove(index);
        else
            print("Índice inválido!");
    }

    public void ListarJogos(List<Conteudo> colecao){
        print("Jogos cadastrados: ");
        var index = 0;
        for (var jogo : colecao)
            print((index++) + " - " + jogo);
    }
}

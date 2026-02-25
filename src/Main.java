import entities.*;
import enums.MENU_INICIAL;
import enums.MENU_JOGO;
import services.JogoService;
import services.SerieService;

import java.util.ArrayList;
import java.util.Scanner;

import static utils.IOUtils.scanInt;
import static utils.StringUtils.print;

public class Main {

    public static final String MENU_PRINCIPAL = """
                    Digite o tipo de conteúdo que deseja gerenciar:
                    0 - Sair
                    1 - Jogos
                    2 - Séries
                    3 - Filmes
                    """;

    public static void main(String[] args) {
        var colecao = new ArrayList<Conteudo>();

        var jogoService = new JogoService();
        var serieService = new SerieService();

        var scan = new Scanner(System.in);


        while (true) {
            print("Bem vindo ao sistema de gerenciamento de Conteúdos!");
            print(MENU_PRINCIPAL);

            var opcao = MENU_INICIAL.values()[scanInt(scan)];

            switch (opcao) {
                case MENU_INICIAL.SAIR -> System.exit(0);
                case MENU_INICIAL.JOGO -> {
                    var loop = true;
                    while (loop) {
                        print("-----------------------------------");
                        print(jogoService.MENU_JOGOS);
                        var opcaoJogo = MENU_JOGO.values()[scanInt(scan)];

                        switch (opcaoJogo) {
                            case MENU_JOGO.VOLTAR -> loop = false;
                            case MENU_JOGO.ADICIONAR_JOGO -> jogoService.AdicionarJogo(scan, colecao);
                            case MENU_JOGO.REMOVER_JOGO -> jogoService.RemoverJogo(scan, colecao);
                            case MENU_JOGO.LISTAR_JOGOS -> jogoService.ListarJogos(colecao);
                            case MENU_JOGO.AVALIAR_JOGO -> jogoService.AvaliarJogo(scan, colecao);
                            case MENU_JOGO.LISTAR_AVALIACOES_JOGO -> jogoService.ListarAvaliacoes(scan, colecao);
                            default -> print("Opção inválida!");
                        }
                    }
                }
                case MENU_INICIAL.SERIE -> {
                    var loop = true;
                    while (loop) {
                        print("-----------------------------------");
                        print(serieService.MENU_SERIE);
                        var opcaoSerie = scanInt(scan);

                        switch (opcaoSerie) {
                            case 0 -> loop = false;
                            default -> print("Opção inválida!");
                        }
                    }
                }
            }
        }
    }
}
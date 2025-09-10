import entities.Conteudo;
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

            var opcao = scanInt(scan);

            switch (opcao) {
                case 0 -> System.exit(0);
                case 1 -> {
                    var loop = true;
                    while (loop) {
                        print("-----------------------------------");
                        print(jogoService.MENU_JOGOS);
                        opcao = scanInt(scan);

                        switch (opcao) {
                            case 0 -> loop = false;
                            case 1 -> jogoService.AdicionarJogo(scan, colecao);
                            case 2 -> jogoService.RemoverJogo(scan, colecao);
                            case 3 -> jogoService.ListarJogos(colecao);
                            default -> print("Opção inválida!");
                        }
                    }
                }
                case 2 -> {
                    var loop = true;
                    while (loop) {
                        print("-----------------------------------");
                        print(serieService.MENU_SERIE);
                        opcao = scanInt(scan);

                        switch (opcao) {
                            case 0 -> loop = false;
                            default -> print("Opção inválida!");
                        }
                    }
                }
            }
        }
    }
}
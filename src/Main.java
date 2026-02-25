import entities.Conteudo;
import enums.MENU_INICIAL;
import enums.MENU_JOGO;
import services.JogoService;
import services.SerieService;


public static final String MENU_PRINCIPAL = """
        Digite o tipo de conteúdo que deseja gerenciar:
        0 - Sair
        1 - Jogos
        2 - Séries
        3 - Filmes
        """;

void main() {
    var colecao = new ArrayList<Conteudo>();

    var jogoService = new JogoService();
    var serieService = new SerieService();

    while (true) {
        IO.println("Bem vindo ao sistema de gerenciamento de Conteúdos!");
        IO.println(MENU_PRINCIPAL);

        var opcao = MENU_INICIAL.values()[Integer.parseInt(IO.readln())];

        switch (opcao) {
            case MENU_INICIAL.SAIR -> System.exit(0);
            case MENU_INICIAL.JOGO -> {
                var loop = true;
                while (loop) {
                    IO.println("-----------------------------------");
                    IO.println(jogoService.MENU_JOGOS);
                    var opcaoJogo = MENU_JOGO.values()[Integer.parseInt(IO.readln())];

                    switch (opcaoJogo) {
                        case MENU_JOGO.VOLTAR -> loop = false;
                        case MENU_JOGO.ADICIONAR_JOGO -> jogoService.AdicionarJogo(colecao);
                        case MENU_JOGO.REMOVER_JOGO -> jogoService.RemoverJogo(colecao);
                        case MENU_JOGO.LISTAR_JOGOS -> jogoService.ListarJogos(colecao);
                        case MENU_JOGO.AVALIAR_JOGO -> jogoService.AvaliarJogo(colecao);
                        case MENU_JOGO.LISTAR_AVALIACOES_JOGO -> jogoService.ListarAvaliacoes(colecao);
                        default -> IO.println("Opção inválida!");
                    }
                }
            }
            case MENU_INICIAL.SERIE -> {
                var loop = true;
                while (loop) {
                    IO.println("-----------------------------------");
                    IO.println(serieService.MENU_SERIE);
                    var opcaoSerie = Integer.parseInt(IO.readln());

                    switch (opcaoSerie) {
                        case 0 -> loop = false;
                        default -> IO.println("Opção inválida!");
                    }
                }
            }
        }
    }
}
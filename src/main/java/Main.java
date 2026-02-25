import entities.Conteudo;
import enums.MENU_INICIAL;
import enums.MENU_JOGO;
import services.JogoService;
import services.SerieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String MENU_PRINCIPAL = """
            Digite o tipo de conteúdo que deseja gerenciar:
            0 - Sair
            1 - Jogos
            2 - Séries
            3 - Filmes
            """;

    public static void main(String[] args) {
    logger.info("Aplicação iniciada");
    var colecao = new ArrayList<Conteudo>();

    var jogoService = new JogoService();
    var serieService = new SerieService();
    logger.debug("Serviços inicializados");

    while (true) {
        IO.println("Bem vindo ao sistema de gerenciamento de Conteúdos!");
        IO.println(MENU_PRINCIPAL);

        var opcao = MENU_INICIAL.values()[Integer.parseInt(IO.readln())];
        logger.debug("Opção selecionada no menu principal: {}", opcao);

        switch (opcao) {
            case MENU_INICIAL.SAIR -> {
                logger.info("Encerrando aplicação");
                System.exit(0);
            }
            case MENU_INICIAL.JOGO -> {
                logger.info("Entrando no menu de Jogos");
                var loop = true;
                while (loop) {
                    IO.println("-----------------------------------");
                    IO.println(jogoService.MENU_JOGOS);
                    var opcaoJogo = MENU_JOGO.values()[Integer.parseInt(IO.readln())];
                    logger.debug("Opção selecionada no menu de jogos: {}", opcaoJogo);

                    switch (opcaoJogo) {
                        case MENU_JOGO.VOLTAR -> {
                            logger.info("Voltando ao menu principal");
                            loop = false;
                        }
                        case MENU_JOGO.ADICIONAR_JOGO -> jogoService.AdicionarJogo(colecao);
                        case MENU_JOGO.REMOVER_JOGO -> jogoService.RemoverJogo(colecao);
                        case MENU_JOGO.LISTAR_JOGOS -> jogoService.ListarJogos(colecao);
                        case MENU_JOGO.AVALIAR_JOGO -> jogoService.AvaliarJogo(colecao);
                        case MENU_JOGO.LISTAR_AVALIACOES_JOGO -> jogoService.ListarAvaliacoes(colecao);
                        default -> {
                            logger.warn("Opção inválida selecionada: {}", opcaoJogo);
                            IO.println("Opção inválida!");
                        }
                    }
                }
            }
            case MENU_INICIAL.SERIE -> {
                logger.info("Entrando no menu de Séries");
                var loop = true;
                while (loop) {
                    IO.println("-----------------------------------");
                    IO.println(serieService.MENU_SERIE);
                    var opcaoSerie = Integer.parseInt(IO.readln());
                    logger.debug("Opção selecionada no menu de séries: {}", opcaoSerie);

                    switch (opcaoSerie) {
                        case 0 -> {
                            logger.info("Voltando ao menu principal");
                            loop = false;
                        }
                        default -> {
                            logger.warn("Opção inválida selecionada: {}", opcaoSerie);
                            IO.println("Opção inválida!");
                        }
                    }
                }
            }
        }
    }
}
}
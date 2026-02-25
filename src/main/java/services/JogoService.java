package services;

import entities.Avaliacao;
import entities.Conteudo;
import entities.Jogo;
import enums.PLATAFORMA_JOGO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

import static java.lang.IO.println;
import static java.lang.IO.readln;

public class JogoService {
    private static final Logger logger = LogManager.getLogger(JogoService.class);

    public final String MENU_JOGOS = """
                        ---- Escolha uma opção: ----
                        1 - Adicionar jogo
                        2 - Remover jogo
                        3 - Listar jogos
                        4 - Avaliar jogo
                        5 - Listar avaliações de um jogo
                        0 - Voltar
                        ----------------------------
                        """;

    public void AdicionarJogo(List<Conteudo> colecao){
        logger.info("Iniciando adição de novo jogo");
        println("Digite o nome do jogo:");
        var nome = readln();
        println("Digite a plataforma do jogo:");
        println("""
                1 - PC,
                2 - PLAYSTATION,
                3 - XBOX,
                4 - NINTENDO,
                5 - MOBILE,
                6 - VR
                """);
        var plataforma = PLATAFORMA_JOGO.values()[Integer.parseInt(IO.readln()) -1];
        println("Digite a data de lançamento do jogo (YYYY-MM-DD)");
        var dataLancamento = readln();

        var jogo = new Jogo();
        jogo.nome = nome;
        jogo.plataforma = plataforma;
        jogo.dataLancamento = LocalDate.parse(dataLancamento);

        colecao.add(jogo);
        logger.info("Jogo adicionado: {} - Plataforma: {}", nome, plataforma);
        logger.debug("Total de jogos na coleção: {}", colecao.size());
    }

    public void RemoverJogo(List<Conteudo> colecao){
        logger.info("Iniciando remoção de jogo");
        println("Digite o numero do jogo a ser removido:");
        var index = Integer.parseInt(IO.readln());
        if (index >= 0 && index < colecao.size()) {
            var jogoRemovido = colecao.get(index);
            colecao.remove(index);
            logger.info("Jogo removido: {}", jogoRemovido);
            logger.debug("Total de jogos restantes: {}", colecao.size());
        } else {
            logger.warn("Tentativa de remoção com índice inválido: {}", index);
            println("Índice inválido!");
        }
    }

    public void AvaliarJogo(List<Conteudo> colecao){
        logger.info("Iniciando avaliação de jogo");
        println("Digite o número do jogo a ser avaliado:");
        var index = Integer.parseInt(IO.readln());
        var jogo = (Jogo) colecao.get(index);
        logger.debug("Jogo selecionado para avaliação: {}", jogo.nome);

        println("Digite o seu nome de usuário: ");
        var usuario = readln();

        println("Digite a nota (0 a 10): ");
        var nota = Integer.parseInt(IO.readln());
        while(nota > 10 || nota < 0){
            println("Nota inválida! Digite uma nota entre 0 e 10:");
            nota = Integer.parseInt(IO.readln());
        }

        println("Digite um comentário (Opcional):");
        var comentario = readln();

        var avaliacao = new Avaliacao();
        avaliacao.nomeUsuario = usuario;
        avaliacao.setNota(nota);
        avaliacao.comentario = comentario;

        jogo.avaliacoes.add(avaliacao);
        jogo.recalcularMediaAvaliacoes();
        logger.info("Avaliação adicionada - Jogo: {}, Usuário: {}, Nota: {}", jogo.nome, usuario, nota);
        logger.debug("Nova média do jogo: {}", jogo.getMediaAvaliacoes());
    }

    public void ListarJogos(List<Conteudo> colecao){
        logger.info("Listando jogos cadastrados");
        println("Jogos cadastrados: ");
        var index = 0;
        for (var jogo : colecao)
            println((index++) + " - " + jogo);
        logger.debug("Total de jogos listados: {}", colecao.size());
    }

    public void ListarAvaliacoes(List<Conteudo> colecao){
        logger.info("Listando avaliações de jogo");
        println("Digite o número do jogo para ver as avaliações dele:");
        var index = Integer.parseInt(IO.readln());
        var jogo = (Jogo) colecao.get(index);
        logger.debug("Listando avaliações do jogo: {}", jogo.nome);

        for(var avaliacao : jogo.avaliacoes)
            println(avaliacao.toString());
        logger.debug("Total de avaliações exibidas: {}", jogo.avaliacoes.size());
    }
}

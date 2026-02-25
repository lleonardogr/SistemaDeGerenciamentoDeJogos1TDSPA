package services;

import entities.Avaliacao;
import entities.Conteudo;
import entities.Jogo;
import enums.PLATAFORMA_JOGO;
import utils.IOUtils;

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
                        4 - Avaliar jogo
                        5 - Listar avaliações de um jogo
                        0 - Voltar
                        ----------------------------
                        """;

    public void AdicionarJogo(Scanner scan, List<Conteudo> colecao){
        print("Digite o nome do jogo:");
        var nome = scan.nextLine();
        print("Digite a plataforma do jogo:");
        print("""
                1 - PC,
                2 - PLAYSTATION,
                3 - XBOX,
                4 - NINTENDO,
                5 - MOBILE,
                6 - VR
                """);
        var plataforma = PLATAFORMA_JOGO.values()[IOUtils.scanInt(scan) -1];
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

    public void AvaliarJogo(Scanner scan, List<Conteudo> colecao){
        print("Digite o número do jogo a ser avaliado:");
        var index = IOUtils.scanInt(scan);
        var jogo = (Jogo) colecao.get(index);

        print("Digite o seu nome de usuário: ");
        var usuario = scan.nextLine();

        print("Digite a nota (0 a 10): ");
        var nota = IOUtils.scanInt(scan);
        while(nota > 10 || nota < 0){
            print("Nota inválida! Digite uma nota entre 0 e 10:");
            nota = IOUtils.scanInt(scan);
        }

        print("Digite um comentário (Opcional):");
        var comentario = scan.nextLine();

        var avaliacao = new Avaliacao();
        avaliacao.nomeUsuario = usuario;
        avaliacao.setNota(nota);
        avaliacao.comentario = comentario;

        jogo.avaliacoes.add(avaliacao);
        jogo.recalcularMediaAvaliacoes();
    }

    public void ListarJogos(List<Conteudo> colecao){
        print("Jogos cadastrados: ");
        var index = 0;
        for (var jogo : colecao)
            print((index++) + " - " + jogo);
    }

    public void ListarAvaliacoes(Scanner scan, List<Conteudo> colecao){
        print("Digite o número do jogo para ver as avaliações dele:");
        var index = IOUtils.scanInt(scan);
        var jogo = (Jogo) colecao.get(index);

        for(var avaliacao : jogo.avaliacoes)
            print(avaliacao.toString());
    }
}

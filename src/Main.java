import entities.Conteudo;
import entities.Jogo;
import services.JogoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var colecao = new ArrayList<Conteudo>();
        var jogoService = new JogoService();

        while (true) {
            System.out.println("Bem vindo ao sistema de gerenciamento de Conteúdos!");
            System.out.println("""
                    Digite o tipo de conteúdo que deseja gerenciar:
                    1 - Jogos
                    2 - Séries
                    3 - Filmes
                    """);
            var scan = new Scanner(System.in);
            var opcaoConteudo = scan.nextInt();
            scan.nextLine();
            if (opcaoConteudo == 1) {
                System.out.println("-----------------------------------");
                System.out.printf("""
                        Escolha uma opção:
                        1 - Adicionar jogo
                        2 - Remover jogo
                        3 - Listar jogos
                        0 - Sair
                        """);

                var opcao = scan.nextInt();
                scan.nextLine();

                if (opcao == 0)
                    System.exit(0);
                else if (opcao == 1) {
                    jogoService.AdicionarJogo(scan, colecao);
                } else if (opcao == 2) {
                    System.out.println("Digite o numero do jogo a ser removido:");
                    var index = scan.nextInt();
                    if (index >= 0 && index < colecao.size())
                        colecao.remove(index);
                    else
                        System.out.println("Índice inválido!");
                } else if (opcao == 3) {
                    System.out.println("Jogos cadastrados: ");
                    var index = 0;
                    for (var jogo : colecao)
                        System.out.println((index++) + " - " + jogo);
                } else
                    System.out.println("Opção inválida!");
            }
        }
    }
}
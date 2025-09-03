import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var colecao = new ArrayList<Jogo>();

        while (true) {
            System.out.printf("""
                    Bem vindo ao sistema de gerenciamento de jogos!
                    Escolha uma opção:
                    1 - Adicionar jogo
                    2 - Remover jogo
                    3 - Listar jogos
                    0 - Sair
                    """);
            var scan = new Scanner(System.in);
            var opcao = scan.nextInt();
            scan.nextLine();

            if(opcao == 0)
                System.exit(0);
            else if (opcao == 1) {
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
            } else if (opcao == 2) {
                System.out.println("Digite o numero do jogo a ser removido:");
                var index = scan.nextInt();
                if(index >= 0 && index < colecao.size())
                    colecao.remove(index);
                else
                    System.out.println("Índice inválido!");
            } else if (opcao == 3) {
                System.out.println("Jogos cadastrados: ");
                var index = 0;
                for(var jogo : colecao)
                    System.out.println((index++) + " - " + jogo);
            } else
                System.out.println("Opção inválida!");
        }
    }
}
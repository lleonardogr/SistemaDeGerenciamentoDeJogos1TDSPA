import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var jogos = new ArrayList<String>();

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
                jogos.add(scan.nextLine());
            } else if (opcao == 2) {
                System.out.println("Digite o numero do jogo a ser removido:");
                var index = scan.nextInt();
                if(index >= 0 && index < jogos.size())
                    jogos.remove(index);
                else
                    System.out.println("Índice inválido!");
            } else if (opcao == 3) {
                System.out.println("Jogos cadastrados: ");
                jogos.forEach(System.out::println);
            } else
                System.out.println("Opção inválida!");
        }
    }
}
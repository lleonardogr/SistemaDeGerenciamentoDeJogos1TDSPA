import java.time.LocalDate;

public class Jogo {
    String nome;
    String plataforma;
    LocalDate dataLancamento;

    @Override
    public String toString() {
        return "Jogo{" +
                "nome='" + nome + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", dataLancamento=" + dataLancamento +
                '}';
    }
}

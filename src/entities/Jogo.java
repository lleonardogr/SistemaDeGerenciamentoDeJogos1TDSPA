package entities;

public class Jogo extends Conteudo {
    public String plataforma;

    @Override
    public String toString() {
        return "entities.Jogo{" +
                "nome='" + nome + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", dataLancamento=" + dataLancamento +
                '}';
    }
}

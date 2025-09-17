package entities;

import java.time.LocalDate;

public class Avaliacao {
    public String nomeUsuario;
    public int nota;
    public String comentario;
    public LocalDate dataLancamento;

    @Override
    public String toString() {
        return "Avaliacao{" +
                "nomeUsuario='" + nomeUsuario + '\'' +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", dataLancamento=" + dataLancamento +
                '}';
    }
}

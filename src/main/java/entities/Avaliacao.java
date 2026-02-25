package entities;

import java.time.LocalDate;

public class Avaliacao {
    public String nomeUsuario;
    private int nota;
    public String comentario;
    public LocalDate dataLancamento;

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        if(nota < 0 || nota > 10)
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        this.nota = nota;
    }

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

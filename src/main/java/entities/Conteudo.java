package entities;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Conteudo {
    public String nome;
    public LocalDate dataLancamento;
    public ArrayList<Avaliacao> avaliacoes = new ArrayList<>();
}

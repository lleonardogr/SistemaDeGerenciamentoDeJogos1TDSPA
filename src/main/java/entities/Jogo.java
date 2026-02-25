package entities;

import enums.PLATAFORMA_JOGO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Jogo extends Conteudo {
    private static final Logger logger = LogManager.getLogger(Jogo.class);

    public PLATAFORMA_JOGO plataforma; // Campos com apenas atribuição eles são publicos
    private double mediaAvaliacoes = calcularAvaliacoes(); // Campos calculados são privados

    public double calcularAvaliacoes(){
        if(avaliacoes.isEmpty()) return 0;
        int soma = 0;
        for(var avaliacao : avaliacoes)
            soma += avaliacao.getNota();

        double media = (double) soma / avaliacoes.size();
        logger.debug("Média calculada para jogo '{}': {} (baseada em {} avaliações)", nome, media, avaliacoes.size());
        return media;
    }

    public void recalcularMediaAvaliacoes(){
        double mediaAnterior = mediaAvaliacoes;
        mediaAvaliacoes = calcularAvaliacoes();
        logger.debug("Média recalculada para jogo '{}': {} -> {}", nome, mediaAnterior, mediaAvaliacoes);
    }

    public double getMediaAvaliacoes(){
        return mediaAvaliacoes;
    }

    @Override
    public String toString() {
        return "Jogo{" +
                "nota=" + mediaAvaliacoes +
                ", dataLancamento=" + dataLancamento +
                ", nome='" + nome + '\'' +
                ", plataforma='" + plataforma + '\'' +
                '}';
    }
}

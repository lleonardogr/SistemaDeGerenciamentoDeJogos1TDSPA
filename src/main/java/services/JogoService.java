package services;

import entities.Avaliacao;
import entities.Conteudo;
import entities.Jogo;
import enums.PLATAFORMA_JOGO;
import infra.DatabaseConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.IO.println;
import static java.lang.IO.readln;

public class JogoService {
    private static final Logger logger = LogManager.getLogger(JogoService.class);

    public final String MENU_JOGOS = """
                        ---- Escolha uma opção: ----
                        1 - Adicionar jogo
                        2 - Remover jogo
                        3 - Listar jogos
                        4 - Avaliar jogo
                        5 - Listar avaliações de um jogo
                        6 - Buscar jogo por ID
                        0 - Voltar
                        ----------------------------
                        """;

    public void AdicionarJogo(List<Conteudo> colecao){
        logger.info("Iniciando adição de novo jogo");
        println("Digite o nome do jogo:");
        var nome = readln();
        println("Digite a plataforma do jogo:");
        println("""
                1 - PC,
                2 - PLAYSTATION,
                3 - XBOX,
                4 - NINTENDO,
                5 - MOBILE,
                6 - VR
                """);
        var plataforma = PLATAFORMA_JOGO.values()[Integer.parseInt(IO.readln()) -1];
        println("Digite a data de lançamento do jogo (YYYY-MM-DD)");
        var dataLancamento = readln();

        var jogo = new Jogo();
        jogo.nome = nome;
        jogo.plataforma = plataforma;
        jogo.dataLancamento = LocalDate.parse(dataLancamento);

        var sql = "INSERT INTO Jogo (NOME, DATA_LANCAMENTO, PLATAFORMA, MEDIA_AVALIACOES) VALUES " +
                "(?,?,?,?)";
        try(var conn = DatabaseConfig.getConnection()){
            var stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setDate(2, java.sql.Date.valueOf(dataLancamento));
            stmt.setString(3, plataforma.name());
            stmt.setInt(4,0);
            var rowsAffected = stmt.executeUpdate();
            logger.info("Jogo inserido com sucesso");
        }
        catch (SQLException ex){
            logger.error("Erro ao adicionar jogo");
        }

        logger.info("Jogo adicionado: {} - Plataforma: {}", nome, plataforma);
        logger.debug("Total de jogos na coleção: {}", colecao.size());
    }

    public void RemoverJogo(){
        logger.info("Iniciando remoção de jogo");
        println("Digite o numero do jogo a ser removido:");
        var id = Integer.parseInt(IO.readln());
        var sql = "DELETE FROM Jogo WHERE id = ?";
        try(var conn = DatabaseConfig.getConnection()){
            var stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            var rowsAffected = stmt.executeUpdate();
            logger.info(
                    "Jogo removido - ID: {}, Linhas afetadas: {}",
                    id, rowsAffected);
        }
        catch(SQLException ex){
            logger.error("Erro ao remover jogo de id: " + id);
        }
    }

    public void AvaliarJogo(List<Conteudo> colecao){
        logger.info("Iniciando avaliação de jogo");
        println("Digite o número do jogo a ser avaliado:");
        var index = Integer.parseInt(IO.readln());
        var jogo = (Jogo) colecao.get(index);
        logger.debug("Jogo selecionado para avaliação: {}", jogo.nome);

        println("Digite o seu nome de usuário: ");
        var usuario = readln();

        println("Digite a nota (0 a 10): ");
        var nota = Integer.parseInt(IO.readln());
        while(nota > 10 || nota < 0){
            println("Nota inválida! Digite uma nota entre 0 e 10:");
            nota = Integer.parseInt(IO.readln());
        }

        println("Digite um comentário (Opcional):");
        var comentario = readln();

        var avaliacao = new Avaliacao();
        avaliacao.nomeUsuario = usuario;
        avaliacao.setNota(nota);
        avaliacao.comentario = comentario;

        jogo.avaliacoes.add(avaliacao);
        jogo.recalcularMediaAvaliacoes();
        logger.info("Avaliação adicionada - Jogo: {}, Usuário: {}, Nota: {}", jogo.nome, usuario, nota);
        logger.debug("Nova média do jogo: {}", jogo.getMediaAvaliacoes());
    }

    public void ListarJogos(){
        logger.info("Listando jogos cadastrados");
        println("Jogos cadastrados: ");
        var index = 0;
        var colecao = new ArrayList<Jogo>();

        try (var conn = DatabaseConfig.getConnection()) {
            var stmt = conn.prepareStatement("SELECT * FROM JOGO");
            var rs = stmt.executeQuery();
            while (rs.next()) {
                var jogo = new Jogo();
                jogo.nome = rs.getString("nome");
                jogo.dataLancamento = rs.getDate("data_lancamento").toLocalDate();
                jogo.plataforma = PLATAFORMA_JOGO.valueOf(rs.getString("plataforma"));
                colecao.add(jogo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Erro ao listar jogos do banco de dados", e);
        }

        for (var jogo : colecao)
            println((index++) + " - " + jogo);
        logger.debug("Total de jogos listados: {}", colecao.size());
    }

    public void ListarAvaliacoes(List<Conteudo> colecao){
        logger.info("Listando avaliações de jogo");
        println("Digite o número do jogo para ver as avaliações dele:");
        var index = Integer.parseInt(IO.readln());
        var jogo = (Jogo) colecao.get(index);
        logger.debug("Listando avaliações do jogo: {}", jogo.nome);

        for(var avaliacao : jogo.avaliacoes)
            println(avaliacao.toString());
        logger.debug("Total de avaliações exibidas: {}", jogo.avaliacoes.size());
    }

    public void BuscarJogoPorId() {
        Jogo jogo;
        logger.info("Buscando jogo por ID");
        println("Digite o ID do jogo:");
        var id = Integer.parseInt(IO.readln());
        try(var conn = DatabaseConfig.getConnection()){
            var stmt = conn.prepareStatement("SELECT * FROM JOGO WHERE ID = ?");
            stmt.setInt(1, id);
            var rs = stmt.executeQuery();
            if(rs.next()){
                jogo = new Jogo();
                jogo.nome = rs.getString("nome");
                jogo.dataLancamento = rs.getDate("data_lancamento").toLocalDate();
                jogo.plataforma = PLATAFORMA_JOGO.valueOf(rs.getString("plataforma"));
                println(jogo);
                logger.info("Jogo encontrado: {}", jogo.nome);
            }
            else {
                println("Jogo não encontrado!");
                logger.warn("Nenhum jogo encontrado com ID: {}", id);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            logger.error("Erro ao buscar jogo por ID no banco de dados", e);
        }
    }
}

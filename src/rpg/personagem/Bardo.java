package rpg.personagem;

import java.util.Random;

import rpg.item.Arma;

// Classe "diferente": o dano da habilidade depende da sorte na nota tocada
public class Bardo extends Jogador {
    private static final int CUSTO = 10;
    private Random sorte = new Random();

    public Bardo(String nome) {
        super(nome, 115, 17, 5, 45, new Arma("Alaude velho", 4));
    }

    @Override
    public boolean usarHabilidade(Personagem alvo) {
        if (!prepararHabilidade(CUSTO)) {
            return false;
        }
        int nota = sorte.nextInt(6) + 1; // 1 a 6
        int dano;
        if (nota == 6) {
            dano = getAtaqueTotal() * 2;
            System.out.println("NOTA PERFEITA! Ate os monstros choram de emocao!");
        } else if (nota == 1) {
            dano = 5;
            System.out.println("Desafinou feio... " + alvo.getNome() + " tapa os ouvidos de dor (e vergonha alheia).");
        } else {
            dano = getAtaqueTotal() + nota * 3;
            System.out.println("Um solo animado de nota " + nota + "!");
        }
        alvo.receberDano(dano); // som ignora armadura
        System.out.println(getNome() + " toca o SOLO DE ALAUDE e causa " + dano + " de dano!");
        return true;
    }

    @Override
    public String getNomeClasse() { return "Bardo"; }

    @Override
    public String getDescricaoHabilidade() {
        return "Solo de alaude (" + CUSTO + " energia): dano por sorte (1 a 6), ignora defesa";
    }

    @Override
    public Arma criarArmaReforcada() { return new Arma("Alaude encantado", 8); }
}

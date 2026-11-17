package rpg.personagem;

import rpg.item.Arma;

public class Mago extends Jogador {
    private static final int CUSTO = 12;

    public Mago(String nome) {
        super(nome, 100, 22, 4, 50, new Arma("Cajado", 4));
    }

    @Override
    public boolean usarHabilidade(Personagem alvo) {
        if (!prepararHabilidade(CUSTO)) {
            return false;
        }
        int dano = Math.max(1, getAtaqueTotal() + 12 - alvo.getDefesa() / 2);
        alvo.receberDano(dano);
        System.out.println(getNome() + " lanca uma BOLA DE FOGO e causa " + dano + " de dano!");
        return true;
    }

    @Override
    public String getNomeClasse() { return "Mago"; }

    @Override
    public String getDescricaoHabilidade() {
        return "Bola de fogo (" + CUSTO + " energia): +12 de dano e so metade da defesa conta";
    }

    @Override
    public Arma criarArmaReforcada() { return new Arma("Cajado arcano", 8); }
}

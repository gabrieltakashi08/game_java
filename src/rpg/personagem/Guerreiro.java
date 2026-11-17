package rpg.personagem;

import rpg.item.Arma;

public class Guerreiro extends Jogador {
    private static final int CUSTO = 10;

    public Guerreiro(String nome) {
        super(nome, 140, 18, 8, 30, new Arma("Espada curta", 4));
    }

    @Override
    public boolean usarHabilidade(Personagem alvo) {
        if (!prepararHabilidade(CUSTO)) {
            return false;
        }
        int dano = Math.max(1, 2 * getAtaqueTotal() - alvo.getDefesa());
        alvo.receberDano(dano);
        System.out.println(getNome() + " usa GOLPE PODEROSO e causa " + dano + " de dano!");
        return true;
    }

    @Override
    public String getNomeClasse() { return "Guerreiro"; }

    @Override
    public String getDescricaoHabilidade() {
        return "Golpe poderoso (" + CUSTO + " energia): dano dobrado menos a defesa";
    }

    @Override
    public Arma criarArmaReforcada() { return new Arma("Espada longa", 8); }
}

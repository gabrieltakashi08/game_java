package rpg.personagem;

import rpg.item.Arma;

public class Paladino extends Jogador {
    private static final int CUSTO = 12;
    private static final int CURA = 20;

    public Paladino(String nome) {
        super(nome, 130, 17, 9, 36, new Arma("Martelo", 4));
    }

    @Override
    public boolean usarHabilidade(Personagem alvo) {
        if (!prepararHabilidade(CUSTO)) {
            return false;
        }
        int dano = Math.max(1, getAtaqueTotal() - alvo.getDefesa());
        alvo.receberDano(dano);
        int curou = recuperarVida(CURA);
        System.out.println(getNome() + " invoca a LUZ SAGRADA: " + dano + " de dano e recupera " + curou + " de vida!");
        return true;
    }

    @Override
    public String getNomeClasse() { return "Paladino"; }

    @Override
    public String getDescricaoHabilidade() {
        return "Luz sagrada (" + CUSTO + " energia): ataque comum + cura " + CURA + " de vida";
    }

    @Override
    public Arma criarArmaReforcada() { return new Arma("Martelo sagrado", 8); }
}

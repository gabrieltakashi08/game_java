package rpg.personagem;

import rpg.item.Arma;

public class Arqueiro extends Jogador {
    private static final int CUSTO = 8;

    public Arqueiro(String nome) {
        super(nome, 120, 20, 6, 40, new Arma("Arco", 4));
    }

    @Override
    public boolean usarHabilidade(Personagem alvo) {
        if (!prepararHabilidade(CUSTO)) {
            return false;
        }
        int dano = getAtaqueTotal() + 4; // ignora a defesa
        alvo.receberDano(dano);
        System.out.println(getNome() + " dispara um TIRO PRECISO e causa " + dano + " de dano!");
        return true;
    }

    @Override
    public String getNomeClasse() { return "Arqueiro"; }

    @Override
    public String getDescricaoHabilidade() {
        return "Tiro preciso (" + CUSTO + " energia): ataque +4 ignorando a defesa";
    }

    @Override
    public Arma criarArmaReforcada() { return new Arma("Arco longo", 8); }
}

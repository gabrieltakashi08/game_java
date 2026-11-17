package rpg.personagem;

public class Chefe extends Monstro {
    private static final int BONUS_ATAQUE = 4;

    public Chefe(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = Math.max(1, getAtaqueTotal() + BONUS_ATAQUE - alvo.getDefesa());
        alvo.receberDano(dano);
        System.out.println(getNome() + " desfere um GOLPE ESMAGADOR e causa " + dano + " de dano!");
    }
}

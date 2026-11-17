package rpg.item;

public class Arma extends Item {
    private int bonusAtaque;

    public Arma(String nome, int bonusAtaque) {
        super(nome, "Arma que aumenta o ataque");
        this.bonusAtaque = bonusAtaque;
    }

    public int getBonusAtaque() { return bonusAtaque; }

    @Override
    public String getTipo() { return "Arma"; }

    @Override
    public String getEfeito() { return "+" + bonusAtaque + " de ataque"; }
}

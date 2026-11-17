package rpg.item;

import rpg.personagem.Jogador;

public class Pocao extends Item implements Consumivel {
    private int cura;

    public Pocao(String nome, int cura) {
        super(nome, "Liquido vermelho com gosto de morango");
        this.cura = cura;
    }

    @Override
    public boolean usar(Jogador jogador) {
        if (!jogador.estaVivo()) {
            System.out.println("A pocao nao revive ninguem.");
            return false;
        }
        if (jogador.vidaCheia()) {
            System.out.println("Sua vida ja esta cheia! Guarde a pocao.");
            return false;
        }
        int recuperado = jogador.recuperarVida(cura);
        System.out.println(jogador.getNome() + " bebe " + getNome() + " e recupera " + recuperado + " de vida.");
        return true;
    }

    @Override
    public String getTipo() { return "Consumivel"; }

    @Override
    public String getEfeito() { return "recupera " + cura + " de vida"; }
}

package rpg.personagem;

import java.util.ArrayList;
import java.util.Random;

public class Monstro extends Personagem {
    private ArrayList<String> provocacoes = new ArrayList<>();
    private Random sorte = new Random();

    public Monstro(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
    }

    public void adicionarProvocacao(String frase) {
        provocacoes.add(frase);
    }

    // Diferencial: os monstros falam besteira durante a luta
    public String provocar() {
        if (provocacoes.isEmpty()) {
            return "Grrr...";
        }
        return provocacoes.get(sorte.nextInt(provocacoes.size()));
    }
}

package rpg.item;

import rpg.personagem.Jogador;

public interface Consumivel {
    // Retorna true se o item foi realmente usado (e deve sair do inventario)
    boolean usar(Jogador jogador);
}

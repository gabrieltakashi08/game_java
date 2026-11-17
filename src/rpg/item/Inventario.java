package rpg.item;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens = new ArrayList<>();

    public void adicionar(Item item) {
        if (item != null) {
            itens.add(item);
        }
    }

    // Posicao comeca em 1 para facilitar no menu
    public Item getItem(int posicao) {
        if (posicao < 1 || posicao > itens.size()) {
            return null;
        }
        return itens.get(posicao - 1);
    }

    public boolean remover(Item item) {
        return itens.remove(item); // remove apenas esse objeto
    }

    public boolean contem(Item item) {
        return itens.contains(item);
    }

    public int tamanho() {
        return itens.size();
    }

    public void listar(Arma equipada) {
        if (itens.isEmpty()) {
            System.out.println("Inventario vazio.");
            return;
        }
        for (int i = 0; i < itens.size(); i++) {
            Item item = itens.get(i);
            String marca = (item == equipada) ? "  [EQUIPADA]" : "";
            System.out.println((i + 1) + " - " + item + marca);
        }
    }
}

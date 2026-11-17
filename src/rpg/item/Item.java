package rpg.item;

public abstract class Item {
    private String nome;
    private String descricao;

    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public abstract String getTipo();

    public abstract String getEfeito();

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return nome + " (" + getTipo() + ") - " + getEfeito();
    }
}

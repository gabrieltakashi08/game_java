package rpg.personagem;

import rpg.item.Arma;
import rpg.item.Consumivel;
import rpg.item.Inventario;
import rpg.item.Item;
import rpg.item.Pocao;

public abstract class Jogador extends Personagem {
    private int energiaMaxima;
    private int energiaAtual;
    private Inventario inventario;
    private Arma armaEquipada;
    private String gritoDeGuerra;

    public Jogador(String nome, int vida, int ataque, int defesa, int energia, Arma armaInicial) {
        super(nome, vida, ataque, defesa);
        this.energiaMaxima = energia;
        this.energiaAtual = energia;
        this.inventario = new Inventario();
        this.gritoDeGuerra = "Por gloria!";

        // Todo jogador comeca com a arma equipada e duas pocoes
        inventario.adicionar(armaInicial);
        inventario.adicionar(new Pocao("Pocao de cura", 30));
        inventario.adicionar(new Pocao("Pocao de cura", 30));
        equiparArma(armaInicial);
    }

    // Cada especializacao define sua habilidade (polimorfismo)
    public abstract boolean usarHabilidade(Personagem alvo);

    public abstract String getNomeClasse();

    public abstract String getDescricaoHabilidade();

    // Recompensa da segunda batalha: versao reforcada da arma da classe
    public abstract Arma criarArmaReforcada();

    @Override
    public int getAtaqueTotal() {
        if (armaEquipada == null) {
            return getAtaque();
        }
        return getAtaque() + armaEquipada.getBonusAtaque();
    }

    // Usado pelas subclasses: verifica e gasta a energia, e solta o grito de guerra
    protected boolean prepararHabilidade(int custo) {
        if (energiaAtual < custo) {
            System.out.println("Energia insuficiente! (precisa de " + custo + ", tem " + energiaAtual + ")");
            return false;
        }
        energiaAtual -= custo;
        System.out.println(getNome() + " grita: \"" + gritoDeGuerra + "\"");
        return true;
    }

    public boolean equiparArma(Arma arma) {
        if (arma == null || !inventario.contem(arma)) {
            System.out.println("Essa arma nao esta no inventario.");
            return false;
        }
        armaEquipada = arma;
        return true;
    }

    public boolean usarItem(Item item) {
        if (item == null || !inventario.contem(item)) {
            System.out.println("Item nao encontrado no inventario.");
            return false;
        }
        if (!(item instanceof Consumivel)) {
            System.out.println(item.getNome() + " nao pode ser consumido.");
            return false;
        }
        Consumivel consumivel = (Consumivel) item;
        if (consumivel.usar(this)) {
            inventario.remover(item);
            return true;
        }
        return false;
    }

    // Chamado entre batalhas
    public void restaurar() {
        restaurarVida();
        energiaAtual = energiaMaxima;
    }

    public void setGritoDeGuerra(String grito) {
        if (grito != null && !grito.isBlank()) {
            this.gritoDeGuerra = grito;
        }
    }

    public int getEnergiaAtual() { return energiaAtual; }
    public int getEnergiaMaxima() { return energiaMaxima; }
    public Inventario getInventario() { return inventario; }
    public Arma getArmaEquipada() { return armaEquipada; }

    @Override
    public String toString() {
        return getNomeClasse() + " " + super.toString()
                + " | Energia: " + energiaAtual + "/" + energiaMaxima
                + "\nArma: " + (armaEquipada == null ? "nenhuma" : armaEquipada.getNome())
                + "\nHabilidade: " + getDescricaoHabilidade()
                + "\nGrito de guerra: \"" + gritoDeGuerra + "\"";
    }
}

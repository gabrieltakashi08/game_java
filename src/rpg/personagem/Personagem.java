package rpg.personagem;

// Classe base de todos os personagens (jogador e monstros)
public abstract class Personagem {
    private String nome;
    private int vidaMaxima;
    private int vidaAtual;
    private int ataque;
    private int defesa;

    public Personagem(String nome, int vidaMaxima, int ataque, int defesa) {
        this.nome = nome;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    // Jogador sobrescreve para somar o bonus da arma
    public int getAtaqueTotal() {
        return ataque;
    }

    public void atacar(Personagem alvo) {
        int dano = Math.max(1, getAtaqueTotal() - alvo.getDefesa());
        alvo.receberDano(dano);
        System.out.println(nome + " ataca " + alvo.getNome() + " e causa " + dano + " de dano.");
    }

    // Apenas aplica o dano ja calculado (a defesa ja foi descontada)
    public void receberDano(int dano) {
        if (dano < 0) {
            dano = 0;
        }
        vidaAtual = Math.max(0, vidaAtual - dano);
    }

    // Retorna quanto de vida foi realmente recuperado
    public int recuperarVida(int pontos) {
        if (!estaVivo() || pontos <= 0) {
            return 0;
        }
        int antes = vidaAtual;
        vidaAtual = Math.min(vidaMaxima, vidaAtual + pontos);
        return vidaAtual - antes;
    }

    public void restaurarVida() {
        vidaAtual = vidaMaxima;
    }

    public boolean estaVivo() {
        return vidaAtual > 0;
    }

    public boolean vidaCheia() {
        return vidaAtual == vidaMaxima;
    }

    public String getNome() { return nome; }
    public int getVidaAtual() { return vidaAtual; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }

    @Override
    public String toString() {
        return nome + " | Vida: " + vidaAtual + "/" + vidaMaxima
                + " | Ataque: " + getAtaqueTotal() + " | Defesa: " + defesa;
    }
}

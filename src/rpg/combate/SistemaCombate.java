package rpg.combate;

import rpg.app.Entrada;
import rpg.item.Item;
import rpg.personagem.Jogador;
import rpg.personagem.Monstro;

public class SistemaCombate {
    private Jogador jogador;
    private Monstro inimigo;
    private Entrada entrada;

    public SistemaCombate(Jogador jogador, Monstro inimigo, Entrada entrada) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.entrada = entrada;
    }

    // Retorna true se o jogador venceu
    public boolean iniciar() {
        System.out.println("\n>>> " + inimigo.getNome() + " aparece: \"" + inimigo.provocar() + "\"");
        int turno = 1;

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            mostrarStatus(turno);

            if (!turnoDoJogador()) {
                continue; // acao recusada ou voltou: o inimigo nao age
            }
            if (!inimigo.estaVivo()) {
                break; // inimigo derrotado nao revida
            }

            System.out.println(inimigo.getNome() + ": \"" + inimigo.provocar() + "\"");
            inimigo.atacar(jogador); // polimorfismo: o Chefe usa a propria versao
            turno++;
        }

        if (jogador.estaVivo()) {
            System.out.println("\n*** " + inimigo.getNome() + " foi derrotado! ***");
            return true;
        }
        System.out.println("\n" + jogador.getNome() + " caiu em combate...");
        return false;
    }

    private void mostrarStatus(int turno) {
        System.out.println("\n----- Turno " + turno + " -----");
        System.out.println(jogador.getNome() + "  Vida: " + jogador.getVidaAtual() + "/" + jogador.getVidaMaxima()
                + "  Energia: " + jogador.getEnergiaAtual() + "/" + jogador.getEnergiaMaxima());
        System.out.println(inimigo.getNome() + "  Vida: " + inimigo.getVidaAtual() + "/" + inimigo.getVidaMaxima());
        System.out.println("1 - Ataque comum");
        System.out.println("2 - Habilidade: " + jogador.getDescricaoHabilidade());
        System.out.println("3 - Usar item");
    }

    // Retorna true se a acao gastou o turno
    private boolean turnoDoJogador() {
        int opcao = entrada.lerOpcao("Escolha: ", 1, 3);
        if (opcao == 1) {
            jogador.atacar(inimigo);
            return true;
        } else if (opcao == 2) {
            return jogador.usarHabilidade(inimigo);
        } else {
            return escolherItem();
        }
    }

    private boolean escolherItem() {
        System.out.println("\nInventario:");
        jogador.getInventario().listar(jogador.getArmaEquipada());
        System.out.println("0 - Voltar");
        int pos = entrada.lerOpcao("Item: ", 0, jogador.getInventario().tamanho());
        if (pos == 0) {
            return false;
        }
        Item item = jogador.getInventario().getItem(pos);
        return jogador.usarItem(item);
    }
}

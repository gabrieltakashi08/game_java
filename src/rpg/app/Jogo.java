package rpg.app;

import java.util.ArrayList;

import rpg.combate.SistemaCombate;
import rpg.item.Arma;
import rpg.item.Item;
import rpg.item.Pocao;
import rpg.personagem.Arqueiro;
import rpg.personagem.Bardo;
import rpg.personagem.Chefe;
import rpg.personagem.Guerreiro;
import rpg.personagem.Jogador;
import rpg.personagem.Mago;
import rpg.personagem.Monstro;
import rpg.personagem.Paladino;

public class Jogo {
    private Entrada entrada = new Entrada();
    private Jogador jogador;
    private ArrayList<Monstro> inimigos;
    private int etapaAtual;

    private String[] locais = {
        "na ENTRADA da fortaleza, onde um portao rangendo esconde um cheiro de meia velha",
        "no CORREDOR escuro, cheio de ossos espalhados e teias de aranha",
        "na CAMARA FINAL, onde o trono do Guardiao brilha sob a luz das tochas"
    };

    public void executar() {
        System.out.println("=========================================");
        System.out.println("        A FORTALEZA DO GUARDIAO        ");
        System.out.println("=========================================");
        while (true) {
            System.out.println("\n1 - Novo jogo");
            System.out.println("0 - Sair");
            int opcao = entrada.lerOpcao("Escolha: ", 0, 1);
            if (opcao == 0) {
                System.out.println("Ate a proxima, aventureiro!");
                return;
            }
            novoJogo();
        }
    }

    // Sempre cria objetos novos: nada da partida anterior e aproveitado
    private void novoJogo() {
        jogador = criarPersonagem();
        inimigos = criarInimigos();
        etapaAtual = 0;
        menuAventura();
    }

    private Jogador criarPersonagem() {
        String nome = "";
        while (nome.isEmpty()) {
            nome = entrada.lerTexto("\nNome do seu personagem: ");
        }

        System.out.println("\nEscolha sua classe:");
        System.out.println("1 - Guerreiro (vida alta, Golpe poderoso)");
        System.out.println("2 - Mago      (ataque alto, Bola de fogo)");
        System.out.println("3 - Arqueiro  (equilibrado, Tiro preciso)");
        System.out.println("4 - Paladino  (defesa alta, Luz sagrada que cura)");
        System.out.println("5 - Bardo     (sorte pura, Solo de alaude)");
        int classe = entrada.lerOpcao("Classe: ", 1, 5);

        Jogador novo;
        switch (classe) {
            case 1: novo = new Guerreiro(nome); break;
            case 2: novo = new Mago(nome); break;
            case 3: novo = new Arqueiro(nome); break;
            case 4: novo = new Paladino(nome); break;
            default: novo = new Bardo(nome); break;
        }

        String grito = entrada.lerTexto("Qual o seu grito de guerra? (Enter para o padrao): ");
        novo.setGritoDeGuerra(grito);

        System.out.println("\n" + novo.getNome() + ", o(a) " + novo.getNomeClasse()
                + ", chega aos portoes da fortaleza tomada por criaturas...");
        return novo;
    }

    private ArrayList<Monstro> criarInimigos() {
        ArrayList<Monstro> lista = new ArrayList<>();

        Monstro goblin = new Monstro("Goblin da entrada", 50, 12, 3);
        goblin.adicionarProvocacao("Sua mae e uma tartaruga!");
        goblin.adicionarProvocacao("Eu cheiro mal, mas voce luta mal!");
        goblin.adicionarProvocacao("Hihihi, vou roubar suas botas!");

        Monstro esqueleto = new Monstro("Esqueleto do corredor", 75, 16, 5);
        esqueleto.adicionarProvocacao("Nao tenho estomago pra essa luta... literalmente.");
        esqueleto.adicionarProvocacao("Sinto isso nos meus ossos: voce vai perder!");
        esqueleto.adicionarProvocacao("*barulho de ossos chacoalhando*");

        Monstro guardiao = new Chefe("Guardiao da Fortaleza", 130, 20, 7);
        guardiao.adicionarProvocacao("VOCE NAO PASSARA!... ah, espera, essa fala ja tem dono.");
        guardiao.adicionarProvocacao("Trezentos anos guardando essa porta e nem um cafezinho.");
        guardiao.adicionarProvocacao("Ajoelhe-se diante do Guardiao!");

        lista.add(goblin);
        lista.add(esqueleto);
        lista.add(guardiao); // chefe sempre por ultimo
        return lista;
    }

    private void menuAventura() {
        while (true) {
            Monstro proximo = inimigos.get(etapaAtual);
            System.out.println("\n=== Etapa " + (etapaAtual + 1) + " de " + inimigos.size()
                    + ": " + proximo.getNome() + " ===");
            System.out.println("1 - Consultar personagem");
            System.out.println("2 - Consultar inventario e trocar equipamento");
            System.out.println("3 - Iniciar a proxima batalha");
            System.out.println("0 - Encerrar a aventura");
            int opcao = entrada.lerOpcao("Escolha: ", 0, 3);

            if (opcao == 0) {
                System.out.println("Voce fugiu da fortaleza. Os goblins comemoram.");
                return;
            } else if (opcao == 1) {
                System.out.println("\n" + jogador);
            } else if (opcao == 2) {
                menuInventario();
            } else {
                boolean continuar = batalhar();
                if (!continuar) {
                    return;
                }
            }
        }
    }

    // Retorna false quando a aventura acabou (vitoria final ou derrota)
    private boolean batalhar() {
        Monstro inimigo = inimigos.get(etapaAtual);
        System.out.println("\nVoce entra " + locais[etapaAtual] + ".");

        SistemaCombate combate = new SistemaCombate(jogador, inimigo, entrada);
        if (!combate.iniciar()) {
            System.out.println("\n========== GAME OVER ==========");
            System.out.println("O " + inimigo.getNome() + " danca em cima do seu escudo.");
            return false;
        }

        darRecompensa();
        etapaAtual++;

        if (etapaAtual == inimigos.size()) {
            System.out.println("\n=========================================");
            System.out.println(" A FORTALEZA FOI LIBERTADA! Parabens, " + jogador.getNome() + "!");
            System.out.println(" Os bardos vao cantar sobre voce (desafinados).");
            System.out.println("=========================================");
            return false;
        }

        jogador.restaurar();
        System.out.println("Voce descansa um pouco: vida e energia restauradas.");
        return true;
    }

    // Chamado uma unica vez por etapa, pois etapaAtual avanca logo depois
    private void darRecompensa() {
        if (etapaAtual == 0) {
            jogador.getInventario().adicionar(new Pocao("Pocao de cura", 30));
            System.out.println("Recompensa: o Goblin deixou cair uma Pocao de cura!");
        } else if (etapaAtual == 1) {
            Arma reforcada = jogador.criarArmaReforcada();
            jogador.getInventario().adicionar(reforcada);
            System.out.println("Recompensa: voce encontrou " + reforcada.getNome()
                    + " (" + reforcada.getEfeito() + ")! Equipe pelo inventario.");
        }
    }

    private void menuInventario() {
        while (true) {
            System.out.println("\nInventario:");
            jogador.getInventario().listar(jogador.getArmaEquipada());
            System.out.println("Digite o numero de uma arma para equipar, ou 0 para voltar.");
            int pos = entrada.lerOpcao("Escolha: ", 0, jogador.getInventario().tamanho());
            if (pos == 0) {
                return;
            }
            Item item = jogador.getInventario().getItem(pos);
            if (item instanceof Arma) {
                jogador.equiparArma((Arma) item);
                System.out.println("Equipado: " + item.getNome() + "! Ataque total: " + jogador.getAtaqueTotal());
            } else {
                System.out.println("So armas podem ser equipadas.");
            }
        }
    }
}

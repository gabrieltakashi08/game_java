package rpg.app;

import java.util.Scanner;

// Unica instancia de Scanner do programa
public class Entrada {
    private Scanner scanner = new Scanner(System.in);

    public String lerTexto(String mensagem) {
        System.out.print(mensagem);
        if (!scanner.hasNextLine()) {
            System.exit(0); // entrada acabou
        }
        return scanner.nextLine().trim();
    }

    // Repete ate o usuario digitar um numero entre min e max
    public int lerOpcao(String mensagem, int min, int max) {
        while (true) {
            String texto = lerTexto(mensagem);
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= min && valor <= max) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Opcao invalida! Digite um numero de " + min + " a " + max + ".");
        }
    }
}

package pt.com.rubira.CryptoCurrency.principal;

import pt.com.rubira.CryptoCurrency.service.ConsumoApi;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();

    private final String URL_BASE = "https://www.mercadobitcoin.net/api/";

    public void ExibeMenu() throws IOException {
        boolean continuar = true;

        while (continuar) {
            System.out.println(" *** OPÇÕES CRYPTO ***");
            System.out.println("1. Buscar informações de uma criptomoeda");
            System.out.println("2. Sair");

            int escolha = leitura.nextInt();

            switch (escolha) {
                case 1:
                    System.out.print("Digite a sigla da criptomoeda (por exemplo, BTC): ");
                    leitura.nextLine(); // Limpa o buffer
                    String sigla = leitura.nextLine();
                    String urlDaCriptomoeda = URL_BASE + sigla + "/ticker";
                    try {
                        String json = consumo.obterDados(urlDaCriptomoeda);
                        if (json != null) {
                            System.out.println("Informações da criptomoeda " + sigla + ":");
                            System.out.println(json);
                        } else {
                            System.out.println("Erro ao buscar informações da criptomoeda " + sigla);
                        }
                    }catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao buscar informações da criptomoeda " + sigla);
                    }

                    break;
                case 2:
                    continuar = false;
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


    }


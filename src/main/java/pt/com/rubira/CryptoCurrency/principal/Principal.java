package pt.com.rubira.CryptoCurrency.principal;

import pt.com.rubira.CryptoCurrency.model.Dados;
import pt.com.rubira.CryptoCurrency.service.ConsumoApi;
import pt.com.rubira.CryptoCurrency.service.ConverteDados;

import java.io.IOException;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();


    private final String URL_BASE = "https://www.mercadobitcoin.net/api/";

    public void ExibeMenu() throws IOException {
        String json = null;
        boolean continuar = true;

        while (continuar) {
            System.out.println(" *** OPÇÕES CRYPTO ***");
            System.out.println("1. Lista de Cryptomoedas");
            System.out.println("2. Digite 2 e em seguida a criptomoeda, para informações detalhadas");
            System.out.println("3. Sair");

            int escolha = leitura.nextInt();

            switch (escolha) {
                case 1:
                    String urlCriptoMoedas = URL_BASE + "coins";
                    try {
                        json = consumo.obterDados(urlCriptoMoedas);
                        if (json != null) {
                            System.out.print("Listando moedas Disponiveis: ");
                            var coins = conversor.obterLista(json, Dados.class);
                            coins.forEach(coin -> System.out.println(coin.getCoins()));

                        } else {
                            System.out.println("Erro ao buscar moedas disponiveis.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao buscar moedas disponiveis.");
                    }
                    break;


                case 2:
                    leitura.nextLine(); // Limpa o buffer
                    String coins = leitura.nextLine();
                    String urlDaCriptomoeda = URL_BASE + coins + "/ticker";
                    try {
                        json = consumo.obterDados(urlDaCriptomoeda);
                        if (json != null) {
                            System.out.println("Informações da criptomoeda " + coins + ":");
                            System.out.println(json);
                        } else {
                            System.out.println("Erro ao buscar informações da criptomoeda " + coins);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Erro ao buscar informações da criptomoeda " + coins + ": está sigla nao consta em nossos registros.");
                    }
                    break;


                case 3:
                    continuar = false;
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");



            }
        }



    }
}


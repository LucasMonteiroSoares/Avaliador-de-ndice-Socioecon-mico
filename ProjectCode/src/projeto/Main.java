//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BST bst = new BST();
        AVL avl = new AVL();
        ComparacaoBSTvsAVL comparacao = new ComparacaoBSTvsAVL();
        int codigo;
        String municipio;

        // Carregamento inicial dos dados das escolas a partir do arquivo CSV
        ListaEscolas escolas = CarregadorDados.carregarDados("C:\\Users\\tomyb\\OneDrive\\Documentos\\projeto\\src\\projeto\\INSE_2019_ESCOLAS.csv"); // Caminho do arquivo 

        for (int i = 0; i < escolas.tamanho(); i++) {
            bst.inserir(escolas.obter(i));
        }

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMENU");
            System.out.println("1. Buscar escola pelo código");
            System.out.println("2. Inserir novos dados de escola");
            System.out.println("3. Remover dados de escola");
            System.out.println("4. Listar escolas de um município (ordenadas por nível socioeconômico)");
            System.out.println("5. Mostrar as 5 escolas com melhor nível socioeconômico");
            System.out.println("6. Mostrar as 5 escolas com pior nível socioeconômico");
            System.out.println("7. Calcular a média do nível socioeconômico em um município");
            System.out.println("8. Listar escolas em ordem crescente de nível socioeconômico");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o código da escola: ");
                    codigo = scanner.nextInt();
                    Escola escola = bst.buscarPorCodigo(codigo); // Busca na BST
                    if (escola != null) {
                        System.out.println("Escola encontrada: " + escola);
                        comparacao.compararBusca(codigo, bst, avl, escolas); // Comparar busca entre BST e AVL
                    } else {
                        System.out.println("Escola não encontrada.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o código da escola: ");
                    codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o nome da escola: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o DIR da escola: ");
                    String dir = scanner.nextLine();
                    System.out.print("Digite o município da escola: ");
                    municipio = scanner.nextLine();
                    System.out.print("Digite o nível socioeconômico: ");
                    float nivelSocio = scanner.nextFloat();

                    // Inserção da nova escola
                    Escola novaEscola = new Escola(codigo, nome, dir, municipio, nivelSocio);
                    bst.inserir(novaEscola);
                    comparacao.compararInsercao(novaEscola, bst, avl, escolas);

                    System.out.println("Escola inserida com sucesso!");
                    break;

                case 3: // Entrada com o identificador da escola para sua remoção
                    System.out.print("Digite o código da escola para remoção: ");
                    codigo = scanner.nextInt();
                    bst.remover(codigo); // Remoção na BST
                    System.out.println("Escola removida.");
                    comparacao.compararRemocao(codigo, bst, avl, escolas); 
                    break;

                case 4: // Listar escolas de um município em ordem de nível socioeconômico
                    System.out.print("Digite o município: ");
                    municipio = scanner.nextLine();
                    ListaEscolas listaMunicipio = bst.listarPorMunicipio(municipio); // Listar escolas na BST
                    listaMunicipio.ordenarPorNivelSocioeconomico(); // Ordena a lista
                    for (int i = 0; i < listaMunicipio.tamanho(); i++) {
                        System.out.println(listaMunicipio.obter(i));
                    }
                    break;

                case 5: // Listar as 5 escolas com melhor nivel socioeconômico
                    ListaEscolas listaMenor = bst.listarEmOrdem();
                    listaMenor.ordenarPorNivelSocioeconomico();
                    System.out.println("5 escolas com melhor nível socioeconômico:");
                    for (int i = 0; i < Math.min(5, listaMenor.tamanho()); i++) {
                        System.out.println(listaMenor.obter(i));
                    }
                    break;

                case 6: // Listar as 5 escolas com pior nivel socioeconômico
                    ListaEscolas listaMaior = bst.listarEmOrdem();
                    listaMaior.ordenarPorNivelSocioeconomico();
                    System.out.println("5 escolas com pior nível socioeconômico:");
                    for (int i = listaMaior.tamanho() - 1; i >= Math.max(0, listaMaior.tamanho() - 5); i--) {
                        System.out.println(listaMaior.obter(i));
                    }
                    break;

                case 7: // Calcular média do nível socioeconômico de um município
                    System.out.print("Digite o município: ");
                    municipio = scanner.nextLine();
                    float media = bst.calcularMediaMunicipio(municipio);
                    if (media == -1) {
                        System.out.println("Nenhuma escola encontrada no município especificado.");
                    } else {
                        System.out.printf("Média do nível socioeconômico em %s: %.2f\n", municipio, media);
                    }
                    break;

                case 8: // Listar escolas em ordem crescente de nível socioeconômico
                    ListaEscolas listaOrdenada = bst.listarEmOrdem();
                    listaOrdenada.ordenarPorNivelSocioeconomico();
                    for (int i = 0; i < listaOrdenada.tamanho(); i++) {
                        System.out.println(listaOrdenada.obter(i));
                    }
                    break;

                case 9: // Sair
                    System.out.println("Saindo...");
                    break;

                default: // Opção inválida
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 9);

        scanner.close();
    }
}

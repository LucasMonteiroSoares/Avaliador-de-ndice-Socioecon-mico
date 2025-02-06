//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

public class ComparacaoBSTvsAVL {
    public void compararInsercao(Escola novaEscola, BST bst, AVL avl,ListaEscolas escolas){
        // Cálculo do tempo para inserção usando BST
        long inicioBST = System.nanoTime();
        for (int i = 0; i < escolas.tamanho(); i++) {
            bst.inserir(escolas.obter(i)); // Reinserção para medição
        }
        bst.inserir(novaEscola);
        long fimBST = System.nanoTime();
        double tempoInsercaoBST = (fimBST - inicioBST) / 1e6; // Converter para milissegundos
        System.out.printf("Tempo de inserção na BST: %.2f microsegundos%n", tempoInsercaoBST);

        // Cálculo do tempo para inserção usando AVL
        long inicioAVL = System.nanoTime();
        for (int i = 0; i < escolas.tamanho(); i++) {
            avl.inserir(escolas.obter(i));
        }
        avl.inserir(novaEscola);
        long fimAVL = System.nanoTime();
        double tempoInsercaoAVL = (fimAVL - inicioAVL) / 1e6;
        System.out.printf("Tempo de inserção na AVL: %.2f microsegundos%n", tempoInsercaoAVL);
    }
    public void compararBusca(int codigo, BST bst, AVL avl,ListaEscolas escolas){
        // Cálculo do tempo para busca usando BST
        long inicioBST = System.nanoTime();
        Escola resultadoBST = bst.buscarPorCodigo(codigo);
        long fimBST = System.nanoTime();
        double tempoBuscaBST = (fimBST - inicioBST) / 1e3;
        System.out.printf("Tempo de busca na BST: %.2f microsegundos%n", tempoBuscaBST);

        // Cálculo do tempo para busca usando AVL
        long inicioAVL = System.nanoTime();
        Escola resultadoAVL = avl.buscarPorCodigo(codigo);
        long fimAVL = System.nanoTime();
        double tempoBuscaAVL = (fimAVL - inicioAVL) / 1e3;
        System.out.printf("Tempo de busca na AVL: %.2f microsegundos%n", tempoBuscaAVL);
    }
    public void compararRemocao(int codigo, BST bst, AVL avl, ListaEscolas escolas){
        // Cálculo do tempo para remoção usando BST
        long inicioBST = System.nanoTime();
        bst.remover(codigo);
        long fimBST = System.nanoTime();
        double tempoRemocaoBST = (fimBST - inicioBST) / 1e3;
        System.out.printf("Tempo de remoção no BST: %.2f microsegundos%n", tempoRemocaoBST);

        // Cálculo do tempo para remoção usando AVL
        long inicioAVL = System.nanoTime();
        avl.remover(codigo);
        long fimAVL = System.nanoTime();
        double tempoRemocaoAVL = (fimAVL - inicioAVL) / 1e3;
        System.out.printf("Tempo de remoção na AVL: %.2f microsegundos%n", tempoRemocaoAVL);
    }
}
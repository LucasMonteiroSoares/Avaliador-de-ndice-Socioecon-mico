//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

public class BST {
    private No raiz;

    public Escola buscarPorCodigo(int codigo) {
        return buscarPorCodigoRecursao(raiz, codigo);
    }

    private Escola buscarPorCodigoRecursao(No raiz, int codigo) {
        if (raiz == null) return null;
        if (raiz.escola.codigo == codigo) return raiz.escola;
        if (codigo < raiz.escola.codigo) return buscarPorCodigoRecursao(raiz.esquerda, codigo);
        return buscarPorCodigoRecursao(raiz.direita, codigo);
    }

    public void inserir(Escola escola) {
        raiz = inserirRecursao(raiz, escola);
    }
    

    private No inserirRecursao(No no, Escola escola) {
        if (no == null) {
            return new No(escola);
        }
        if (escola.codigo < no.escola.codigo) {
            no.esquerda = inserirRecursao(no.esquerda, escola);
        } else if (escola.codigo > no.escola.codigo) {
            no.direita = inserirRecursao(no.direita, escola);
        }
        return no;
    }

    public void remover(int codigo) {
        raiz = removerRecusao(raiz, codigo);
    }

    private No removerRecusao(No raiz, int codigo) {
        if (raiz == null) return null;

        // Busca pelo nó a ser removido
        if (codigo < raiz.escola.codigo) {
            raiz.esquerda = removerRecusao(raiz.esquerda, codigo);
        } else if (codigo > raiz.escola.codigo) {
            raiz.direita = removerRecusao(raiz.direita, codigo);
        } else {
            if (raiz.esquerda == null) return raiz.direita;
            if (raiz.direita == null) return raiz.esquerda;
            // Para o caso de dois filhos
            No temp = getMenorNo(raiz.direita);
            raiz.escola = temp.escola;
            raiz.direita = removerRecusao(raiz.direita, temp.escola.codigo);
        }

        return raiz;
    }

    private No getMenorNo(No no) {
        No atual = no;
        while (atual != null && atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    // Listar escolas em ordem crescente
    public ListaEscolas listarEmOrdem() {
        ListaEscolas lista = new ListaEscolas();
        listarEmOrdemRecursao(raiz, lista);
        return lista;
    }

    private void listarEmOrdemRecursao(No no, ListaEscolas lista) {
        if (no != null) {
            listarEmOrdemRecursao(no.esquerda, lista);
            lista.adicionar(no.escola);
            listarEmOrdemRecursao(no.direita, lista);
        }
    }

    // Listar escolas de um município específico
    public ListaEscolas listarPorMunicipio(String municipio) {
        ListaEscolas lista = new ListaEscolas();
        listarPorMunicipioRecursao(raiz, municipio, lista);
        return lista;
    }

    private void listarPorMunicipioRecursao(No no, String municipio, ListaEscolas lista) {
        if (no != null) {
            listarPorMunicipioRecursao(no.esquerda, municipio, lista);
            if (no.escola.municipio.equalsIgnoreCase(municipio)) {
                lista.adicionar(no.escola);
            }
            listarPorMunicipioRecursao(no.direita, municipio, lista);
        }
    }

    // Cálculo da média do nível socioeconômico em um município
    public float calcularMediaMunicipio(String municipio) {
        ListaEscolas lista = listarPorMunicipio(municipio);
        if (lista.tamanho() == 0) return -1;

        float soma = 0;
        for (int i = 0; i < lista.tamanho(); i++) {
            soma += lista.obter(i).nivelSocioeconomico;
        }
        return soma / lista.tamanho();
    }
}
//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

public class AVL {
    private NoAVL raiz;

    private class NoAVL {
        Escola escola;
        NoAVL esquerda, direita;
        int altura;

        public NoAVL(Escola escola) {
            this.escola = escola;
            this.altura = 1;
        }
    }

    public void inserir(Escola escola) {
        raiz = inserirRecursao(raiz, escola);
    }

    private NoAVL inserirRecursao(NoAVL no, Escola escola) {
        if (no == null) {
            return new NoAVL(escola);
        }
        if (escola.codigo < no.escola.codigo) {
            no.esquerda = inserirRecursao(no.esquerda, escola);
        } else if (escola.codigo > no.escola.codigo) {
            no.direita = inserirRecursao(no.direita, escola);
        } else {
            return no; 
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita)); // Atualiza altura do nó

        return balancear(no, escola.codigo); // Faz o balanceamento, se necessário
    }

    public void remover(int codigo) {
        raiz = removerRecusao(raiz, codigo);
    }

    private NoAVL removerRecusao(NoAVL no, int codigo) {
        if (no == null) return null;

        // Busca pelo nó a ser removido
        if (codigo < no.escola.codigo) {
            no.esquerda = removerRecusao(no.esquerda, codigo);
        } else if (codigo > no.escola.codigo) {
            no.direita = removerRecusao(no.direita, codigo);
        } else {
            if (no.esquerda == null || no.direita == null) {
                NoAVL temp = (no.esquerda != null) ? no.esquerda : no.direita;
                if (temp == null) {
                    no = null;
                } else {
                    no = temp;
                }
            } else { // Para o caso de dois filhos
                NoAVL temp = getMenorNo(no.direita);
                no.escola = temp.escola;
                no.direita = removerRecusao(no.direita, temp.escola.codigo);
            }
        }

        if (no == null) {
            return null;
        }

        no.altura = 1 + Math.max(altura(no.esquerda), altura(no.direita)); // Atualiza altura do nó

        return balancear(no, codigo); // Faz o balanceamento, se necessário
    }

    private NoAVL getMenorNo(NoAVL no) {
        NoAVL atual = no;
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }

    private NoAVL balancear(NoAVL no, int chave) {
        int balance = fatorBalanceamento(no);

        if (balance > 1 && chave < no.esquerda.escola.codigo) {
            return rotacaoDireita(no);
        }

        if (balance < -1 && chave > no.direita.escola.codigo) {
            return rotacaoEsquerda(no);
        }

        if (balance > 1 && chave > no.esquerda.escola.codigo) {
            no.esquerda = rotacaoEsquerda(no.esquerda);
            return rotacaoDireita(no); // Rotação dupla à direita
        }

        if (balance < -1 && chave < no.direita.escola.codigo) {
            no.direita = rotacaoDireita(no.direita);
            return rotacaoEsquerda(no); // Rotação dupla à esquerda
        }

        return no;
    }

    private int altura(NoAVL no) { // retorna a altura do nó
        return (no == null) ? 0 : no.altura;
    }

    private int fatorBalanceamento(NoAVL no) {
        return (no == null) ? 0 : altura(no.esquerda) - altura(no.direita);
    }

    private NoAVL rotacaoDireita(NoAVL y) {
        NoAVL x = y.esquerda;
        NoAVL T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;
        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;

        return x;
    }

    private NoAVL rotacaoEsquerda(NoAVL x) {
        NoAVL y = x.direita;
        NoAVL T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = Math.max(altura(x.esquerda), altura(x.direita)) + 1;
        y.altura = Math.max(altura(y.esquerda), altura(y.direita)) + 1;

        return y;
    }

    public Escola buscarPorCodigo(int codigo) {
        return buscarPorCodigoRecursao(raiz, codigo);
    }

    private Escola buscarPorCodigoRecursao(NoAVL no, int codigo) {
        if (no == null) return null;
        if (codigo == no.escola.codigo) return no.escola;
        if (codigo < no.escola.codigo) return buscarPorCodigoRecursao(no.esquerda, codigo);
        return buscarPorCodigoRecursao(no.direita, codigo);
    }
}

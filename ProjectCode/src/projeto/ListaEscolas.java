//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

public class ListaEscolas {
    private Escola[] escolas;
    private int tamanho;

    public ListaEscolas() {
        escolas = new Escola[100];
        tamanho = 0;
    }

    public void adicionar(Escola escola) { // Função para adicionar uma nova escola na lista
        if (tamanho == escolas.length) {
            redimensionar();
        }
        escolas[tamanho++] = escola;
    }

    public Escola obter(int indice) { // Função para buscar
        if (indice < 0 || indice >= tamanho) {
            return null;
        }
        return escolas[indice];
    }

    public int tamanho() {
        return tamanho;
    }

    private void redimensionar() { // Redimensiona a lista
        Escola[] novaLista = new Escola[escolas.length * 2];
        for (int i = 0; i < escolas.length; i++) {
            novaLista[i] = escolas[i];
        }
        escolas = novaLista;
    }

    public void ordenarPorNivelSocioeconomico() { // Ordenação pelo nível
        for (int i = 0; i < tamanho - 1; i++) {
            for (int j = 0; j < tamanho - i - 1; j++) {
                if (escolas[j].nivelSocioeconomico > escolas[j + 1].nivelSocioeconomico) {
                    Escola temp = escolas[j];
                    escolas[j] = escolas[j + 1];
                    escolas[j + 1] = temp;
                }
            }
        }
    }
}

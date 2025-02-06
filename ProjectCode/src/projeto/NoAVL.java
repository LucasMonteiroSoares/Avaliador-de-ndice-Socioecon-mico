//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

public class NoAVL {
    int chave;
    String dados;
    NoAVL esquerda, direita;
    int altura;

    public NoAVL(int chave, String dados) {
        this.chave = chave;
        this.dados = dados;
        this.altura = 1;
    }
}

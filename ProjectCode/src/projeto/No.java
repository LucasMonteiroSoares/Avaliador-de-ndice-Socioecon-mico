//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

public class No {
    Escola escola;
    No esquerda, direita;

    public No(Escola escola) {
        this.escola = escola;
        this.esquerda = this.direita = null;
    }
}
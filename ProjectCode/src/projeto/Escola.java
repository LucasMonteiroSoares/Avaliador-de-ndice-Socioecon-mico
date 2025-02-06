//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

public class Escola {
    int codigo;
    String nome;
    String dir;
    String municipio;
    float nivelSocioeconomico;

    public Escola(int codigo, String nome, String dir, String municipio, float nivelSocioeconomico) {
        this.codigo = codigo;
        this.nome = nome;
        this.dir = dir;
        this.municipio = municipio;
        this.nivelSocioeconomico = nivelSocioeconomico;
    }

    @Override
    public String toString() {
        return "Código: " + codigo +
                ", Nome: " + nome +
                ", DIR: " + dir +
                ", Município: " + municipio +
                ", Nível Socioeconômico: " + nivelSocioeconomico;
    }
}

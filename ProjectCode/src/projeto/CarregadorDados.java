//Fernando Cavaleiro Paiva - 10416680
//Lucas Monteiro Soares - 10417881
//Matteo Porcare - 10418276
//Tomy Goldberg Boimel - 10417109
package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CarregadorDados { 
    public static ListaEscolas carregarDados(String caminhoArquivo) { //Função para leitura do arquivo
        ListaEscolas lista = new ListaEscolas();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            br.readLine();
            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(";");
                int codigo = Integer.parseInt(valores[0]);
                String nome = valores[1];
                String dir = valores[2];
                String municipio = valores[3];
                float nivelSocio = Float.parseFloat(valores[4].replace(",", "."));

                Escola escola = new Escola(codigo, nome, dir, municipio, nivelSocio);
                lista.adicionar(escola);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return lista;
    }
}

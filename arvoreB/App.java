package arvoreB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        
        List<Integer> numeros = new ArrayList<>();
        String input = "/Users/igors/OneDrive/Documentos/input.txt";
        String out = "/Users/igors/OneDrive/Documentos/output.txt";


        try (BufferedReader bReader = new BufferedReader(new FileReader(input))) {
            String linha;

            while ((linha = bReader.readLine()) != null) {
                numeros.add(Integer.parseInt(linha));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        Arvore arvore = new Arvore();
        for (int numero : numeros) {
            arvore.inserir(numero);
        }

        List<Integer> elementos = arvore.coletarElementos();
        int ordem = arvore.getOrdem();
        int nivel = arvore.calcularNivel();

        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(out))) {
            bWriter.write("Ordem: " + ordem);
            bWriter.newLine();
            bWriter.write("Nível: " + nivel);
            bWriter.newLine();
            bWriter.write("Elementos: ");
            for (int i = 0; i < elementos.size(); i++) {
                if (i > 0) {
                    bWriter.write(" - ");
                }
                bWriter.write(String.valueOf(elementos.get(i)));
            }
            bWriter.newLine();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

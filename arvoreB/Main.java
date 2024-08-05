package arvoreB;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            String folderPath = "arvoreB";
            File inputFile = new File(folderPath, "entrada.txt");
            File outputFile = new File(folderPath, "saida.txt");

            if (!inputFile.exists()) {
                System.err.println("Erro: arquivo 'entrada.txt' não encontrado no diretório 'arvoreB'.");
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

            int t = Integer.parseInt(reader.readLine().trim());
            BTree bTree = new BTree(t);

            String line;
            while ((line = reader.readLine()) != null) {
                int num = Integer.parseInt(line.trim());
                bTree.inserir(num);
            }

            reader.close();

            if (bTree.raiz.n == 0) {
                writer.println("Arvore B vazia");
            } else {
                writer.println("Ordem: " + t + " & Niveis: " + bTree.encontrarNumeroDeNiveis(bTree.raiz));
            }

            bTree.imprimirNiveis(writer);

            writer.close();
        } catch (IOException e) {
            System.err.println("Erro ao abrir arquivos: " + e.getMessage());
        }
    }
}


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
        String input = "/home/jorge/Documentos/input.txt";
        String out = "/home/jorge/Documentos/output.txt";

        try{
            BufferedReader bReader = new BufferedReader(new FileReader(input));
            String linha;

            while((linha = bReader.readLine()) != null){
                numeros.add(Integer.parseInt(linha));
            }
            bReader.close();
        }catch(Exception e){
            System.out.println(e);
        }

        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(out));
            bWriter.write("Total de numeros: " + numeros.size());

            bWriter.newLine();

            for(int i = 0; i < numeros.size(); i++){
                if(i > 0 && i % 10 == 0){
                    bWriter.newLine();
                }else if(i > 0){
                    bWriter.write(" - ");
                }

                bWriter.write(String.valueOf(numeros.get(i)));
            }

            bWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

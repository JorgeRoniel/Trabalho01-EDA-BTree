package arvoreB;

public class App {
    public static void main(String[] args) {
        Arvore arvore = new Arvore(2);

        int[] nums = {10, 100, 34, 19, 55, 78, 66, 95};

        for(int chave : nums){
            arvore.inserir(chave);
        }

        System.out.println(arvore);
    }
}

package arvoreB;

import java.util.Vector;

public class Pagina {
    private int n;
    private Vector<Integer> chaves;
    private Vector<Pagina> filhos;
    private Boolean folha;
    private int larguraFilho;


    public Pagina(Boolean folha){
        this.folha = folha;
    }

    public Pagina(int n){
        this.chaves = new Vector<Integer>(n - 1);
        for(int i = 0; i < n - 1;i++){
            this.chaves.add(null);
        }

        this.filhos = new Vector<Pagina>(n);
        for(int x = 0; x < n;x++){
            this.filhos.add(null);
        }

        this.folha = true;
        this.n = 0;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Vector<Integer> getChaves() {
        return chaves;
    }

    public void setChaves(Vector<Integer> chaves) {
        this.chaves = chaves;
    }

    public Vector<Pagina> getFilhos() {
        return filhos;
    }

    public void setFilhos(Vector<Pagina> filhos) {
        this.filhos = filhos;
    }

    public Boolean getFolha() {
        return folha;
    }

    public void setFolha(Boolean folha) {
        this.folha = folha;
    }

    public int getLarguraFilho() {
        return larguraFilho;
    }

    public void setLarguraFilho(int larguraFilho) {
        this.larguraFilho = larguraFilho;
    }

    @Override
    public String toString() {
        return "Pagina [chaves=" + chaves + ", filhos=" + filhos + "]";
    }

    
}

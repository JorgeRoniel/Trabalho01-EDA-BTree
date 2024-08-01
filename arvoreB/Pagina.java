package arvoreB;

import java.util.ArrayList;
import java.util.List;

public class Pagina {
    private int n;
    private List<Integer> chaves = new ArrayList<>();
    private List<Pagina> filhos = new ArrayList<>();
    private Boolean folha;

    
    public Pagina(Boolean folha) {
        this.folha = folha;
    }


    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }
    public List<Integer> getChaves() {
        return chaves;
    }
    public void setChaves(List<Integer> chaves) {
        this.chaves = chaves;
    }
    public List<Pagina> getFilhos() {
        return filhos;
    }
    public void setFilhos(List<Pagina> filhos) {
        this.filhos = filhos;
    }
    public Boolean IsFolha() {
        return folha;
    }
    public void setFolha(Boolean folha) {
        this.folha = folha;
    }


    @Override
    public String toString() {
        return "Pagina [chaves=" + chaves + ", filhos=" + filhos + "]";
    }

    
}

package arvoreB;

public class Arvore {
    private int ordem;
    private Pagina raiz;
    private int num_elementos;
    
    public int getOrdem() {
        return ordem;
    }
    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
    public Pagina getRaiz() {
        return raiz;
    }
    public void setRaiz(Pagina raiz) {
        this.raiz = raiz;
    }
    public int getNum_elementos() {
        return num_elementos;
    }
    public void setNum_elementos(int num_elementos) {
        this.num_elementos = num_elementos;
    }

    public Arvore(int n){
        this.raiz = new Pagina(n);
        this.ordem = n;
        this.num_elementos = 0;
    }

    public void inserir(int k){
        if(buscaChave(raiz, k) == null){
            if(raiz.getN() == 0){
                raiz.getChaves().set(0, k);
                raiz.setN(raiz.getN()+1);
            }else{
                Pagina r = raiz;
                if(r.getN() == ordem - 1){
                    Pagina pag = new Pagina(ordem);
                    raiz = pag;
                    pag.setFolha(false);
                    pag.setN(0);
                    pag.getFilhos().set(0, r);
                    dividePag(pag, 0, r);
                    inserePagNaoCheio(pag, k);
                }else{
                    inserePagNaoCheio(r, k);
                }
            }
            num_elementos++;
        }
    }

    public Pagina buscaChave(Pagina pag, int k){
        int i = 1;
        while((i <= pag.getN()) && (k > pag.getChaves().get(i - 1))){
            i++;
        }

        if((i <= pag.getN()) && (k == pag.getChaves().get(i-1))){
            return pag;
        }

        if(pag.getFolha()){
            return null;
        }else{
            return(buscaChave(pag.getFilhos().get(i-1), k));
        }
    }

    public void dividePag(Pagina pai, int i, Pagina filho){
        int metade = (int) Math.floor((ordem-1)/2);

        Pagina aux = new Pagina(ordem);
        aux.setFolha(filho.getFolha());
        aux.setN(metade);

        for(int j = 0; j < metade+1; j++){
            if((ordem-1) % 2 == 0){
                aux.getChaves().set(j, filho.getChaves().get(j + metade));
            }else{
                aux.getChaves().set(j, filho.getChaves().get(j+metade+1));
            }
            filho.setN(filho.getN()-1);
        }

        if(!filho.getFolha()){
            for(int j = 0; j < metade+1;j++){
                if((ordem-1) % 2 == 0){
                    aux.getFilhos().set(j, filho.getFilhos().get(j + metade));
                }else{
                    aux.getFilhos().set(j, filho.getFilhos().get(j + metade + 1));
                }
            }
        }

        filho.setN(metade);

        for(int j = pai.getN(); j > i; j--){
            pai.getFilhos().set(j+1, pai.getFilhos().get(j));
        }

        pai.getFilhos().set(i+1, aux);

        for(int j = pai.getN(); j > i; j--){
            pai.getChaves().set(j, pai.getChaves().get(j-1));
        }

        if((ordem - 1) % 2 == 0){
            pai.getChaves().set(i, filho.getChaves().get(metade-1));
            filho.setN(filho.getN()-1);
        }else{
            pai.getChaves().set(i, filho.getChaves().get(metade));
        }

        pai.setN(pai.getN()+1);
    }

    public void inserePagNaoCheio(Pagina pag, int x){
        int i = pag.getN() - 1;

        if(pag.getFolha()){
            while(i >= 0 && x < pag.getChaves().get(i)){
                pag.getChaves().set(i + 1, pag.getChaves().get(i));
                i--;
            }
            i++;
            pag.getChaves().set(i, x);
            pag.setN(pag.getN() + 1);
        }else{
            while(i >= 0 && x < pag.getChaves().get(i)){
                i--;
            }

            i++;
            if((pag.getFilhos().get(i)).getN() == ordem - 1){
                dividePag(pag, i, pag.getFilhos().get(i));
                if(x > pag.getChaves().get(i)){
                    i++;
                }
            }

            inserePagNaoCheio(pag.getFilhos().get(i), x);
        }
    }
    @Override
    public String toString() {
        return "Arvore [raiz=" + raiz + "]";
    }

    
}

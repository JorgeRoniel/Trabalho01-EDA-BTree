package arvoreB;

import java.util.ArrayList;
import java.util.List;

public class Arvore {
    private static final int ordem = 3;
    private Pagina raiz;

    public Arvore() {
        raiz = new Pagina(true);
    }

    public void inserir(int valor) {
        Pagina r = raiz;
        if (r.getN() == 2 * ordem - 1) {
            Pagina aux = new Pagina(false);
            raiz = aux;
            aux.getFilhos().add(r);
            dividePag(aux, 0, r);
            inserirEmNoNaoCheio(aux, valor);
        } else {
            inserirEmNoNaoCheio(r, valor);
        }
    }

    private void dividePag(Pagina pai, int i, Pagina filho) {
        Pagina aux = new Pagina(filho.IsFolha());
        aux.setN(ordem - 1);

        for (int j = 0; j < ordem - 1; j++) {
            aux.getChaves().add(filho.getChaves().get(ordem));
        }

        if (!filho.IsFolha()) {
            for (int j = 0; j < ordem; j++) {
                aux.getFilhos().add(filho.getFilhos().get(ordem));
            }
        }

        filho.setN(ordem - 1);

        pai.getFilhos().add(i + 1, aux);
        pai.getChaves().add(i, filho.getChaves().get(ordem - 1));
        pai.setN(pai.getN() + 1);
    }

    private void inserirEmNoNaoCheio(Pagina pag, int valor) {
        int i = pag.getN() - 1;

        if (pag.IsFolha()) {
            pag.getChaves().add(0);
            while (i >= 0 && valor < pag.getChaves().get(i)) {
                pag.getChaves().set(i + 1, pag.getChaves().get(i));
                i--;
            }

            pag.getChaves().set(i + 1, valor);
            pag.setN(pag.getN() + 1);
        } else {
            while (i >= 0 && valor < pag.getChaves().get(i)) i--;

            i++;
            if (pag.getFilhos().get(i).getN() == 2 * ordem - 1) {
                dividePag(pag, i, pag.getFilhos().get(i));
                if (valor > pag.getChaves().get(i)) i++;
            }
            inserirEmNoNaoCheio(pag.getFilhos().get(i), valor);
        }
    }

    public int getOrdem() {
        return ordem;
    }

    public int calcularNivel() {
        return raiz.calcularNivel(0);
    }

    public List<Integer> coletarElementos() {
        List<Integer> elementos = new ArrayList<>();
        raiz.coletarElementos(elementos);
        elementos.sort(Integer::compareTo);
        return elementos;
    }

    @Override
    public String toString() {
        return "Arvore [raiz=" + raiz + "]";
    }
}

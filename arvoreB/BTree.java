package arvoreB;
import java.io.*;
import java.util.ArrayList;

class BTreeNode {
    int[] chaves;
    BTreeNode[] filhos;
    int n;
    boolean folha;

    public BTreeNode(int t, boolean folha) {
        this.chaves = new int[2 * t + 1];
        this.filhos = new BTreeNode[2 * t + 2];
        this.n = 0;
        this.folha = folha;
    }
}

class BTree {
    BTreeNode raiz;
    int t;

    public BTree(int t) {
        this.raiz = new BTreeNode(t, true);
        this.t = t;
    }

    public void inserir(int k) {
        BTreeNode r = raiz;
        if (r.n == 2 * t + 1) {
            BTreeNode s = new BTreeNode(t, false);
            raiz = s;
            s.filhos[0] = r;
            dividirFilho(s, 0, r);
            inserirNaoCheio(s, k);
        } else {
            inserirNaoCheio(r, k);
        }
    }

    private void inserirNaoCheio(BTreeNode x, int k) {
        int i = x.n - 1;
        if (x.folha) {
            while (i >= 0 && x.chaves[i] > k) {
                x.chaves[i + 1] = x.chaves[i];
                i--;
            }
            x.chaves[i + 1] = k;
            x.n += 1;
        } else {
            while (i >= 0 && x.chaves[i] > k) {
                i--;
            }
            i++;
            if (x.filhos[i].n == 2 * t + 1) {
                dividirFilho(x, i, x.filhos[i]);
                if (x.chaves[i] < k) {
                    i++;
                }
            }
            inserirNaoCheio(x.filhos[i], k);
        }
    }

    private void dividirFilho(BTreeNode x, int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(t, y.folha);
        z.n = t;
        for (int j = 0; j < t; j++) {
            z.chaves[j] = y.chaves[j + t + 1];
        }
        if (!y.folha) {
            for (int j = 0; j <= t; j++) {
                z.filhos[j] = y.filhos[j + t + 1];
            }
        }
        y.n = t;
        for (int j = x.n; j >= i + 1; j--) {
            x.filhos[j + 1] = x.filhos[j];
        }
        x.filhos[i + 1] = z;
        for (int j = x.n - 1; j >= i; j--) {
            x.chaves[j + 1] = x.chaves[j];
        }
        x.chaves[i] = y.chaves[t];
        x.n += 1;
    }

    public void imprimirNiveis(PrintWriter writer) {
        ArrayList<BTreeNode> fila = new ArrayList<>();
        ArrayList<Integer> niveis = new ArrayList<>();
        fila.add(raiz);
        niveis.add(1);
        int nivelAtual = 1;
        writer.print("Nivel " + nivelAtual + ": ");

        boolean primeiroElemento = true;

        while (!fila.isEmpty()) {
            BTreeNode no = fila.remove(0);
            int nivelNo = niveis.remove(0);

            if (nivelNo > nivelAtual) {
                writer.println();
                nivelAtual = nivelNo;
                writer.print("Nivel " + nivelAtual + ": ");
                primeiroElemento = true;
            } else if (!primeiroElemento) {
                writer.print(" - ");
            }

            for (int i = 0; i < no.n; i++) {
                if (!primeiroElemento) {
                    writer.print(" ");
                }
                writer.print(no.chaves[i]);
                primeiroElemento = false;
            }

            if (!no.folha) {
                for (int i = 0; i <= no.n; i++) {
                    fila.add(no.filhos[i]);
                    niveis.add(nivelNo + 1);
                }
            }
        }
        writer.println();
    }

    public int encontrarNumeroDeNiveis(BTreeNode no) {
        if (no.folha) {
            return 1;
        } else {
            return 1 + encontrarNumeroDeNiveis(no.filhos[0]);
        }
    }
}

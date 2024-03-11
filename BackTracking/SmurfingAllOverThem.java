
import java.util.Scanner;

//1-Preto
//2-Branco
public class SmurfingAllOverThem {

    private int encontrados;
    private int lado;
    private int[] pretosLinha;
    private int[] pretosColuna;
    private int[] brancosLinha;
    private int[] brancosColuna;
    private int[] tranzLinha;
    private int[] tranzColuna;
    private int[] pretosQuadrante;
    private int[] brancosQuadrante;
    private int pretosDiagonal1;
    private int brancosDiagonal1;
    private int pretosDiagonal2;
    private int brancosDiagonal2;
    private int[][] codigo;
    private int[][] hitCodigo;
    private int chamadas;

    public static void main(String[] args) {
        SmurfingAllOverThem saot = new SmurfingAllOverThem();
        Scanner stdin = new Scanner(System.in);
        int ciclos = stdin.nextInt();
        saot.chamadas = 0;
        for (int i = 0; i < ciclos; i++) {
            saot.leInputs(stdin);
            if (saot.verDefeitos()) {
                saot.preProcessamento(0, 0);
                if (!saot.defeitos2()) {
                    saot.colocaquad(0, 0, 2);//chamar com inicio branco
                    saot.colocaquad(0, 0, 1);//chamar com inicio preto
                }
            }
            saot.concluir();
        }
    }

    public boolean defeitos2() {
        if (pretosDiagonal1 < 0 || pretosDiagonal2 < 0 || brancosDiagonal1 < 0 || brancosDiagonal2 < 0) {
            return true;
        }
        if (pretosQuadrante[0] < 0 || pretosQuadrante[1] < 0 || pretosQuadrante[2] < 0 || pretosQuadrante[3] < 0) {
            return true;
        }
        if (brancosQuadrante[0] < 0 || brancosQuadrante[1] < 0 || brancosQuadrante[2] < 0 || brancosQuadrante[3] < 0) {
            return true;
        }
        return false;
    }

    public void preProcessamento(int linhasProcessadas, int colunasProcessadas) {
        boolean progresso = false;
        int splitPoint = Math.floorDiv(lado, 2);
        //ver as linhas
        for (int i = 0; i < lado; i++) {
            if (brancosLinha[i] == 0 && pretosLinha[i] > 0) {//apenas falta colocar pretos
                pretosLinha[i] = 0;
                for (int j = 0; j < lado; j++) {
                    if (codigo[i][j] == 0) {//celula sem info
                        codigo[i][j] = 1;//pinta preto
                        pretosColuna[j]--;
                        if (i < splitPoint && j < splitPoint) {//quadrante 2
                            pretosQuadrante[1]--;
                        }
                        if (i < splitPoint && j >= splitPoint) {//quadrante 1
                            pretosQuadrante[0]--;
                        }
                        if (i >= splitPoint && j < splitPoint) {//quadrante 3
                            pretosQuadrante[2]--;
                        }
                        if (i >= splitPoint && j >= splitPoint) {//quadrante 4
                            pretosQuadrante[3]--;
                        }
                        if (i == j) {//diagonal1
                            pretosDiagonal1--;
                        }
                        if (i == lado - 1 - j) {
                            pretosDiagonal2--;
                        }
                    }
                }
                linhasProcessadas++;
                progresso = true;
            }
            if (pretosLinha[i] == 0 && brancosLinha[i] > 0) {//apenas falta colocar brancos
                brancosLinha[i] = 0;
                for (int j = 0; j < lado; j++) {
                    if (codigo[i][j] == 0) {//celula sem info
                        codigo[i][j] = 2;//pinta preto
                        brancosColuna[j]--;
                        if (i < splitPoint && j < splitPoint) {//quadrante 2
                            brancosQuadrante[1]--;
                        }
                        if (i < splitPoint && j >= splitPoint) {//quadrante 1
                            brancosQuadrante[0]--;
                        }
                        if (i >= splitPoint && j < splitPoint) {//quadrante 3
                            brancosQuadrante[2]--;
                        }
                        if (i >= splitPoint && j >= splitPoint) {//quadrante 4
                            brancosQuadrante[3]--;
                        }
                        if (i == j) {//diagonal1
                            brancosDiagonal1--;
                        }
                        if (i == lado - 1 - j) {
                            brancosDiagonal2--;
                        }
                    }
                }
                linhasProcessadas++;
                progresso = true;
            }
        }
        //ver as colunas
        for (int i = 0; i < lado; i++) {
            if (brancosColuna[i] == 0 && pretosColuna[i] > 0) {//apenas falta colocar pretos
                pretosColuna[i] = 0;
                for (int j = 0; j < lado; j++) {
                    if (codigo[j][i] == 0) {//celula sem info
                        codigo[j][i] = 1;//pinta preto
                        pretosLinha[j]--;
                        if (i < splitPoint && j < splitPoint) {//quadrante 2
                            pretosQuadrante[1]--;
                        }
                        if (i < splitPoint && j >= splitPoint) {//quadrante 3
                            pretosQuadrante[2]--;
                        }
                        if (i >= splitPoint && j < splitPoint) {//quadrante 1
                            pretosQuadrante[0]--;
                        }
                        if (i >= splitPoint && j >= splitPoint) {//quadrante 4
                            pretosQuadrante[3]--;
                        }
                        if (i == j) {//diagonal1
                            pretosDiagonal1--;
                        }
                        if (j == lado - 1 - i) {
                            pretosDiagonal2--;
                        }
                    }
                }
                linhasProcessadas++;
                progresso = true;
            }
            if (pretosColuna[i] == 0 && brancosColuna[i] > 0) {//apenas falta colocar brancos
                brancosColuna[i] = 0;
                for (int j = 0; j < lado; j++) {
                    if (codigo[j][i] == 0) {//celula sem info
                        codigo[j][i] = 2;//pinta preto
                        brancosLinha[j]--;
                        if (i < splitPoint && j < splitPoint) {//quadrante 2
                            brancosQuadrante[1]--;
                        }
                        if (i < splitPoint && j >= splitPoint) {//quadrante 3
                            brancosQuadrante[2]--;
                        }
                        if (i >= splitPoint && j < splitPoint) {//quadrante 1
                            brancosQuadrante[0]--;
                        }
                        if (i >= splitPoint && j >= splitPoint) {//quadrante 4
                            brancosQuadrante[3]--;
                        }
                        if (i == j) {//diagonal1
                            brancosDiagonal1--;
                        }
                        if (i == lado - 1 - j) {
                            brancosDiagonal2--;
                        }
                    }
                }
                linhasProcessadas++;
                progresso = true;
            }
        }
        //ver as diagonais
        if (brancosDiagonal1 == 0 && pretosDiagonal1 > 0) {
            pretosDiagonal1 = 0;
            for (int i = 0; i < lado; i++) {
                if (codigo[i][i] == 0) {
                    codigo[i][i] = 1;
                    pretosLinha[i]--;
                    pretosColuna[i]--;
                    if (i == (lado - 1 - i) && pretosDiagonal2 > 0) {//ver se verdade
                        pretosDiagonal2--;
                    }
                    if (i < splitPoint) {
                        pretosQuadrante[1]--;
                    } else {
                        pretosQuadrante[3]--;
                    }
                }
            }
            progresso = true;
        }
        if (pretosDiagonal1 == 0 && brancosDiagonal1 > 0) {
            brancosDiagonal1 = 0;
            for (int i = 0; i < lado; i++) {
                if (codigo[i][i] == 0) {
                    codigo[i][i] = 2;
                    brancosLinha[i]--;
                    brancosColuna[i]--;
                    if (i == (lado - 1 - i) && brancosDiagonal2 > 0) {//ver se verdade
                        brancosDiagonal2--;
                    }
                    if (i < splitPoint) {
                        brancosQuadrante[1]--;
                    } else {
                        brancosQuadrante[3]--;
                    }
                }
            }
            progresso = true;
        }
        /*if (brancosDiagonal2 == 0 && pretosDiagonal2 > 0) {
            pretosDiagonal2 = 0;
            for (int i = 0; i < lado; i++) {
                if (codigo[i][lado - 1 - i] == 0) {
                    codigo[i][lado - 1 - i] = 1;
                    pretosLinha[i]--;
                    pretosColuna[i]--;
                    if (i == (lado - 1 - i) && pretosDiagonal1 > 0) {//ver se verdade
                        pretosDiagonal1--;
                    }
                    if (i < splitPoint) {
                        pretosQuadrante[0]--;
                    } else if (i == lado - 1 - i) {
                        pretosQuadrante[3]--;
                    } else {
                        pretosQuadrante[2]--;
                    }
                }
            }
            progresso = true;
        }
        if (pretosDiagonal2 == 0 && brancosDiagonal2 > 0) {
            brancosDiagonal2 = 0;
            for (int i = 0; i < lado; i++) {
                if (codigo[i][lado - 1 - i] == 0) {
                    codigo[i][lado - 1 - i] = 2;
                    brancosLinha[i]--;
                    brancosColuna[i]--;
                    if (i == (lado - 1 - i) && brancosDiagonal1 > 0) {//ver se verdade
                        brancosDiagonal1--;
                    }
                    if (i < splitPoint) {
                        brancosQuadrante[0]--;
                    } else if (i == lado - 1 - i) {
                        brancosQuadrante[3]--;
                    } else {
                        brancosQuadrante[2]--;
                    }
                }
            }
            progresso = true;
        }*/
        //Quadrantes
        if (brancosQuadrante[0] == 0 && pretosQuadrante[0] > 0) {//Quadrante1
            pretosQuadrante[0] = 0;
            for (int i = 0; i < splitPoint; i++) {//linhas
                for (int j = splitPoint; j < lado; j++) {//colunas
                    if (codigo[i][j] == 0) {//esta open
                        codigo[i][j] = 1;
                        pretosLinha[i]--;
                        pretosColuna[j]--;
                        if (i == lado - 1 - j) {
                            pretosDiagonal2--;
                        }
                    }
                }
            }
            progresso = true;
        }
        if (pretosQuadrante[0] == 0 && brancosQuadrante[0] > 0) {//Quadrante1
            brancosQuadrante[0] = 0;
            for (int i = 0; i < splitPoint; i++) {//linhas
                for (int j = splitPoint; j < lado; j++) {//colunas
                    if (codigo[i][j] == 0) {//esta open
                        codigo[i][j] = 2;
                        brancosLinha[i]--;
                        brancosColuna[j]--;
                        if (i == lado - 1 - j) {
                            brancosDiagonal2--;
                        }
                    }
                }
            }
            progresso = true;
        }
        if (brancosQuadrante[1] == 0 && pretosQuadrante[1] > 0) {//Quadrante2
            pretosQuadrante[1] = 0;
            for (int i = 0; i < splitPoint; i++) {//linhas
                for (int j = 0; j < splitPoint; j++) {//colunas
                    if (codigo[i][j] == 0) {//esta open
                        codigo[i][j] = 1;
                        pretosLinha[i]--;
                        pretosColuna[j]--;
                        if (i == j) {
                            pretosDiagonal1--;
                        }
                    }
                }
            }
            progresso = true;
        }
        if (pretosQuadrante[1] == 0 && brancosQuadrante[1] > 0) {//Quadrante2
            brancosQuadrante[1] = 0;
            for (int i = 0; i < splitPoint; i++) {//linhas
                for (int j = 0; j < splitPoint; j++) {//colunas
                    if (codigo[i][j] == 0) {//esta open
                        codigo[i][j] = 2;
                        brancosLinha[i]--;
                        brancosColuna[j]--;
                        if (i == j) {
                            brancosDiagonal1--;
                        }
                    }
                }
            }
            progresso = true;
        }
        if (brancosQuadrante[2] == 0 && pretosQuadrante[2] > 0) {//Quadrante3
            pretosQuadrante[2] = 0;
            for (int i = splitPoint; i < lado; i++) {//linhas
                for (int j = 0; j < splitPoint; j++) {//colunas
                    if (codigo[i][j] == 0) {//esta open
                        codigo[i][j] = 1;
                        pretosLinha[i]--;
                        pretosColuna[j]--;
                        if (i == lado - 1 - j) {
                            pretosDiagonal2--;
                        }
                    }
                }
            }
            progresso = true;
        }
        if (pretosQuadrante[2] == 0 && brancosQuadrante[2] > 0) {//Quadrante3
            brancosQuadrante[2] = 0;
            for (int i = splitPoint; i < lado; i++) {//linhas
                for (int j = 0; j < splitPoint; j++) {//colunas
                    if (codigo[i][j] == 0) {//esta open
                        codigo[i][j] = 2;
                        brancosLinha[i]--;
                        brancosColuna[j]--;
                        if (i == lado - 1 - j) {
                            brancosDiagonal2--;
                        }
                    }
                }
            }
            progresso = true;
        }
        if (brancosQuadrante[3] == 0 && pretosQuadrante[3] > 0) {//Quadrante4
            pretosQuadrante[3] = 0;
            if (lado % 2 == 1) {//caso impar
                if (codigo[splitPoint][splitPoint] == 0) {
                    pretosDiagonal2--;
                }
            }
            for (int i = splitPoint; i < lado; i++) {//linhas
                for (int j = splitPoint; j < lado; j++) {//colunas
                    if (codigo[i][j] == 0) {//esta open
                        codigo[i][j] = 1;
                        pretosLinha[i]--;
                        pretosColuna[j]--;
                        if (i == j) {
                            pretosDiagonal1--;
                        }
                    }
                }
            }
            progresso = true;
        }
        if (pretosQuadrante[3] == 0 && brancosQuadrante[3] > 0) {//Quadrante4
            brancosQuadrante[3] = 0;
            if (lado % 2 == 1) {//caso impar
                if (codigo[splitPoint][splitPoint] == 0) {
                    brancosDiagonal2--;
                }
            }
            for (int i = splitPoint; i < lado; i++) {//linhas
                for (int j = splitPoint; j < lado; j++) {//colunas
                    if (codigo[i][j] == 0) {//esta open
                        codigo[i][j] = 2;
                        brancosLinha[i]--;
                        brancosColuna[j]--;
                        if (i == j) {
                            brancosDiagonal1--;
                        }
                    }
                }
            }
            progresso = true;
        }
        //System.out.println("yoo");
        //concluir();
        if (progresso) {
            preProcessamento(linhasProcessadas, colunasProcessadas);
        }
    }

    public void leInputs(Scanner stdin) {
        int elementos;
        float div;
        encontrados = 0;
        lado = stdin.nextInt();
        pretosLinha = new int[lado];
        pretosColuna = new int[lado];
        brancosLinha = new int[lado];
        brancosColuna = new int[lado];
        tranzLinha = new int[lado];
        tranzColuna = new int[lado];
        pretosQuadrante = new int[4];
        brancosQuadrante = new int[4];
        for (int i = 0; i < lado; i++) {
            pretosLinha[i] = stdin.nextInt();
            brancosLinha[i] = lado - pretosLinha[i];
        }
        for (int i = 0; i < lado; i++) {
            pretosColuna[i] = stdin.nextInt();
            brancosColuna[i] = lado - pretosColuna[i];
        }
        for (int i = 0; i < lado; i++) {
            tranzLinha[i] = stdin.nextInt();
        }
        for (int i = 0; i < lado; i++) {
            tranzColuna[i] = stdin.nextInt();
        }
        div = (float) (lado / 2.0);
        for (int i = 0; i < 4; i++) {//ver bem as dimensoes dos quadrantes
            pretosQuadrante[i] = stdin.nextInt();

            if (i == 0) {
                elementos = Math.floorDiv(lado, 2) * Math.round(div);
                brancosQuadrante[i] = elementos - pretosQuadrante[i];
            }
            if (i == 1) {
                elementos = (Math.floorDiv(lado, 2)) * Math.floorDiv(lado, 2);
                brancosQuadrante[i] = elementos - pretosQuadrante[i];
            }
            if (i == 2) {
                elementos = Math.round(div) * Math.floorDiv(lado, 2);
                brancosQuadrante[i] = elementos - pretosQuadrante[i];
            }
            if (i == 3) {
                elementos = Math.round(div) * Math.round(div);
                brancosQuadrante[i] = elementos - pretosQuadrante[i];
            }

        }
        pretosDiagonal1 = stdin.nextInt();
        pretosDiagonal2 = stdin.nextInt();
        brancosDiagonal1 = lado - pretosDiagonal1;
        brancosDiagonal2 = lado - pretosDiagonal2;
        codigo = new int[lado][lado];
    }

    public void concluir() {
        if (encontrados == 1) {//
            System.out.println("VALID: 1 QR Code generated!");
            System.out.print("+");
            for (int j = 0; j < lado; j++) {
                System.out.print("-");
            }
            System.out.println("+");
            for (int i = 0; i < lado; i++) {
                System.out.print("|");
                for (int j = 0; j < lado; j++) {
                    if (hitCodigo[i][j] == 1) {
                        System.out.print("#");
                    } else if (hitCodigo[i][j] == 2) {
                        System.out.print(" ");
                    } else {
                        System.out.print("Z");
                    }
                }
                System.out.println("|");
            }
            System.out.print("+");
            for (int j = 0; j < lado; j++) {
                System.out.print("-");
            }
            System.out.println("+");
        } else if (encontrados > 1) {
            System.out.println("INVALID: " + encontrados + " QR Codes generated!");
        } else {
            System.out.println("DEFECT: No QR Code generated!");
        }
    }

    public boolean verDefeitos() {
        int totalLinhas1 = 0, totalLinhas2 = 0, totalColunas1 = 0, totalColunas2 = 0;
        int splitPoint = Math.floorDiv(lado, 2);
        //ve validade dos quadrantes e trazicoes dentro de cada linha
        for (int i = 0; i < splitPoint; i++) {
            totalLinhas1 = totalLinhas1 + pretosLinha[i];
            if (Math.min(pretosLinha[i], brancosLinha[i]) * 2 + 1 < tranzLinha[i]) {
                return false;
            }
        }
        if (totalLinhas1 != (pretosQuadrante[0] + pretosQuadrante[1])) {
            return false;
        }
        for (int i = splitPoint; i < lado; i++) {
            totalLinhas2 = totalLinhas2 + pretosLinha[i];
            if (Math.min(pretosLinha[i], brancosLinha[i]) * 2 + 1 < tranzLinha[i]) {
                return false;
            }
        }
        if (totalLinhas2 != (pretosQuadrante[2] + pretosQuadrante[3])) {
            return false;
        }

        for (int i = 0; i < splitPoint; i++) {
            totalColunas1 = totalColunas1 + pretosColuna[i];
            if (Math.min(pretosColuna[i], brancosColuna[i]) * 2 + 1 < tranzColuna[i]) {
                return false;
            }
        }
        if (totalColunas1 != (pretosQuadrante[1] + pretosQuadrante[2])) {
            return false;
        }
        for (int i = splitPoint; i < lado; i++) {
            totalColunas2 = totalColunas2 + pretosColuna[i];
            if (Math.min(pretosColuna[i], brancosColuna[i]) * 2 + 1 < tranzColuna[i]) {
                return false;
            }
        }
        if (totalColunas2 != (pretosQuadrante[0] + pretosQuadrante[3])) {
            return false;
        }
        //ve validade das linhas em relacao as colunas
        if ((totalLinhas1 + totalLinhas2) != totalColunas1 + totalColunas2) {
            return false;
        }
        //ve se podem exitir as diagonais
        if (pretosDiagonal1 > (pretosQuadrante[1] + pretosQuadrante[3])) {
            return false;
        }
        if (pretosDiagonal2 > (pretosQuadrante[0] + pretosQuadrante[2])) {
            return false;
        }
        return true;
    }

    public void colocaquad(int linha, int coluna, int cor) {
        chamadas++;
        boolean eTranzLinha = false, eTranzColuna = false;
        //System.out.println(linha + " " + coluna + " " + cor);
        if (linha == lado - 1 && coluna > 0) {//generalizar
            if (tranzColuna[coluna - 1] != 0) {
                return;
            }
        }
        if (coluna == lado) {
            if (tranzLinha[linha] != 0) {
                return;
            }
            //System.out.println("mudei");
            coluna = 0;
            linha++;
        }
        if (linha == lado) {//condicao de paragem
            if (cor == 1) {
                encontrados++;
                //concluir();
                if (encontrados == 1) {
                    hitCodigo = new int[lado][lado];
                    for (int i = 0; i < lado; i++) {
                        hitCodigo[i] = codigo[i].clone();
                    }
                }
            }
            return;
        }
        //check por preprocessamento
        if (codigo[linha][coluna] != 0) {
            if (codigo[linha][coluna] == cor) {
                //atualizar as tranzicoes
                if (coluna != 0 && codigo[linha][coluna - 1] != cor) {//linha
                    eTranzLinha = true;
                }
                if (linha != 0 && codigo[linha - 1][coluna] != cor) {//coluna
                    eTranzColuna = true;
                }
                if (eTranzLinha && tranzLinha[linha] > 0 || !eTranzLinha) {//relacionado com o anterior??
                    if (eTranzColuna && tranzColuna[coluna] > 0 || !eTranzColuna) {
                        if (eTranzColuna) {
                            tranzColuna[coluna]--;
                        }
                        if (eTranzLinha) {
                            tranzLinha[linha]--;
                        }
                        colocaquad(linha, coluna + 1, 1);
                        colocaquad(linha, coluna + 1, 2);
                        if (eTranzColuna) {
                            tranzColuna[coluna]++;
                        }
                        if (eTranzLinha) {
                            tranzLinha[linha]++;
                        }
                    }
                }
            }
            return;
        }
        //determinar o quadrante onde estou
        int quadrante;
        if (linha
                + 1 <= Math.floorDiv(lado,
                        2) && coluna + 1 > Math.floorDiv(lado, 2)) {
            quadrante = 0;
        } else if (linha
                + 1 <= Math.floorDiv(lado,
                        2) && coluna + 1 <= Math.floorDiv(lado, 2)) {
            quadrante = 1;
        } else if (linha
                + 1 > Math.floorDiv(lado,
                        2) && coluna + 1 <= Math.floorDiv(lado, 2)) {
            quadrante = 2;
        } else {
            quadrante = 3;
        }
        //System.out.println("Quadrante:"+quadrante);
        //ver se sou uma trazicao
        if (coluna != 0 && codigo[linha][coluna - 1] != cor) {//linha
            eTranzLinha = true;
        }
        if (linha != 0 && codigo[linha - 1][coluna] != cor) {//coluna
            eTranzColuna = true;
        }
        //ver se sou uma diagonal
        boolean diagonal1 = false;
        boolean diagonal2 = false;
        if (linha == coluna) {
            diagonal1 = true;
        }
        if (linha == lado - 1 - coluna) {
            diagonal2 = true;
        }
        //System.out.println("tranzicoes" + eTranzLinha + " " + eTranzColuna);
        //System.out.println("Diagonal" + diagonal1 + " " + diagonal2);
        //Stack de condicoes
        if (cor == 1) {//colocar um preto   
            //System.out.println("sou um preto");
            if (pretosLinha[linha] > 0) {//pretos na linha
                //System.out.println("posso estar na linha");
                if (pretosColuna[coluna] > 0) {// pretos na coluna
                    //System.out.println("posso estar na coluna");
                    if (pretosQuadrante[quadrante] > 0) {//pretos no quadrante
                        //System.out.println("posso estar no quadrante");
                        if ((eTranzLinha && tranzLinha[linha] > 0) || !eTranzLinha) {//tranzicao linha
                            //System.out.println("respeito as tranz linha");
                            if ((eTranzColuna && tranzColuna[coluna] > 0) || !eTranzColuna) {//tranzicao coluna
                                //System.out.println("respeito as tranz coluna");
                                if (diagonal1 || diagonal2) {//estou nas diagonais
                                    if ((pretosDiagonal1 == 0 && diagonal1) || (pretosDiagonal2 == 0 && diagonal2)) {
                                        return;
                                    }
                                    if (diagonal1) {
                                        pretosDiagonal1--;
                                    }
                                    if (diagonal2) {
                                        pretosDiagonal2--;
                                    }
                                }
                                pretosLinha[linha]--;
                                pretosColuna[coluna]--;
                                pretosQuadrante[quadrante]--;
                                if (eTranzColuna) {
                                    tranzColuna[coluna]--;
                                }
                                if (eTranzLinha) {
                                    tranzLinha[linha]--;
                                }

                                codigo[linha][coluna] = 1;//insseri
                                //nova chamada
                                colocaquad(linha, coluna + 1, 1);
                                colocaquad(linha, coluna + 1, 2);
                                //
                                codigo[linha][coluna] = 0;
                                pretosLinha[linha]++;
                                pretosColuna[coluna]++;
                                pretosQuadrante[quadrante]++;
                                if (eTranzColuna) {
                                    tranzColuna[coluna]++;
                                }
                                if (eTranzLinha) {
                                    tranzLinha[linha]++;
                                }
                                if (diagonal1) {
                                    pretosDiagonal1++;
                                }
                                if (diagonal2) {
                                    pretosDiagonal2++;
                                }
                            }
                        }
                    }
                }
            }
        } else {//colocar um branco
            //System.out.println("sou um branco");
            if (brancosLinha[linha] > 0) {//pretos na linha
                //System.out.println("posso estar na linha");
                if (brancosColuna[coluna] > 0) {// pretos na coluna
                    //System.out.println("posso estar na coluna");
                    if (brancosQuadrante[quadrante] > 0) {//pretos no quadrante
                        //System.out.println("posso estar no quadrante");
                        if ((eTranzLinha && tranzLinha[linha] > 0) || !eTranzLinha) {//tranzicao linha
                            if ((eTranzColuna && tranzColuna[coluna] > 0) || !eTranzColuna) {//tranzicao coluna
                                if (diagonal1 || diagonal2) {//estou nas diagonais
                                    if ((brancosDiagonal1 == 0 && diagonal1) || (brancosDiagonal2 == 0 && diagonal2)) {
                                        return;
                                    }
                                    if (diagonal1) {
                                        brancosDiagonal1--;
                                    }
                                    if (diagonal2) {
                                        brancosDiagonal2--;
                                    }
                                }
                                brancosLinha[linha]--;
                                brancosColuna[coluna]--;
                                brancosQuadrante[quadrante]--;
                                if (eTranzColuna) {
                                    tranzColuna[coluna]--;
                                }
                                if (eTranzLinha) {
                                    tranzLinha[linha]--;
                                }

                                codigo[linha][coluna] = 2;
                                //nova chamada
                                colocaquad(linha, coluna + 1, 1);
                                colocaquad(linha, coluna + 1, 2);
                                //
                                codigo[linha][coluna] = 0;

                                brancosLinha[linha]++;
                                brancosColuna[coluna]++;
                                brancosQuadrante[quadrante]++;
                                if (eTranzColuna) {
                                    tranzColuna[coluna]++;
                                }
                                if (eTranzLinha) {
                                    tranzLinha[linha]++;
                                }
                                if (diagonal1) {
                                    brancosDiagonal1++;
                                }
                                if (diagonal2) {
                                    brancosDiagonal2++;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

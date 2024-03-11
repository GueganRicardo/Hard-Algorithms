import java.util.Scanner;

public class El1V9TurboSmurf {

    int maxShares;
    int ratio;
    int dias;
    long resultado1, resultado3;
    int[] resultado2;
    int[][] lucro;
    int[][] caminhosDP;
    int[] valores;
    int divisor = 1000000007;
    
    public static void main(String[] args) {
        El1V9TurboSmurf tS = new El1V9TurboSmurf();
        Scanner stdin = new Scanner(System.in);
        int tarefa = stdin.nextInt();
        int vezes = stdin.nextInt();
        tS.dias = stdin.nextInt();
        tS.maxShares = stdin.nextInt();
        tS.ratio = stdin.nextInt();
        tS.valores = new int[tS.dias];
        stdin.nextLine();
        //teste ler input
        if (tarefa == 1) {
            for (int i = 0; i < vezes; i++) {
                tS.tarefa1(stdin);
                System.out.println(tS.resultado1);
            }
        } else if (tarefa == 2) {
            for (int i = 0; i < vezes; i++) {
                tS.tarefa2(stdin);
                System.out.println(tS.resultado1);
                System.out.print(tS.resultado2[0]);
                for (int j = 1; j < tS.dias; j++) {
                    System.out.print(" " + tS.resultado2[j]);
                }
                System.out.println("");
            }
        } else if (tarefa == 3) {
            for (int i = 0; i < vezes; i++) {
                tS.tarefa3(stdin);
                System.out.println(tS.resultado1 + " " + tS.resultado3);
            }
        }

    }

    public void tarefa1(Scanner stdin) {
        boolean mao = false; //false -> nao tenho acoes  true-> tenho acoes
        long currentProfit = 0;
        int valorCompra, valorVenda = 0;
        String linha = stdin.nextLine();
        String[] numeros = linha.split(" ");
        for (int i = 0; i < dias; i++) {
            valores[i] = Integer.parseInt(numeros[i]);
        }
        valorCompra = valores[0];
        for (int i = 1; i < dias; i++) {
            if (!mao) {//procura minimo local
                if (valorCompra > valores[i]) {
                    valorCompra = valores[i];
                } else if (valorCompra + ratio < valores[i]) {//momento de vender
                    mao = true;
                    valorVenda = valores[i];//fazer la venda
                }
            } else {//procura maximo local
                if (valorVenda < valores[i]) {
                    valorVenda = valores[i];
                } else if (valorVenda > valores[i] + ratio) {//fazemos a venda
                    currentProfit = currentProfit + maxShares * (valorVenda - valorCompra - ratio);
                    valorCompra = valores[i];
                    mao = false;
                }
            }
        }
        if (mao && valorCompra + ratio < valorVenda) { // tinha uma venda por fazer
            currentProfit = currentProfit + maxShares * (valorVenda - valorCompra - ratio);
        }
        resultado1 = currentProfit;
    }

    public void tarefa2(Scanner stdin) {
        resultado2 = new int[dias];
        boolean mao = false; //false -> nao tenho acoes  true-> tenho acoes
        long currentProfit = 0;
        int valorCompra, valorVenda = 0, diaCompra, diaVenda = -1;
        String linha = stdin.nextLine();
        String[] numeros = linha.split(" ");
        for (int i = 0; i < dias; i++) {
            valores[i] = Integer.parseInt(numeros[i]);
        }
        valorCompra = valores[0];
        diaCompra = 0;
        for (int i = 1; i < dias; i++) {
            if (!mao) {//procura minimo local
                if (valorCompra > valores[i]) {
                    valorCompra = valores[i];
                    diaCompra = i;
                } else if (valorCompra + ratio < valores[i]) {//momento de vender
                    mao = true;
                    valorVenda = valores[i];//fazer la venda
                    diaVenda = i;
                }
            } else {//procura maximo local
                if (valorVenda < valores[i]) {
                    valorVenda = valores[i];
                    diaVenda = i;
                } else if (valorVenda > valores[i] + ratio) {//fazemos a venda
                    resultado2[diaCompra] = maxShares;
                    resultado2[diaVenda] = -maxShares;
                    currentProfit = currentProfit + maxShares * (valorVenda - valorCompra - ratio);
                    valorCompra = valores[i];
                    diaCompra = i;
                    mao = false;
                }
            }
        }

        if (mao && valorCompra + ratio < valorVenda) {   // tinha uma venda por fazer
            resultado2[diaCompra] = maxShares;
            resultado2[diaVenda] = -maxShares;
            currentProfit = currentProfit + maxShares * (valorVenda - valorCompra - ratio);
        }

        resultado1 = currentProfit;

    }

    public void tarefa3(Scanner stdin) {
        caminhosDP = new int[dias][maxShares + 1];
        lucro = new int[dias][maxShares + 1];
        int[] lucrosLocais = new int[maxShares + 1];
        int lucroLocalMax;
        String linha = stdin.nextLine();
        String[] numeros = linha.split(" ");
        for (int i = 0; i < dias; i++) {
            valores[i] = Integer.parseInt(numeros[i]);
        }
        for (int i = 0; i <= maxShares; i++) {
            caminhosDP[0][i] = 1;
            lucro[0][i] = -((valores[0]+ratio) * i);
        }
        for (int i = 1; i < dias; i++) {
            for (int j = 0; j <= maxShares; j++) {//determinar o lucro max deste dia com x acoes
                for (int k = 0; k <= maxShares; k++) {//determinar as possibilidades de lucro
                    if (k == j) {//manter as acoes
                        lucrosLocais[k] = lucro[i - 1][k];
                    } else if (k > j) {//vender acoes
                        lucrosLocais[k] = lucro[i - 1][k] + valores[i] * (k - j);//venda
                    } else if (k < j) {//comprar acoes
                        lucrosLocais[k] = lucro[i - 1][k] - (valores[i] + ratio) * (j-k);//compra
                    }
                }
                lucroLocalMax = Integer.MIN_VALUE;
                for (int k = 0; k <= maxShares; k++) {
                    if (lucrosLocais[k] > lucroLocalMax) {
                        lucroLocalMax = lucrosLocais[k];
                    }
                }
                lucro[i][j]=lucroLocalMax;
                for(int k = 0; k <=maxShares; k++){
                    if(lucrosLocais[k]==lucroLocalMax){
                        caminhosDP[i][j]= (caminhosDP[i][j]%divisor + caminhosDP[i-1][k]%divisor)%divisor;
                    }
                }
            }
        }
        resultado1=lucro[dias-1][0];
        resultado3 = caminhosDP[dias-1][0];
    }
}

package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		return soma(array, 0);
	}
	
	private int soma(int[] array, int i) {
		int res = 0;
		if (i < array.length) 
			res += array[i] + soma(array, i+1);
		
		return res;
	}

	public long calcularFatorial(int n) {
		long result = 1;
		if (n > 1) {
			result = calcularFatorial(n - 1) * n;
		}
		System.out.println(n + "! = " + result);
		return result;
	}

	public int calcularFibonacci(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return calcularFibonacci(n-1) + calcularFibonacci(n-2);
	}

	public int countNotNull(Object[] array) {
		return countNotNull(array, 0);
	}

	private int countNotNull(Object[] array, int i) {
		if (i < array.length) {
			if (array[i] != null) {
				return 1 + countNotNull(array, i+1);
			}
			return countNotNull(array, i+1);
		} else {
			return 0;
		}
	}

	public long potenciaDe2(int expoente) {
		if (expoente == 0) {
			return 1;
		}
		return 2 * potenciaDe2(expoente-1);
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {

		if (n == 1) {
			return termoInicial;
		}
		return razao + progressaoAritmetica(termoInicial, razao, n - 1);
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		
		if (n == 1) {
			return termoInicial;
		}
		return razao * progressaoGeometrica(termoInicial, razao, n - 1);
	}
	
	
}

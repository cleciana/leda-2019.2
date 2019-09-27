package problems;

import java.util.Scanner;

import orderStatistic.OrderStatisticsSelectionImpl;

public class Test {

	public static void main(String[] args) {
		String[] linha = new Scanner(System.in).nextLine().split(" ");
		Integer[] numeros = new Integer[linha.length];
		
		for (int i = 0; i < linha.length; i++) {
			numeros[i] = Integer.valueOf(linha[i]);
		}
		
		OrderStatisticsSelectionImpl<Integer> order = new  OrderStatisticsSelectionImpl<Integer>();
		
		// System.out.println(pro.floor(numeros, 7));
		
		// System.out.println(pro.ceil(numeros, 7));
		
		System.out.println(order.getOrderStatistics(numeros, 10));
		
		
	}
}

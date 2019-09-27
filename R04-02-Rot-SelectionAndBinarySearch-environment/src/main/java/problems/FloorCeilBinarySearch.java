package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes:
 *  - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

   public Integer floor(Integer[] array, Integer x) {
      return floorSearch(array, 0, array.length - 1, x);
   }

   private Integer floorSearch(Integer[] array, int i, int j, Integer x) {
      int meio = (i + j) / 2;

      if (array[meio] <= x) {
         return array[meio];

      } else {
         return floorSearch(array, i, meio, x);
      }
   }

   public Integer ceil(Integer[] array, Integer x) {
      return ceilSearch(array, 0, array.length - 1, x);
   }

   private Integer ceilSearch(Integer[] array, int i, int j, Integer x) {
      int meio = (i + j) / 2;

      if (array[meio] >= x) {
         return array[meio];

      } else {
         return ceilSearch(array, meio, j, x);
      }
   }

}

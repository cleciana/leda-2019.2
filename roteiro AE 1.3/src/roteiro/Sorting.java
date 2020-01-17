package roteiro;

public interface Sorting <T extends Comparable<T>> {

	public void sort(T[] array);
	
	public long tempoDeExecucao();
	
	public String getNome();
}

package roteiro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class FluxoDeDados {

	private File data = new File("sort.data");
	
	public void salvar(String linha) {
		
		try {
			OutputStream os = new FileOutputStream(data, true);
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write(linha + System.lineSeparator());
			
			bw.close();
			
		} catch (Exception io) {
			io.printStackTrace();
		}
	}
	
}

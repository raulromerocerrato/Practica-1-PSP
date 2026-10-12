import java.io.*;

public class FiltreLog {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int comptador = 0;
        int linies = 0;
        String linia;
        while((linia = br.readLine()) != null) {
            if(linia.contains("ERROR")) {
                comptador++;
            }
            linies++;
        }
        if(linies > 0) {
            System.out.println(comptador);
            System.exit(0);
        } else {
            System.err.println("Error: Text buit");
            System.exit(1);
        }
    }
}
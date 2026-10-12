import java.io.*;

public class FiltreLog {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int comptador = 0;
        String linia;
        while((linia = br.readLine()) != null) {
            if(linia.contains("ERROR")) {
                comptador++;
            }
        }
        System.out.println(comptador);
    }
}
import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException {
        String classpath = System.getProperty("java.class.path");
        ProcessBuilder pb = new ProcessBuilder("java", "-cp", classpath, "FiltreLog");
        pb.redirectError(new File("errors_filtre.log"));
        Process process = pb.start();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        out.write("ERROR: Hola món\n");
        out.write("Hola món\n");
        out.write("ERROR: Hola món\n");
        out.write("ERROR: Hola món\n"); 
        out.flush();
        out.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String resposta = in.readLine();
        System.out.println("El fill diu: " + resposta);
        process.waitFor();

        int codiSortida = process.exitValue();
        if (codiSortida == 0) {
            System.out.println("El procés fill ha acabat correctament.");
        } else {
            System.err.println("El procés fill ha donat error, consulta a errors_filtre.log. Codi de sortida: " + codiSortida);
        }
    }
}

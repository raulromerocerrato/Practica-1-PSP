import java.io.*;

public class AnalitzadorPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException {
        String classpath = System.getProperty("java.class.path");
        String text = "ERROR: Hola món\n"
            + "WARNING: Hola món\n"
            + "ERROR: Hola món\n"
            + "ERROR: Hola món\n"
            + "WARNING: Hola món\n"
            + "WARNING: Hola món\n";
            
        ProcessBuilder pbA = new ProcessBuilder("java", "-cp", classpath, "FiltreLog", "ERROR");
        ProcessBuilder pbB = new ProcessBuilder("java", "-cp", classpath, "FiltreLog", "WARNING");
        pbA.redirectError(new File("errors_filtre.log"));
        pbB.redirectError(new File("errors_filtre.log"));
        Process processA = pbA.start();
        Process processB = pbB.start();

        BufferedWriter outA = new BufferedWriter(new OutputStreamWriter(processA.getOutputStream()));
        BufferedWriter outB = new BufferedWriter(new OutputStreamWriter(processB.getOutputStream()));
        outA.write(text);
        outB.write(text);
        outA.flush();
        outB.flush();
        outA.close();
        outB.close();

        BufferedReader inA = new BufferedReader(new InputStreamReader(processA.getInputStream()));
        String respostaA = inA.readLine();
        BufferedReader inB = new BufferedReader(new InputStreamReader(processB.getInputStream()));
        String respostaB = inB.readLine();
        System.out.println("El fill A diu: " + respostaA);
        System.out.println("El fill B diu: " + respostaB);
        processA.waitFor();
        processB.waitFor();

        int codiSortidaA = processA.exitValue();
        int codiSortidaB = processB.exitValue();
        if (codiSortidaA == 0) {
            System.out.println("El procés fill A ha acabat correctament.");
        } else {
            System.err.println("El procés fill A ha donat error, consulta a errors_filtre.log. Codi de sortida: " + codiSortidaA);
        }
        if (codiSortidaB == 0) {
            System.out.println("El procés fill B ha acabat correctament.");
        } else {
            System.err.println("El procés fill B ha donat error, consulta a errors_filtre.log. Codi de sortida: " + codiSortidaB);
        }
    }
}

import javax.print.DocFlavor;

/**
 * Created by Jay on 3/26/2017.
 */
public class MemSim {
    public static void main(String[] args) {
        int nFrames = Integer.valueOf(args[0]);
        String pageReplacementAlgorithm = args[1];
        String mode = args[2];
        String traceFile = args[3];

        Simulator simulator = new Simulator(nFrames, pageReplacementAlgorithm, mode, traceFile);

        simulator.simulate();




    }
}

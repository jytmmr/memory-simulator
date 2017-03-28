import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Jay on 3/26/2017.
 */
public class Simulator {

    int nFrames;
    String pageReplacementAlgorithm;
    String mode;
    String traceFile;

    public Simulator(int nFrames, String pageReplacementAlgorithm, String mode, String traceFile){
        this.nFrames = nFrames;
        this.pageReplacementAlgorithm = pageReplacementAlgorithm;
        this.mode = mode;
        this.traceFile = traceFile;
    }

    public SimulationResults simulate(){

        SimulationResults simulationResults = new SimulationResults();
        FrameTable frameTable = new FrameTable(nFrames);
        try{
            int counter = Integer.MIN_VALUE;
            Scanner scanner = new Scanner(new File(this.traceFile));
            int freeFrames = nFrames;
            while(scanner.hasNext()) {

                Scanner sc = new Scanner(scanner.nextLine());

                Integer address = Integer.parseUnsignedInt(sc.next(), 16);

                System.out.println(address);

                String mode = sc.next();

                System.out.println("   " + mode);

                int pageNumber = address >> 12;

                System.out.println(pageNumber);


                boolean writeMode = mode.equals("W");


                // we are assuming that containsKey is a constant time operation
                System.out.println("Free frames " + freeFrames);
                if(freeFrames == 0){
                    System.out.println(frameTable);
                    System.exit(0);
                }
                if(!frameTable.contains(pageNumber)){
                    PageInfo pageInfo = new PageInfo(writeMode, counter, pageNumber);
                    if(freeFrames > 0){
                        --freeFrames;
                        // TODO: if counter == long.MaxVal, restructure frame table and lower counter value
                        frameTable.add(pageInfo);

                    }
                    else{
                        boolean diskWrite = false;
                        switch(this.pageReplacementAlgorithm){
                            case "lru":
                                diskWrite = lru(frameTable, pageInfo);
                            case "random":
                                diskWrite = random(frameTable, counter, pageInfo);
                            case "clockpage":
                                diskWrite = clockPage(frameTable, counter, pageInfo);
                            case "ideal":
                                diskWrite = ideal(frameTable, counter, pageInfo);
                        }

                        if(diskWrite){
                            ++simulationResults.diskWriteCount;
                        }
                    }
                    ++simulationResults.diskReadCount;
                }

                simulationResults.eventCount++;
                ++counter;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return simulationResults;

    }

    public boolean lru(FrameTable frameTable, PageInfo pageInfo){
        PageInfo removedPage = frameTable.removeLeastRecentlyUsed();
        frameTable.add(pageInfo);
        return removedPage.dirty;
    }

    public boolean random(FrameTable frameTable, PageInfo pageInfo){
        PageInfo removedPage = frameTable.removeRandom();
        frameTable.add(pageInfo);
        return removedPage.dirty;
    }

    public boolean clockPage(){

    }




}

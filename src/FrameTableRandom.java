import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by Jay on 3/27/2017.
 */
public class FrameTableRandom {
    HashMap<Integer, PageInfo> frameMap;
    PriorityQueue<PageInfo> frameQueue;
    ArrayList<PageInfo> frameClock = new ArrayList<>();

    int clockIndex = 0;

    int size;

    public FrameTable(int nFrames){
        frameMap = new HashMap<>(nFrames);
        frameQueue = new PriorityQueue<>(nFrames);
        size = nFrames;
    }


    public void add(PageInfo pageInfo){
        frameQueue.add(pageInfo);
        frameMap.put(pageInfo.pageNumber, pageInfo);
        frameClock.add(pageInfo);
    }

    public boolean replace(PageInfo replacer, Callable<PageInfo> funct){
        funct(replacer);
    }

    public boolean contains(int pageNumber){
        return frameMap.containsKey(pageNumber);
    }

    public PageInfo removeLeastRecentlyUsed(PageInfo replacer){
        PageInfo removedPage = frameMap.remove(frameQueue.poll().pageNumber);
        add(replacer);
        return removedPage;
    }

    public PageInfo removeRandom(PageInfo replacer){
        PageInfo removedPage = frameMap.remove(frameMap.keySet().toArray()[(new Random()).nextInt(frameQueue.size())]);
        frameQueue.remove(removedPage);
        add(replacer);
        return removedPage;
    }

    public PageInfo removeClockPage(PageInfo replacer){
        PageInfo removedPage;
        while(true){
            if(frameClock.get(clockIndex).access == false){
                removedPage = frameClock.get(clockIndex);
                frameClock.set(clockIndex, )
            }
        }
    }

    public String toString(){
        return frameMap + "\n\n\n" + frameQueue;
    }

}

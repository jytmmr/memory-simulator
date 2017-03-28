import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by Jay on 3/27/2017.
 */
public class FrameTableClock {
    ArrayList<PageInfo> frameClock;

    int clockIndex = 0;

    int size;

    public FrameTableClock(int nFrames){
        frameClock = new ArrayList<>(nFrames);
        size = nFrames;
    }


    public void add(PageInfo pageInfo){
        frameClock.add(pageInfo);
    }

    public boolean replace(PageInfo replacer){
        PageInfo removedPage;
        while(true){
            if(frameClock.get(clockIndex).access == false){
                removedPage = frameClock.get(clockIndex);
                frameClock.set(clockIndex, )
            }
        }
        return removedPage;
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

    }
    public String toString(){
        return frameMap + "\n\n\n" + frameQueue;
    }

}

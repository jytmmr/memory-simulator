import java.util.Comparator;

/**
 * Created by Jay on 3/26/2017.
 */
public class PageInfo implements Comparable<PageInfo> {


    public boolean dirty;
    public int lastUsed;
    int pageNumber;
    boolean access;

    public  PageInfo(boolean dirty, int lastUsed, int pageNumber){
        this.dirty = dirty;
        this.lastUsed = lastUsed;
        this.pageNumber = pageNumber;
    }

    public int compareTo(PageInfo pageInfo){
        return this.lastUsed - pageInfo.lastUsed;
    }




}

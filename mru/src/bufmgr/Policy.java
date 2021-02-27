package bufmgr;

import diskmgr.*;
import global.*;

  /**
   * class Policy is a subclass of class Replacer use the given replacement
   * policy algorithm for page replacement
   */
class Policy extends  Replacer {

  //
  // Frame State Constants
  //
  protected static final int AVAILABLE = 10;
  protected static final int REFERENCED = 11;
  protected static final int PINNED = 12;
  int numberOfBuffers;

  //Following are the fields required for LRU and MRU policies:
  /**
   * private field
   * An array to hold number of frames in the buffer pool
   */

    private int  frames[];
 
  /**
   * private field
   * number of frames used
   */   
  private int  nframes;

  /** Clock head; required for the default clock algorithm. */
  protected int head;

  /**
   * This pushes the given frame to the end of the list.
   * @param frameNo	the frame number
   */
  

  /**
   * Class constructor
   * Initializing frames[] pinter = null.
   */
    public Policy(BufMgr mgrArg)
    {
      super(mgrArg);
      numberOfBuffers = mgrArg.getNumBuffers();
      // initialize the frame states
    for (int i = 0; i < frametab.length; i++) {
      frametab[i].state = AVAILABLE;
    }
      // initialize parameters for LRU and MRU
      nframes = 0;
      frames = new int[numberOfBuffers];
      for ( int i = 0; i < numberOfBuffers; ++i )
        frames[i] = -i;

      frames[0] = -numberOfBuffers;

    // initialize the clock head for Clock policy
    head = -1;
    }
  /**
   * Notifies the replacer of a new page.
   */


   private void update(int frameNo)
  {
     //This function is to be used for LRU and MRU
      int i;
      int numBuffers = numberOfBuffers;
     
    for ( i=0; i < numBuffers; ++i )
        if ( frames[i] < 0  ||  frames[i] == frameNo )
            break;

  
    
     
  }
  public void newPage(FrameDesc fdesc) {
    // no need to update frame state
  }

  /**
   * Notifies the replacer of a free page.
   */
  public void freePage(FrameDesc fdesc) {
    fdesc.state = AVAILABLE;
  }

  /**
   * Notifies the replacer of a pined page.
   */
  public void pinPage(FrameDesc fdesc) {
    fdesc.state = PINNED;
  }

  /**
   * Notifies the replacer of an unpinned page.
   */
  public void unpinPage(FrameDesc fdesc) {
    if (fdesc.pincnt == 0)
		  fdesc.state = REFERENCED;

  }
  
  /**
   * Finding a free frame in the buffer pool
   * or choosing a page to replace using your policy
   *
   * @return 	return the frame number
   *		return -1 if failed
   */

 public int pickVictim()
 {
  int numBuffers = numberOfBuffers;

 int i, frame1;
 for ( i = 0; i < numBuffers; ++i )
        if (frames[i] < 0) {
            if ( i == 0 )
                frames[i] = 0;
            else
                frames[i] =frames[i]* -1;
            frame1 = frames[i];
            frametab[frame1].state = PINNED;
            
            update(frame1);
            return frame1;
        }

   for ( i = 0; i < numBuffers; ++i ) {
        frame1 = frames[i];
       if ( frametab[frame1].state != PINNED ) {
           frametab[frame1].state  = PINNED;
           update(frame1);
           return frame1;
       }
   }

   return -1;
 }
}
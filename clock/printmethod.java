public void printBhrAndRefCount(){ 
  int totPageHits=0;
  int totPageRequests=0;
  int pageLoadHits=0;
  int pageLoadRequests=0;
  int aggregateBHR=0;
  int pageLoadBHR=0;




    //print counts:
    System.out.println("totPageHits: "+totPageHits);
    System.out.println("totPageRequests: "+totPageRequests);
    System.out.println("pageLoadHits: "+pageLoadHits);
    System.out.println("pageLoadRequests: "+pageLoadRequests);
    System.out.println("+----------------------------------------+");
    
    
    //compute BHR1 and BHR2 
    aggregateBHR = (float)totPageHits/totPageRequests;   
    pageLoadBHR = (float)pageLoadHits/pageLoadRequests;
  
    System.out.println("Aggregate BHR (BHR1): "+ aggregateBHR);
    System.out.println("Load-based BHR (BHR2): "+ pageLoadBHR);
    System.out.println("+----------------------------------------+");
    
    
    //sc: set the first 8 page refs to 0 so it wont distort ref count
    for (int i = 0; i < 9 ; i++)  {  
      //* System.out.println("+---Before setting it to zero: page, refCount: "+pageRefCount[i][2]+", "+pageRefCount[i][1]);
      pageRefCount[i][1]=0;
    }
    
    //before sorting, need to compare the FINAL round refcounts and fix it
    for (int i = 0; i < pageRefCount.length ; i++) {
        if (pageRefCount[i][0] > pageRefCount[i][1]) pageRefCount[i][1] = pageRefCount[i][0];
        pageRefCount[i][0] = 0;
    }
    //Sort and print top 5 page references here. uncomment and substitute your code
    sortbyColumn(pageRefCount, 1);
    
    System.out.println("The top k referenced pages are:");
    System.out.println("       Page No.\t\tNo. of references");
       
    for (int i = 0; i < 10 ; i++)    
      System.out.println("\t"+pageRefCount[i][2]+"\t\t"+pageRefCount[i][1]);
    
    System.out.println("+----------------------------------------+");
    //* System.out.println("pageRefCount.length: "+pageRefCount.length);
    // *for (int i = 0; i < pageRefCount.length ; i++)    
      // *System.out.println("\t"+pageRefCount[i][2]+"\t\t"+pageRefCount[i][1]+"\t\t"+pageRefCount[i][0]);
}

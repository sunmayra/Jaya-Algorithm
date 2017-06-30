import java.io.*;
import java.util.Random;
import java.util.*;
import java.util.Collections;

class Dimension 	
{
  public ArrayList listofdimension=new ArrayList();
  public Dimension(double x[])
  {
     for(int i=0;i<x.length;i++)
    { 
        listofdimension.add(x[i]);
    }
  }
}

public class JayaSeq
{ 
			
	public static void main(String[] args) throws Exception{
		
		double[] fxnew;
		double[] fxold;
		
		double[] Xnew;
		double[] Xold;
        
		double max=0.0;
		double min=0.0;

		int dim=0;
		int pop=0;										
		
		int func_num=0;
		int i;
		
        int best=0;
        int worst=0;

        int itr_cnt=0;
        int curr_cnt=1; 							

		long startTime =System.currentTimeMillis();  

		JayaTestFunc13 tf = new JayaTestFunc13();  
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuffer att=new StringBuffer();
		StringBuffer att1=new StringBuffer(); 		 							
			
		System.out.println("Jaya Algorithm on CEC13 test functions");

		System.out.println("Enter Population Size");
		pop=Integer.parseInt(br.readLine());					
		System.out.println("Enter Number of Dimension(Design Variables= 2 or 5 or 10 or 30 or 50 or 100 ");
		dim=Integer.parseInt(br.readLine());   				

		System.out.println("Enter Number of Iterations (Generations)");
		itr_cnt=Integer.parseInt(br.readLine()); 			

		System.out.println("Enter function_Number");
		func_num=Integer.parseInt(br.readLine());  			 
		
		System.out.println("Do u want to see Comparison at each Iteration(Y/N)");
        char ch = (char) br.read(); 
        
        double rightLimit=100D; 	
		double leftLimit=-100D;	
		
		Xold = new double[pop*dim];
		Xnew = new double[pop*dim];
		
		fxold = new double[pop];
		fxnew = new double[pop];
         
		Dimension s2[]=new Dimension[pop];	
		
		for( i=0;i<dim*pop;i++)	
			{	
				double generatedDouble = leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);  
				Xold[i]=generatedDouble;		
			}
		 
		tf.test_func(Xold,fxold,dim,pop,func_num);
		
		int jforxold=0;
   		int cforobjects=0;
   		
   		double n[]=new double[dim];
   		Dimension s1[]=new Dimension[pop];
   		
   		for(int k=1;k<=pop*dim;k=k+dim)
   		{
   			for( i=0;i<dim;i++)
  	 		{
      				if(k<pop*dim)
      				{

	      				n[i]=Xold[jforxold];
	      				jforxold++;
      				}
   			}
   			s1[cforobjects]=new Dimension(n);
   			cforobjects++;
   		}

		LinkedHashMap map1=new LinkedHashMap();
		LinkedHashMap map2=new LinkedHashMap();
		LinkedHashMap map3=new LinkedHashMap();
		LinkedHashMap map4=new LinkedHashMap();
		ArrayList listout=new ArrayList();
		
		for( i=0;i<pop;i++)
		{
			map1.put(fxold[i],s1[i]);
		}

		ArrayList l=new ArrayList(map1.entrySet());
		Iterator it1=l.iterator();
		
		if(ch=='y' || ch=='Y')
		{
			while(it1.hasNext())
			{
			Map.Entry entry=(Map.Entry)it1.next();
			double d=(double)entry.getKey();
			Dimension s=(Dimension)entry.getValue();
			System.out.println("Xold ="+s.listofdimension+"   f(x)="+d);						
			listout.add(d);
			}
   		}
			
		if(ch=='y' || ch=='Y')
		{

			min=Collections.min(listout);	
			max=Collections.max(listout);
			
			best=(int)listout.indexOf(min);
	    	worst=(int)listout.indexOf(max);
		
	    	System.out.println(" best is ="+best);
	    	System.out.println(" worst is ="+worst);
               
			Iterator i8=l.iterator();
				while(i8.hasNext())
				{
					Map.Entry entry4=(Map.Entry)i8.next();
					Dimension s=(Dimension)entry4.getValue();
					double d=(double)entry4.getKey();
						if((double)entry4.getKey()==min)
							{
								Iterator i7=s.listofdimension.iterator();
								while(i7.hasNext())
									{
										double f=(double)i7.next();
										att.append(Double.toString(f));
										att.append("  ");
									}
							att.append(Double.toString(d));
					}
				}
				
				System.out.println(" Initial MINIMUM x1 x2 x3..... xn  and f(x) is = "+att);
		}
			att.delete(0,att.length());	
			listout.clear();
            System.out.println();

            
        do
		{
            if(ch=='y' || ch=='Y')
			{
            	System.out.println(" The current iteration is = "+curr_cnt);
            	System.out.println();}
				
            	for( i=0;i<pop;i++)				
					{
						for(int j=0;j<dim;j++)				
						{
							Xnew[dim*i+j] = Xold[dim*i+j] + (Math.random() * ( Xold[dim*best+j] - Math.abs(Xold[dim*i+j]))) - (Math.random() * (Xold[dim*worst+j] - Math.abs(Xold[dim*i+j]))); 
						}
					}	
				
			tf.test_func(Xnew,fxnew,dim,pop,func_num);		
			 
			jforxold=0;
	   		cforobjects=0;
	   		
	   		for(int k=1;k<=pop*dim;k=k+dim)
	   			{
  	 				for( i=0;i<dim;i++)
  	 					{
  	 						if(k<pop*dim)	
  	 							{
  	 								n[i]=Xnew[jforxold];
  	 								jforxold++;
  	 							}
  	 					}
  	 			
  	 				s2[cforobjects]=new Dimension(n);
  	 				cforobjects++;
	   			}

			for( i=0;i<pop;i++)
			{
				map2.put(fxnew[i],s2[i]);
			}
			
			ArrayList l2=new ArrayList(map2.entrySet());
			Iterator it2=l2.iterator();
			
			while(it2.hasNext())
				{
				Map.Entry entry=(Map.Entry)it2.next();
				double d=(double)entry.getKey();
				Dimension s=(Dimension)entry.getValue();
					
					if(ch=='y' || ch=='Y')			
						System.out.println("Xnew ="+s.listofdimension+"    f(x) ="+d);
				}
			
			ArrayList l1=new ArrayList(map1.entrySet());
			ArrayList llist=new ArrayList(map4.entrySet());
			Iterator i3;
			
			if(curr_cnt==1) //
 			{
				 i3=l1.iterator();
			}
			else
			{
				i3=llist.iterator();
				map1.clear();
			}
 
     		Iterator i4=l2.iterator();
			while(i3.hasNext() && i4.hasNext())
				{
					Map.Entry entry1=(Map.Entry)i3.next();
					Map.Entry entry2=(Map.Entry)i4.next();
					double fxold1=(double)entry1.getKey();
					double fxnew1=(double)entry2.getKey();
					
					if(fxnew1<fxold1)
						{
							map3.put(fxnew1,entry2.getValue());
						}
					else
					map3.put(fxold1,entry1.getValue());
			}

			if(ch=='y' || ch=='Y')
				{
					System.out.println("  --------------------------------------------------");
					System.out.println("  The comparision between CURRENT and PREVIOUS iteration at Iteration no="+curr_cnt+"   is\t");
					System.out.println();
				}
			
					ArrayList l3=new ArrayList(map3.entrySet());
					Iterator i5=l3.iterator();
					
					map1.clear();
					map4.clear();
			
					while(i5.hasNext())
						{
							Map.Entry entry3=(Map.Entry)i5.next();
							double dis=(double)entry3.getKey();
							Dimension splay=(Dimension)entry3.getValue();
				
							if(ch=='y' || ch=='Y')					
								System.out.println("Xold="+splay.listofdimension+"    f(x) ="+dis);
								map4.put(dis,splay);
								listout.add(dis);
						}
	
						min=Collections.min(listout);
						max=Collections.max(listout);
			
						best=(int)listout.indexOf(min);
						worst=(int)listout.indexOf(max);
			
						Iterator i6=l3.iterator();
						int iforfxnew=0;
						
						while(i6.hasNext())
							{
								Map.Entry entry4=(Map.Entry)i6.next();
								Dimension s=(Dimension)entry4.getValue();
								double d=(double)entry4.getKey();
				
								if(iforfxnew<pop)
									{
										fxnew[iforfxnew]=(double)d;
										iforfxnew++;
									}
			
								if((double)entry4.getKey()==min)
									{
										Iterator i7=s.listofdimension.iterator();
										
										while(i7.hasNext())
											{
												double f=(double)i7.next();
												att.append(Double.toString(f));
												att.append("  ");
											}
												att.append(Double.toString(d));
									}

							}
	
						map2.clear();
						map3.clear();
			
						for( i=0;i<pop;i++)
							{
								s2[i]=null;
								s1[i]=null;	
							}
			
						l1.clear();
						l2.clear();
						listout.clear();


							if(curr_cnt==itr_cnt-1)
								{
									att1.append(att);
								}
			

							if(ch=='y' || ch=='Y')
								System.out.println(" x1 x2 x3..... xn  and f(x) at iteration no= "+curr_cnt+"\t"+att);

							curr_cnt++;
							att.delete(0,att.length());	
							System.out.println();	
							
		}while(curr_cnt<=itr_cnt);

		long stopTime =System.currentTimeMillis(); 	
		long elapsedTime = stopTime - startTime; 
				
		System.out.println(" Final diamensions(x1 x2 x3........xn) and f(x) values are = "+att1);              
		System.out.println("\n Total time required(in milli seconds): "+elapsedTime); 	
		System.gc();
    	}
}


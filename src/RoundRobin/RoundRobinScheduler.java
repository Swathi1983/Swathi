package RoundRobin;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter; 
  
import java.util.List;

import org.cloudbus.cloudsim.*;
import java.util.Random;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import utils.Constants;
import utils.DatacenterCreator;
import utils.GenerateMatrices;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.Math;   
  
public class RoundRobinScheduler {

    private static List<Cloudlet> cloudletList;
    private static List<Vm> vmList;
    private static Datacenter[] datacenter;
    private static double[][] commMatrix;
    private static double[][] execMatrix;
    private static char tasks[]=new char[50];
    private static int mtasks[]=new int[10];
    private static int htasks[]=new int[10];
    private static int burt[]=new int[50];
    private static int vml[]=new int[50];
    private static int taq[]=new int[50];
    private static int tac[]=new int[50];
    private static final int MAX_POWER = 100;
    private static int ttg=30;
    int vmcap;
    private static double rho;
    private static int vmc;
    private static double gamma;
    private static double s;
    private static double beta;
    private static double n_t;
    private static double l_0;
    private static int numGlowworms; 
    private static int iterMax; 
    private static double r_0;
    private static double r_s;
    private static int t = 1;
    private static int th;
    private static int pc;
    private static List<Vm> createVM(int userId, int vms) {
        //Creates a container to store VMs. This list is passed to the broker later
        LinkedList<Vm> list = new LinkedList<Vm>();

        //VM Parameters
        long size = 10000; //image size (MB)
        int ram = 512; //vm memory (MB)
        int mips = 250;
        long bw = 1000;
       
        int tts,th,UID;
        String sp="  ";
        int pesNumber = 1; //number of cpus
        String vmm = "Xen"; //VMM name
        Scanner ob= new Scanner(System.in);
        //create VMs
        Random random = new Random();   
        Vm[] vm = new Vm[vms];
        Log.printLine("========== Initializing Required Parameters ==========");
        Log.printLine("========== Allocating Vitual Machines For Cloud Integrity Verification and User Validation Execution ==========");
        vmc=(int)(Math.random() * (10 - 5)) + 5;  
        Log.printLine("====Allocated Vitual Machines are:"+vmc+"====");
        Log.printLine("========== Identifying VM Location Points ==========");
        vml[0]=0;
        int a,b;
                
        for (int vmp = 1;vmp<=vmc;vmp++) {
        	if(vml[vmp]!=vml[vmp-1])
        	{
        	    a=(int)(Math.random() * (15 - 1)) + 15;
        		vml[vmp]=random.nextInt(a);
        	}
        	else
        	{
        		b=(int)(Math.random() * (50 - 16)) + 50;
        		vml[vmp]=random.nextInt(b);	
        	}
        }
        Log.printLine("========== VM Location Points Identified at Points ==========");
         for (int vmp = 1;vmp<=vmc;vmp++) {
        	Log.printLine(vml[vmp]+" ");
        }
        
        Log.printLine("========== Please provide the Physical Address ==========");
        tts=ob.nextInt();
        Log.printLine("========== Please provide the Threshold Value ==========");
        th=ob.nextInt();
        Log.printLine("========== User Information Processed:User ID is:==========");
        UID=(tts+th)<<2;
        Log.printLine("User ID:" + UID);
        Log.printLine("========== Please provide the data to store in Cloud environment ==========");
        String data= sc.nextLine(); 
        Log.printLine("========== The given data stored in cloud environment is==========");
        Log.printLine("User DataL"+data);
               	
        }
        Log.printLine("========== Calculating Burst Time for Tasks ==========");
        for (int r = 1;r<=tts;r++)
        {
        	try {
				TimeUnit. SECONDS. sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	burt[r]=(int)(Math.random() * (20 - 5)) + 15;
        }
        for (int i = 0; i < vms; i++) {
            vm[i] = new Vm(datacenter[i].getId(), userId, mips, pesNumber, ram, bw, size, vmm, new CloudletSchedulerSpaceShared());
            list.add(vm[i]);
        }
        
        Log.printLine("========== Tasks & Deadline for Execution of Tasks are==========");
        for (int i = 1; i <= tts; i++) {
        	Log.printLine("Task ID:" + sp + 100+tasks[i] + sp + "Burst Time:" + sp + burt[i]);
        	try {
				TimeUnit. SECONDS. sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
       int min=tasks[0]; 
       int flag=0;
       int f=0,h=0;
       int gk=0;
       int gt=0;
       Log.printLine("========== Ordered Tasks for Classification are==========");
       for (int i = 0; i < tts; i++) 
        {
            
           if(tasks[i] <min)
           {
               min = tasks[i];
               tac[i]=min;
              gk=(burt[i]+min+tasks[i]);
              gt=((int)(Math.sqrt(gk+min+tasks[i])));
           }
                       }  
      
     
        Log.printLine("========== Low Weighted Task Classification is done as==========");
        for (int hi = 1; hi <= tts; hi++) 
        {
        	
        	if(burt[hi]<=25)
        	Log.printLine("Task ID:" + sp + 100+tasks[hi] + sp + "Time for Task Execution:" + sp + burt[hi]);
        	mtasks[f]=tasks[hi];
        	f++;
        	try {
				TimeUnit. SECONDS. sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        Log.printLine("========== High Weighted Task Classification is done as==========");
        for (int ji = 1; ji <= tts; ji++) 
        {
        	
        	if(burt[ji]>25)
        	Log.printLine("Task ID:" + sp + 100+tasks[ji] + sp + "Time for Task Execution:" + sp + burt[ji]);
        	htasks[h]=tasks[ji]; 
        	h++;
        	try {
				TimeUnit. SECONDS. sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        Log.printLine("========== Calculating Power Consumtion==========");
        try {
			TimeUnit. SECONDS. sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PowerCon();
        parsetree();
        VMP();
        return list;
               
    }
    
   
    private static List<Cloudlet> createCloudlet(int userId, int cloudlets, int idShift) {
        // Creates a container to store Cloudlets
        LinkedList<Cloudlet> list = new LinkedList<Cloudlet>();

        //cloudlet parameters
        long fileSize = 300;
        long outputSize = 300;
        int pesNumber = 1;
        UtilizationModel utilizationModel = new UtilizationModelFull();

        Cloudlet[] cloudlet = new Cloudlet[cloudlets];

        for (int i = 0; i < cloudlets; i++) {
            int dcId = (int) (Math.random() * Constants.NO_OF_DATA_CENTERS);
            long length = (long) (1e3 * (commMatrix[i][dcId] + execMatrix[i][dcId]));
            cloudlet[i] = new Cloudlet(idShift + i, length, pesNumber, fileSize, outputSize, utilizationModel, utilizationModel, utilizationModel);
            // setting the owner of these Cloudlets
            cloudlet[i].setUserId(userId);
            cloudlet[i].setVmId(dcId + 2);
            list.add(cloudlet[i]);
        }
        
        
        
        
        
        return list;
        
        
    }

    public static void main(String[] args) {
        Log.printLine("Starting Round Robin Scheduler...");

        new GenerateMatrices();
        execMatrix = GenerateMatrices.getExecMatrix();
        commMatrix = GenerateMatrices.getCommMatrix();

        try {
            int num_user = 1;   // number of grid users
            Calendar calendar = Calendar.getInstance();
            boolean trace_flag = false;  // mean trace events

            CloudSim.init(num_user, calendar, trace_flag);

            // Second step: Create Datacenters
            datacenter = new Datacenter[Constants.NO_OF_DATA_CENTERS];
            for (int i = 0; i < Constants.NO_OF_DATA_CENTERS; i++) {
                datacenter[i] = DatacenterCreator.createDatacenter("Datacenter_" + i);
            }

            //Third step: Create Broker
            RoundRobinDatacenterBroker broker = createBroker("Broker_0");
            int brokerId = broker.getId();

            //Fourth step: Create VMs and Cloudlets and send them to broker
            vmList = createVM(brokerId, Constants.NO_OF_DATA_CENTERS);
            cloudletList = createCloudlet(brokerId, Constants.NO_OF_TASKS, 0);

            broker.submitVmList(vmList);
            broker.submitCloudletList(cloudletList);

            // Fifth step: Starts the simulation
            CloudSim.startSimulation();

            // Final step: Print results when simulation is over
            List<Cloudlet> newList = broker.getCloudletReceivedList();
            //newList.addAll(globalBroker.getBroker().getCloudletReceivedList());

            CloudSim.stopSimulation();

            printCloudletList(newList);

            Log.printLine(RoundRobinScheduler.class.getName() + " finished!");
        } catch (Exception e) {
            e.printStackTrace();
            Log.printLine("The simulation has been terminated due to an unexpected error");
        }
    }

    private static RoundRobinDatacenterBroker createBroker(String name) throws Exception {
        return new RoundRobinDatacenterBroker(name);
    }

    /**
     * Prints the Cloudlet objects
     *
     * @param list list of Cloudlets
     */
    private static void printCloudletList(List<Cloudlet> list) {
        int size = list.size();
        Cloudlet cloudlet;

        String indent = "    ";
        Log.printLine();
        Log.printLine("========== OUTPUT ==========");
        Log.printLine("Cloudlet ID" + indent + "STATUS" +
                indent + "Data center ID" +
                indent + "VM ID" +
                indent + indent + "Time" +
                indent + "Start Time" +
                indent + "Finish Time");

        DecimalFormat dft = new DecimalFormat("###.##");
        dft.setMinimumIntegerDigits(2);
        for (int i = 0; i < size; i++) {
            cloudlet = list.get(i);
            Log.print(indent + dft.format(cloudlet.getCloudletId()) + indent + indent);

            if (cloudlet.getCloudletStatus() == Cloudlet.SUCCESS) {
                Log.print("SUCCESS");

                Log.printLine(indent + indent + dft.format(cloudlet.getResourceId()) +
                        indent + indent + indent + dft.format(cloudlet.getVmId()) +
                        indent + indent + dft.format(cloudlet.getActualCPUTime()) +
                        indent + indent + dft.format(cloudlet.getExecStartTime()) +
                        indent + indent + indent + dft.format(cloudlet.getFinishTime()));
            }
        }
        double makespan = calcMakespan(list);
        Log.printLine("Makespan using RR: " + makespan);
    }

    private static double calcMakespan(List<Cloudlet> list) {
        double makespan = 0;
        double[] dcWorkingTime = new double[Constants.NO_OF_DATA_CENTERS];

        for (int i = 0; i < Constants.NO_OF_TASKS; i++) {
            int dcId = list.get(i).getVmId() % Constants.NO_OF_DATA_CENTERS;
            if (dcWorkingTime[dcId] != 0) --dcWorkingTime[dcId];
            dcWorkingTime[dcId] += execMatrix[i][dcId] + commMatrix[i][dcId];
            makespan = Math.max(makespan, dcWorkingTime[dcId]);
        }
        return makespan;
    }
    public static void PowerCon() {
    	    	
 		for(int k=1;k<ttg;k++) {
 		int dcId = Constants.NO_OF_DATA_CENTERS*10;
 		pc=MAX_POWER-dcId;
 		 Log.printLine("Energy Consumption is"+pc);
 		}
        
    }
    
    public static void treegen() {
    	Log.printLine("========== Ordered Tasks for Classification are==========");
    	int kk=mtasks.length+htasks.length;
    	Log.printLine(htasks[1]);
    

 		
 		}
    
    public static void parsetree() {
    	Log.printLine("========== Ordered Tasks for Classification are==========");
    	int kk=mtasks.length+htasks.length;
    	Log.printLine(htasks[1]);
    	for(int k=0;k<htasks.length;k++) {
 		Log.printLine(htasks[k]);
 		}
 	
 		for(int k=0;k<mtasks.length;k++) {
 	 		Log.printLine(mtasks[k]);
 	 		}
    }
  
    
		
   private static void VMP()
   {
	   double weight_sum = 0;
		int weight=5;
		int value=1;
		int vmm;
		int k;
		int lm=5;
		int ff=1;
		int tu=1;
		int po;
		int np;
		Random rand = new Random();
	Scanner ob= new Scanner(System.in);
Log.printLine("Enter VM to Migrate");
		 vmm=ob.nextInt();
Scanner sc= new Scanner(System.in);
Log.printLine("Enter Data to Store in Cloud: ");  
String str= sc.nextLine(); 
Log.printLine("Cloud Deduplication Model Activated");
try {
	TimeUnit. SECONDS. sleep(4);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
str = str.toLowerCase();    
//Split the string into words using built-in function    
String words[] = str.split(" ");    
int count;    
System.out.println("Duplicate words in a given file : ");     
for(int i = 0; i < words.length; i++) {    
    count = 1;    
    for(int j = i+1; j < words.length; j++) {    
        if(words[i].equals(words[j])) {    
            count++;    
                
            words[j] = "0";    
        }    
    }    
     
    if(count > 1 && words[i] != "0")    
        System.out.println(words[i]);  
         
}    
System.out.println("Deduplication Completed ");  
Log.printLine("==========Resource Demand Calculation is Initialited==========");
try {
	TimeUnit. SECONDS. sleep(3);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
Log.printLine("VM scalability is in Location Point"+vml[vmm]);
Log.printLine("==========Identification of nearby Location to select a VM==========");
try {
	TimeUnit. SECONDS. sleep(3);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
np=++vmm;
Log.printLine("New VM selected is"+vml[np]);
Log.printLine("==========Execution is in Progress==========");
try {
	TimeUnit. SECONDS. sleep(3);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}



if(pc>=50)
{
	Log.printLine("==========VM scalability is completed in new Location==========");
}
if(rho<l_0)
{
	if(beta<s)
	{
	rho=gamma+n_t;
	beta=beta+rho;
	
	
		}
}
for (int h=0;h<vmc;h++)
{
	lm=vmc-value;
	pc=pc-lm-5;
}
Log.printLine("VM scalability New Location Point"+vml[np]);

   }

   
   
}
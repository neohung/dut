package neo.dut.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import neo.dut.UI.singleGroup;

import org.eclipse.swt.widgets.Display;

public class dutTest  extends Thread {
	private singleGroup sg;
	private String scriptName;
	public dutTest(singleGroup sg) {
		this.sg = sg;
		this.scriptName = "./a.sh";
	}
	public void run() {
		dutInit();
		dutPreRun();
		dutRun();
		dutPostRun();
		dutEnd();
	}
	private void dutInit(){
	}
	private void dutPreRun(){		
	}
	private void dutRun(){
		runScriptFile();
		/*
		if (!sg.goNextStage()) System.out.println("To end!");
		if (!sg.goNextStage()) System.out.println("To end!");
		increaseProgressBar();
		if (!sg.goNextStage()) System.out.println("To end!");
		if (!sg.goNextStage()) System.out.println("To end!");
		if (!sg.goNextStage()) System.out.println("To end!");
		
		if (!sg.goNextStage()) System.out.println("To end!");
		*/
	}
	private void dutPostRun(){		
	}
	private void dutEnd(){
					sg.showPass();
	}
	private void increaseProgressBar(){
		 Display.getDefault().asyncExec(new Runnable() {
			 public void run() {
				 sg.progressBar.setSelection(sg.progressBar.getSelection() + 100);
			 }
		 });	
	}
	private void runScriptFile(){
		//try {
		 	ProcessBuilder builder = new ProcessBuilder();  
	        builder.command(this.scriptName);  
	        final Process p;
			try {
				p = builder.start();
				new Thread() {  
	                public void run() {  
	                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));  
	                    try {  
	                        String lineB = null;  
	  
	                        while ((lineB = br.readLine()) != null) {  
	                            if (lineB != null) System.out.println(lineB);  
	  
	                        }  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }.start();  
	            
	            new Thread() {  
	                public void run() {  
	                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));  
	                    try {  
	                        String lineB = null;  
	  
	                        while ((lineB = br.readLine()) != null) {  
	                            if (lineB != null) System.out.println(lineB);  
	  
	                        }  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }.start();
	              
	            p.waitFor();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//Process p = Runtime.getRuntime().exec(this.scriptName);
			//Process p = Runtime.getRuntime().exec();
			//BufferedWriter buffOut = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
            //BufferedReader buffIn = new BufferedReader(new InputStreamReader(p.getInputStream()));
            //BufferedReader buffErrIn = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            /*
            //Pipe args
            buffOut.write("/ \n");
			buffOut.flush();
			*/
			//Grep message
            //p.waitFor();
 catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
                    
          /*  
			String line = "";
            while((line = buffIn.readLine()) != null){
            	String err = "";
            	if((err = buffErrIn.readLine()) != null) {
            		if (err.contains("sudo:"))  {
            		//	buffOut.write("000000");
            		//	buffOut.newLine();
            		//	buffOut.flush();
            		}
            	//	System.out.println(err);
            	}
                    System.out.println(line);
            }
            buffErrIn.close();
            buffOut.close();
            buffIn.close();
           	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
           	} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/	
	}
}

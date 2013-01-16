package neo.dut.thread;

import neo.dut.UI.singleGroup;

import org.eclipse.swt.widgets.Display;

public class dutTest  extends Thread {
	private singleGroup sg;
	public dutTest(singleGroup sg) {
		this.sg = sg;
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
		
		if (!sg.goNextStage()) System.out.println("To end!");
		/*
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
}

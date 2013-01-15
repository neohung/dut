package neo.dut.UI;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class UI {
	//final static Display display = Display.getDefault();
	//final static Shell shell = new Shell(SWT.MIN|SWT.MAX|SWT.CLOSE);
	public final Display display = new Display();
	public final Shell shell = new Shell(display);
	public Button startButton;
	public singleGroup[] group;

	public void shellInit(String title){
		shell.layout(true, true);
	    shell.setMaximized(true);
		shell.setFullScreen(true);
		shell.setText(title);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		shell.setLayout(gridLayout);  
	}
	
	public void createProgressItems(int deviceSize,int stagesize){
		group = new singleGroup[deviceSize];
		for (int i=1;i <= deviceSize;i++){
			group[i-1] = new singleGroup(shell, i, stagesize);
			group[i-1].progressBar.setMinimum(0);
			group[i-1].progressBar.setMaximum(100);
		}
	}
	public void createExitItem(Shell s){
		Button exitButton = new Button(s, SWT.PUSH);
		exitButton.setText("Exit");
		exitButton.setFont(new Font(s.getDisplay(),"Arial", 28, SWT.NORMAL));
		exitButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true,150,100));
		exitButton.addSelectionListener( new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
				 System. out.println("Exit!" );
		         shell.dispose();
		     }
		 });
	}
	public void createStartItem(Composite c, SelectionAdapter sa){
		startButton = new Button(c, SWT.PUSH);
		startButton.setText("Start");
		startButton.setFont(new Font(c.getDisplay(),"Arial", 28, SWT.NORMAL));
		startButton.setLayoutData(new GridData(150,50));
		//"sa" should be defined in the main function
		startButton.addSelectionListener(sa);
	}
}

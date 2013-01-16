package neo.dut.main;

import neo.dut.UI.UI;
import neo.dut.thread.dutTest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class test {
	static int deviceSize = 2;
	static int stagesize = 6;
	public static void main(String[] args) {
		final test mainTest = new test();
		final UI ui = new UI();
		ui.shellInit("DUT");
		ui.createProgressItems(deviceSize, stagesize);
		//----for test-----
		Composite buttonGroup = new  Composite(ui.shell,SWT.NONE);
		buttonGroup.setLayout(new GridLayout(6,false));
		//Create buttonPressFunction
		SelectionAdapter buttonPressFunction = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				mainTest.createThreadProcess(ui);
			     }
			 };
		//Create group Item and register buttonPressFunction
		ui.createStartItem(buttonGroup,buttonPressFunction);
		//----for test-----
		ui.createExitItem(ui.shell);
		ui.shell.open();
		while (!ui.shell.isDisposed()) {
			if (!ui.display.readAndDispatch()) ui.display.sleep ();
		}
		ui.display.dispose ();
	}
	public void createThreadProcess(UI ui){
		ui.startButton.setEnabled( false );
		for (int i=0;i < deviceSize;i++){
			new dutTest(ui.group[i]).start();
		}
	}
}

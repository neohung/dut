package neo.dut.UI;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Text;

public class singleGroup  extends Composite{
	ImageLoader loader = new ImageLoader();
	ImageData[] passImageData = loader.load(UI.class.getResourceAsStream("/images/pass.png"));
	ImageData[] failImageData = loader.load(UI.class.getResourceAsStream("/images/fail.png"));
	Label deviceName = null;
	public ProgressBar progressBar = null;
	Text barcode = null;
	//Button button = null;
	Label passImageLabel = null;
	Label failImageLabel = null;
	Group group = null;
	public int stageIndex = 1;
	int stageSize;
	List <Button> stages = new LinkedList<Button>();
	public singleGroup(Composite c, final int index, int size) {
		super(c, SWT.BORDER);
		stageSize = size;
		//Label,Progressbar,barcode text,two image=5
		int sizePerRow = 5 + size;
		this.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        this.setLayout(new GridLayout(sizePerRow,false));
        //Create Label Control
		Label label = new Label(this, SWT.NONE);
		//label.setLocation(5, 10);
		label.setFont(new Font(c.getDisplay(),"Arial", 28, SWT.BOLD ));
		label.setText("Device"+index+":");
		//label.pack();
		
		//Create stage Button
		for (int i=0;i<stageSize;i++){
		 final Button stageButton = new Button(this, SWT.TOGGLE);
		 
		 stageButton.setText(String.valueOf(i+1));
		 //button1.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_CYAN));  
		 stageButton.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));  
		 stageButton.setSelection(false);
		 stageButton.addSelectionListener(new SelectionAdapter() {
			 public void widgetSelected(SelectionEvent e) {
				 stageButton.setSelection(false);
			 }
		 });
		 stageButton.setLayoutData(new GridData(50,45));
		 stages.add(stageButton);
		}
		//Create ProgressBar Control 
		progressBar = new ProgressBar(this, SWT.HORIZONTAL);
		 //progressBar.setMaximum( COUNT );
		progressBar.setLayoutData(new GridData(250,50));
		
		Text barcode = new Text(this, SWT.SINGLE|SWT.BORDER);
		 barcode.setTextLimit(8);
		 //Set Font Size
		 Font initialFont = barcode.getFont();
		 FontData[] fontData = initialFont.getFontData();
		 for (int i = 0; i < fontData.length; i++) {
			 fontData[i].setHeight(24);
		 }
		 Font newFont = new Font(c.getDisplay(), fontData);
		 barcode.setFont(newFont);
		 //Set Border Size  
		 barcode.setLayoutData(new GridData(150,45));
		 //Create Pass & Fail flags 
		 int imageWidth = 50;
		 Image image = new Image(null, passImageData[0].scaledTo(imageWidth, 50));		  
		 passImageLabel = new Label(this, SWT.NONE);
		 passImageLabel.setImage(image);
		 passImageLabel.setLayoutData(new GridData(imageWidth,50));
		 passImageLabel.setVisible(false);	
		 image = new Image(null, failImageData[0].scaledTo(imageWidth, 50));		  
		 failImageLabel = new Label(this, SWT.NONE);
		 failImageLabel.setImage(image);
		 failImageLabel.setLayoutData(new GridData(imageWidth,50));
		 failImageLabel.setVisible(false);
		 //create statu bar
		 Label statu = new Label(this, SWT.SINGLE);
		 //Set Font Size
		 Font statuFont = statu.getFont();
		 FontData[] statuFontData = statuFont.getFontData();
		 for (int i = 0; i < statuFontData.length; i++) {
			 statuFontData[i].setHeight(10);
		 }
		 Font statuNewFont = new Font(c.getDisplay(), statuFontData);
		 statu.setFont(statuNewFont);
		 //Set Border Size  
		 statu.setLayoutData(new GridData(10,12));
		 statu.setText("This is test");
			 
	}
	public void showPass(){
		 Display.getDefault().asyncExec(new Runnable() {
			 public void run() {					
				 passImageLabel.setVisible(true);	
			 }
		 });
	}
	public void showFail(){
		 Display.getDefault().asyncExec(new Runnable() {
			 public void run() {					
				 failImageLabel.setVisible(true);
			 }
		 });
	}
	public boolean goNextStage(){
		if (stageIndex >= stageSize) return false;
		 Display.getDefault().asyncExec(new Runnable() {
			 public void run() {	
				 stages.get(stageIndex - 1).setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));  
			 }
		  });
		 stageIndex++;
		 Display.getDefault().asyncExec(new Runnable() {
			 public void run() {	
		 stages.get(stageIndex - 1).setBackground(Display.getDefault().getSystemColor(SWT.COLOR_CYAN)); 
			 }
		  });
		return true;
	}

}

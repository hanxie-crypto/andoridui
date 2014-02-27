package cn.example.ui3;

import java.util.Timer;
import java.util.TimerTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class MainActivity extends Activity {
	
	private boolean isStartShouming = true;
	private boolean isStartPm = true;
	private boolean isStartKongMoshi = true;
	private boolean isStartKongDeng = true;
	private boolean isStartKongFeng = true;
	private boolean isStartKongJingdian = true;
	private boolean isStartStart = true;
	private int numberPm;
//	private int buffPm;
	private String buff = null;
	private String pm = null;
	private String Shouming = null;
	private int buffDeng;
	private int Deng;
	private int buffFeng;
	private int Feng;
	private int buffMoshi;
	private int Moshi;
	private int buffJingdian;
	private int Jingdian;
	private int buffShouming;
	private int buffTable;
	
	
	private int MoshiBuff;
	private int FengBuff;
	private int JingdianBuff;
	
	private String methodName = null;
	
	private String endPoint = null;
	private String soapAction = null;
	private String nameSpace = null;
	
	private static Handler mHandler = null;
	private Timer mTimer = null;
	private TimerTask mTimerTask = null;
	
	public void getShouming(){
		methodName = "get-feltrate-life";
		endPoint = "http://192.168.1.232:9000"; 
		soapAction = "http://192.168.1.232:9000/get-feltrate-life";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("contolPm",buffShouming);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);
			Object result = (Object)envelope.getResponse();
			Shouming = result+"";
		}catch(Exception e){
		}
	}
			
	public void getTable(){

		methodName = "get-cof-table";
		endPoint = "http://192.168.1.232:9000"; 
		soapAction = "http://192.168.1.232:9000/get-cof-table";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("contolPm",buffTable);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);
			Object result = (Object)envelope.getResponse();
			buff = result+"";
			
			
		}catch(Exception e){
		}
	}
	
	public void getPm(){
		methodName = "GetSensorPM";
		endPoint = "http://192.168.1.232:9000"; 
		soapAction = "http://192.168.1.232:9000/GetSensorPM";
		nameSpace = "urn:web";
		//创建一个SoapObject对象
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		//添加GetSensorPM方法的参数
		rpc.addProperty("contolPm",1);
		//设置SOAP请求信息，获取序列化的envelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);
			Object result = (Object)envelope.getResponse();
//			numberPm = (int)(Integer) result;
			pm = result+"";
			Log.e("PM22.5 = ", pm);
		}catch(Exception e){
			pm = "出错";
			e.printStackTrace();
		}
	}
	
	public void kongMoshi(){
		
		methodName = "loop-mode-command";
		endPoint = "http://192.168.1.232:9000"; 	//endPoint		
		soapAction = "http://192.168.1.232:9000/loop-mode-command";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("command",buffMoshi);  //开关传参
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);//调用webservice
			Object result = (Object)envelope.getResponse();
			Moshi = (int)(Integer) result;	
		}catch(Exception e){
		}
		if(Moshi==1){
			isStartKongMoshi=!isStartKongMoshi;
		}
	}
 	
	public void kongDeng(){
		
		methodName = "lamp-mode-command";
		endPoint = "http://192.168.1.232:9000"; 	//endPoint		
		soapAction = "http://192.168.1.232:9000/lamp-mode-command";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("command",buffDeng);  //开关传参
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);//调用webservice
			Object result = (Object)envelope.getResponse();
			Deng = (int)(Integer) result;	
		}catch(Exception e){
		}
		
	}
	
	public void kongFeng(){
		
		methodName = "fan-mode-command";
		endPoint = "http://192.168.1.232:9000"; 	//endPoint		
		soapAction = "http://192.168.1.232:9000/fan-mode-command";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("command",buffFeng);  //开关传参
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);//调用webservice
			Object result = (Object)envelope.getResponse();
			Feng = (int)(Integer) result;	
		}catch(Exception e){
		}
		if(Feng==1){
			isStartKongFeng = !isStartKongFeng;
		}
		
	}
	
	public void kongJingdian(){
		methodName = "elc-field-mode-command";
		endPoint = "http://192.168.1.232:9000"; 	//endPoint		
		soapAction = "http://192.168.1.232:9000/elc-field-mode-command";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("command",buffJingdian);  //开关传参
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);//调用webservice
			Object result = (Object)envelope.getResponse();
			Jingdian = (int)(Integer) result;	
		}catch(Exception e){
		}
	}
	
	public void StarStart(){
			
		kongDeng();
		kongFeng();
		kongJingdian();
		
	}
	
	
	
	public void getMoshiBuff(){

		methodName = "get-loop-status";
		endPoint = "http://192.168.1.232:9000"; 
		soapAction = "http://192.168.1.232:9000/get-loop-status";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("i-loop",1);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);
			Object result = (Object)envelope.getResponse();
			MoshiBuff = (int)(Integer) result;
		}catch(Exception e){
		}
	}
	
	public void getFengBuff(){

		methodName = "get-fans-status";
		endPoint = "http://192.168.1.232:9000"; 
		soapAction = "http://192.168.1.232:9000/get-fans-status";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("i-fan",1);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);
			Object result = (Object)envelope.getResponse();
			FengBuff = (int)(Integer) result;
		}catch(Exception e){
		}
	}
	
	public void getJingdianBuff(){

		methodName = "get-elc-field-status";
		endPoint = "http://192.168.1.232:9000"; 
		soapAction = "http://192.168.1.232:9000/get-elc-field-status";
		nameSpace = "urn:web";
		SoapObject rpc = new SoapObject(nameSpace, methodName);  
		rpc.addProperty("i-elce-field",1);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = rpc;
		envelope.dotNet = false; 
		HttpTransportSE transport = new HttpTransportSE(endPoint); 
		transport.debug=true;
		
		try{
			transport.call(soapAction, envelope);
			Object result = (Object)envelope.getResponse();
			JingdianBuff = (int)(Integer) result;
		}catch(Exception e){
		}
	}
	
	
	
	
	public class MyRunnableGetMoshiBuff implements Runnable{
		public void run() {
			getMoshiBuff();
		}
	}
	
	public class MyRunnableGetFengBuff implements Runnable{
		public void run() {
			getFengBuff();
		}
	}
	
	public class MyRunnableGetJingdianBuff implements Runnable{
		public void run() {
			getJingdianBuff();
		}
	}
	
	
	
	
		
	public class MyRunnablePm implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			/*
			 * 使用Timer去实现不停的获取Pm的值
			 */
			mTimer = new Timer();
			mTimerTask = new TimerTask(){
				public void run() {
					if(isStartPm){
						getPm();
						Message msg = new Message();
    					msg.what = 1 ;
    					mHandler.sendMessage(msg);
					}else{
						pm=null;
						Message msg = new Message();
    					msg.what = 1 ;
    					mHandler.sendMessage(msg);
					}
				}
			};
			mTimer.schedule(mTimerTask,50,1000);	
		}	
	}
	
	public class MyRunnableMoshi implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			kongMoshi();
		}	
	}
	
	
	
	
	public class MyRunnableDeng implements Runnable{
		@Override
		public void run() {
			
			kongDeng();
		}	
	}
	
	public class MyRunnableFeng implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			kongFeng();
		}	
	}
	
	public class MyRunnableJingdian implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			kongJingdian();
		}	
	}
	
	public class MyRunnableShouming implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			mTimer = new Timer();
			mTimerTask = new TimerTask(){
				public void run() {
					if(!isStartShouming){
						getShouming();
						Message msg = new Message();
						msg.what = 2 ;
						mHandler.sendMessage(msg);
					}else{
						Shouming = null;
						Message msg = new Message();
						msg.what = 2 ;
						mHandler.sendMessage(msg);
					}
				}
			};
			mTimer.schedule(mTimerTask,50,1000);
		}
		
	}
	

	
	public void ThreadGetMoshiBuff(){
		MyRunnableGetMoshiBuff one = new MyRunnableGetMoshiBuff();
		Thread T1 = new Thread(one);
		T1.start();
	}

	
	public void ThreadGetFengBuff(){
		MyRunnableGetFengBuff one = new MyRunnableGetFengBuff();
		Thread T1 = new Thread(one);
		T1.start();
	}
	
	
	public void ThreadGetJingdianBuff(){
		MyRunnableGetJingdianBuff one = new MyRunnableGetJingdianBuff();
		Thread T1 = new Thread(one);
		T1.start();
	}
	
	
	//开线程获取pm的值，并在界面中显示
	public void ThreadPm(){
		MyRunnablePm one = new MyRunnablePm();
		Thread ThreadPm = new Thread(one);
		ThreadPm.start();
	}
	
	public void ThreadMoshi(){
		MyRunnableMoshi one = new MyRunnableMoshi();
		Thread ThreadMoshi = new Thread(one);
		ThreadMoshi.start();
	}
	
	public void ThreadDeng(){
		MyRunnableDeng one = new MyRunnableDeng();
		Thread ThreadDeng = new Thread(one);
		ThreadDeng.start();
	}
	
	public void ThreadFeng(){
		MyRunnableFeng one = new MyRunnableFeng();
		Thread ThreadFeng = new Thread(one);
		ThreadFeng.start();
	}
	
	public void ThreadJingdian(){
		MyRunnableJingdian one = new MyRunnableJingdian();
		Thread ThreadJingdian = new Thread(one);
		ThreadJingdian.start();
	}
	
	public void ThreadShouming(){

		MyRunnableShouming one = new MyRunnableShouming();
		Thread ThreadShouming = new Thread(one);
		ThreadShouming.start();
	}
	
	
	public void PanDuan(){
		if(MoshiBuff==1){
			TextViewMoshiBuff.setText("外循环");
		}else{
			TextViewMoshiBuff.setText("内循环");
		}
		if(FengBuff==1){
			TextViewFengBuff.setText("风机转动中");
		}else{
			TextViewFengBuff.setText("风机停止");
		}
		if(buffJingdian == 1){
			TextViewJingdianBuff.setText("除尘箱开启");
		}else{
			TextViewJingdianBuff.setText("除尘箱关闭");
		}
	}
	
	
	private Button buttonPm;
	private Button buttonMoshi;
	private Button buttonFeng;
	private Button buttonJingdian;
	private Button buttonStarStart;
	private Button buttonShouming;
	private TextView TextViewPM;
	private TextView TextViewShouming;
	private TextView TextViewMoshiBuff;
	private TextView TextViewFengBuff;
	private TextView TextViewJingdianBuff;
	
//	private TextView TextViewSE;
	
	public void shua(){
		mHandler = new Handler(){
			public void handleMessage(Message msg){
				switch (msg.what) {
				//�ж�msg=1ʱִ��ProcessTimer�������
				case 1:	
					TextViewPM.setText(pm);
					break;
				case 2:
					TextViewShouming.setText(Shouming);
					break;
				}	
				super.handleMessage(msg);
			}
		};
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buttonPm = (Button) findViewById(R.id.button1);
		buttonMoshi = (Button) findViewById(R.id.button4);
		buttonFeng = (Button) findViewById(R.id.button6);
		buttonJingdian = (Button) findViewById(R.id.button8);
		buttonStarStart = (Button) findViewById(R.id.button9);
		buttonShouming = (Button) findViewById(R.id.button2);
		
		TextViewPM = (TextView) findViewById(R.id.textView2);
		TextViewShouming = (TextView) findViewById(R.id.textView9);
		TextViewMoshiBuff = (TextView) findViewById(R.id.textView5);
		TextViewFengBuff = (TextView) findViewById(R.id.textView4);
		TextViewJingdianBuff = (TextView) findViewById(R.id.textView7);
		
		
//		TextViewSE = (TextView) findViewById(R.id.textView4);
		
//		ThreadShouming();
		ThreadPm();
		
		
		ThreadGetMoshiBuff();
		ThreadGetFengBuff();
		ThreadGetJingdianBuff();
		
		
		shua();
		
		buttonPm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				isStartPm = !isStartPm;
				if(isStartPm){
					pm = null;
					buttonPm.setText("关");
				}else{
					pm = null;
					buttonPm.setText("开");
				}
			}
		});
		
		buttonMoshi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				ThreadGetMoshiBuff();
				if(MoshiBuff==1){
					buffMoshi = 0;
					TextViewMoshiBuff.setText(MoshiBuff);
				}else{
					buffMoshi = 1;
					TextViewMoshiBuff.setText("");
				}
				ThreadMoshi();
				
			}
		});
		
		buttonFeng.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				ThreadGetFengBuff();
				if(FengBuff==1){
					buffFeng = 0;
					TextViewFengBuff.setText(FengBuff);
				}else{
					buffFeng = 1;
					TextViewFengBuff.setText("");
				}
				ThreadFeng();
				
			}
		});
		
		buttonJingdian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				ThreadGetJingdianBuff();
				if(buffJingdian == 1){
					JingdianBuff = 0;
					TextViewJingdianBuff.setText(buffJingdian);
				}else{
					JingdianBuff = 1;
					TextViewJingdianBuff.setText("");
				}
				ThreadJingdian();
			}
		});
		
		buttonStarStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				
			}
		});
		
		buttonShouming.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v){
				isStartShouming = !isStartShouming;
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

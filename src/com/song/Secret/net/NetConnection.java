package com.song.Secret.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.song.Secret.Config;

import android.os.AsyncTask;
//�������ӵ�ͨ����
public class NetConnection {
	/**
	 * 
	 * @param url:ͨ�ŵ�ַ
	 * @param method:����ʽ
	 * @param successCallback:
	 * @param failCallback
	 * @param kvs
	 */
	public NetConnection(final String url,final HttpMethod method,final SuccessCallback successCallback,final FailCallback failCallback,final String...kvs){
		
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				StringBuffer paromsStr=new StringBuffer();
				for (int i = 0; i < kvs.length; i+=2) {
					paromsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
				}
				try {
					URLConnection uc;
					switch (method) {
					case POST:
						//POSTͨ��
						uc=new URL(url).openConnection();
						uc.setDoOutput(true);
						BufferedWriter bW=new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(),Config.CHARSET));
						bW.write(paromsStr.toString());
						break;
					default:
						//GETͨ��
						uc=new URL(url+"?"+paromsStr.toString()).openConnection();
						break;
						
						
					}
					System.out.println("Request url:"+uc.getURL());
					System.out.println("Request data:"+paromsStr);
					//��ȡ���������صĽ��
					BufferedReader br=new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
					String line=null;
					StringBuffer result=new StringBuffer();
					while ((line=br.readLine())!=null) {
						result.append(line);
					}
					//����ֵ,�������
					return result.toString();
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			@Override
			protected void onPostExecute(String result) {
				if (result!=null) {
					//�жϼ������Ƿ����
					if (successCallback!=null) {
						successCallback.onSucccss(result);
					}
				}else {
					//�жϼ������Ƿ����
					if (failCallback!=null) {
						failCallback.onFail();
					}
				}
				super.onPostExecute(result);
			}
		};
	}
	public static interface SuccessCallback{
		void onSucccss(String result);
	}
	public static interface FailCallback{
		void onFail();
	}

}

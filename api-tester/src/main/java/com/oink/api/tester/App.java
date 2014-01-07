package com.oink.api.tester;

import java.util.logging.Logger;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.tempuri.PingHeadersDocument;
import org.tempuri.PingHeadersDocument.PingHeaders;
import org.tempuri.TestCallBackConnectionDocument;
import org.tempuri.TestCallBackConnectionDocument.TestCallBackConnection;
import org.tempuri.TestCallBackConnectionResponseDocument;
import org.tempuri.TestCallBackConnectionResponseDocument.TestCallBackConnectionResponse;

import com.oink.api.TransactionServiceStub;


/**
 * Hello world!
 *
 */
public class App 
{
	private static Log log = LogFactory.getLog(App.class);
	 
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        try{
        	HttpTransportProperties.Authenticator basicAuthentication = new HttpTransportProperties.Authenticator();
        	basicAuthentication.setUsername("vpintegation@virtualpiggy.com");
        	basicAuthentication.setPassword("potter2012");
        	
        	TransactionServiceStub stub =
                new TransactionServiceStub
                ("https://integration.virtualpiggy.com/services/TransactionService.svc?singleWsdl");

        	//stub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);
        	//stub._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.AUTHENTICATE, basicAuthentication);
        	OMFactory fac = OMAbstractFactory.getOMFactory();
        	OMNamespace omNs = fac.createOMNamespace("vp", "ns2");
        	SOAPHeaderBlock merchantIdentifier = OMAbstractFactory.getSOAP11Factory().createSOAPHeaderBlock("MerchantIdentifier", omNs);
        	merchantIdentifier.setText("13481e49-f5f5-43cb-8ae4-c94cd9647bfe");
        	SOAPHeaderBlock apiKey = OMAbstractFactory.getSOAP11Factory().createSOAPHeaderBlock("APIkey", omNs);
        	apiKey.setText("fakewear123");
        	stub._getServiceClient().addHeader(merchantIdentifier);
        	stub._getServiceClient().addHeader(apiKey);
        
            doGet(stub);
            

        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }
    
    public static void doGet(TransactionServiceStub stub){
        try{
            
        	TestCallBackConnectionDocument req = TestCallBackConnectionDocument.Factory.newInstance();
        	req.addNewTestCallBackConnection();
        	TestCallBackConnectionResponseDocument resp = stub.TestCallBackConnection(req);
        	
        	log.info("request: " + req);
        	log.info("response: " + resp);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("\n\n\n");
        }
    }
}

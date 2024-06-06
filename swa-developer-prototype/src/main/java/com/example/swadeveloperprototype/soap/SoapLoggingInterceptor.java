package com.example.swadeveloperprototype.soap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPElement;
import jakarta.xml.soap.Node;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class SoapLoggingInterceptor implements EndpointInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SoapLoggingInterceptor.class);

    @Override
    public boolean handleRequest(MessageContext messageContext, Object endpoint) {
        logSoapMessage("Request", messageContext);
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext, Object endpoint) {
        logSoapMessage("Response", messageContext);
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext, Object endpoint) {
        logSoapMessage("Fault", messageContext);
        return true;
    }

    private void logSoapMessage(String type, MessageContext messageContext) {
        try {
            SaajSoapMessage soapMessage = (SaajSoapMessage) messageContext.getRequest();
            jakarta.xml.soap.SOAPMessage saajMessage = soapMessage.getSaajMessage();
            SOAPBody body = saajMessage.getSOAPBody();
            SOAPElement operationElement = (SOAPElement) body.getFirstChild();
            String operationName = operationElement.getLocalName();
            String params = extractParameters(operationElement);
            logger.info("- {} {} {}",operationName, type ,params);
        } catch (Exception e) {
            logger.error("Error processing SOAP {} log: ", type, e);
        }
    }

    private String extractParameters(SOAPElement operationElement) {
        StringBuilder params = new StringBuilder();
        Iterator<Node> it = operationElement.getChildElements();
        while (it.hasNext()) {
            Node node = it.next();
            if (node instanceof SOAPElement) {
                SOAPElement element = (SOAPElement) node;
                if ("id".equals(element.getLocalName()) && element.getValue() != null) {
                    if (params.length() > 0) params.append(", ");
                    params.append("id=").append(element.getValue());
                }
            }
        }
        return params.toString();
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) {
        // No implementation needed
    }
}

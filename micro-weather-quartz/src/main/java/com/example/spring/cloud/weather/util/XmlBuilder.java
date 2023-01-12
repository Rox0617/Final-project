package com.example.spring.cloud.weather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

public class XmlBuilder {

	//Converts the xml to the specified POJO
	public static Object xmlStrToObject(Class<?> clazz,String xmlStr) throws Exception{

		Object xmlObject=null;
		Reader reader=null;
		JAXBContext context=JAXBContext.newInstance(clazz);

		//xml to object interface
		Unmarshaller unmarshaller=context.createUnmarshaller();

		reader=new StringReader(xmlStr);
		xmlObject=unmarshaller.unmarshal(reader);

		if(reader!=null){
			reader.close();
		}

		return xmlObject;
	}

}

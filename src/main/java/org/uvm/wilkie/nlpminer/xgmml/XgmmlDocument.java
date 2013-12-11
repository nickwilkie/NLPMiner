package org.uvm.wilkie.nlpminer.xgmml;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.uvm.wilkie.nlpminer.xgmml.bind.GraphicGraph;
import org.uvm.wilkie.nlpminer.xgmml.bind.ObjectFactory;

public class XgmmlDocument {
    JAXBContext jc;
	
	GraphicGraph graph;
	ObjectFactory factory;
	
	public XgmmlDocument(File file) throws IOException, JAXBException {
		jc = JAXBContext.newInstance(GraphicGraph.class);
		factory = new ObjectFactory();
		
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        GraphicGraph graph = (GraphicGraph) unmarshaller.unmarshal(file);

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        marshaller.marshal(graph, System.out);
	}
	
	public XgmmlDocument() throws JAXBException {
		jc = JAXBContext.newInstance(GraphicGraph.class);
		factory = new ObjectFactory();
		graph = factory.createGraphicGraph();
	}
}

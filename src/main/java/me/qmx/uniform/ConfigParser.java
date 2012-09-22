package me.qmx.uniform;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ConfigParser {
    private final InputStream stream;

    public ConfigParser(InputStream stream) {
        this.stream = stream;
    }

    public Map<String, String> parse() {
        final Map<String, String> map = new HashMap<String, String>();
        final XMLInputFactory factory = XMLInputFactory.newFactory();
        try {
            final XMLStreamReader reader = factory.createXMLStreamReader(stream);
            while (reader.hasNext()) {
                final int eventCode = reader.next();
                switch (eventCode) {
                    case XMLStreamConstants.START_ELEMENT:
                        if ("setting".equals(reader.getLocalName())) {
                            if (checkAttributeFormat(reader)) {
                                map.put(reader.getAttributeValue(0), reader.getAttributeValue(1));
                            }
                        }
                        break;
                }
            }
            reader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return map;
    }

    private boolean checkAttributeFormat(XMLStreamReader reader) {
        return reader.getAttributeCount() == 2
                && "id".equals(reader.getAttributeLocalName(0))
                && "value".equals(reader.getAttributeLocalName(1));
    }
}

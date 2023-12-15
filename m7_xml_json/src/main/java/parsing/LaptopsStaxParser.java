package parsing;
import com.sun.xml.txw2.output.IndentingXMLStreamWriter;
import model.Laptop;
import model.Laptops;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LaptopsStaxParser {
    private Laptops laptops;
    private XMLStreamWriter streamWriter;

    public LaptopsStaxParser(Laptops laptops, String filePath) {
        this.laptops = laptops;
        XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
        try {
            streamWriter = new IndentingXMLStreamWriter(outputFactory.createXMLStreamWriter(new FileWriter(filePath)));
          //  staxWriteXML();
        } catch (XMLStreamException | IOException  e) {
            e.printStackTrace();
        }


    }

    public void staxWriteXML(){
        try {
            // Start het schrijven van de XML
            streamWriter.writeStartDocument();
            streamWriter.writeStartElement("Laptops");

            for (Laptop laptop : laptops.getAllLaptops()) {
                writeElement(laptop);
            }

            // Sluit XML-elementen
            streamWriter.writeEndElement();
            streamWriter.writeEndDocument();

            // Sluit de XMLStreamWriter
            streamWriter.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    private void writeElement(Laptop laptop) {

        try {
            streamWriter.writeStartElement("Laptop");
            streamWriter.writeAttribute("Category", laptop.getCategory().toString());

            writeProperty("name", laptop.getName());
            writeProperty("Colour", laptop.getColour());
            writeProperty("Price", String.valueOf(laptop.getPrice()));
            writeProperty("Ram", String.valueOf(laptop.getRam()));
            writeProperty("Storage", String.valueOf(laptop.getStorage()));
            writeProperty("ReleaseDate", laptop.getReleaseDate().toString());
            writeProperty("MacAddress", laptop.getMacAdres());

            streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }


    /**
     * Vermijd redundante code
     */
    private void writeProperty(String localName, String value) {
        try {
            streamWriter.writeStartElement(localName);
            streamWriter.writeCharacters(value);
            streamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();

        }
    }
}





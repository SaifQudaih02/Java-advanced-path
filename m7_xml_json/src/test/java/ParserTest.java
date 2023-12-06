import Data.Data;
import model.Laptop;
import model.Laptops;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import parsing.LaptopsDomParser;
import parsing.LaptopsGsonParser;
import parsing.LaptopsJaxbParser;
import parsing.LaptopsStaxParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    private Laptops laptops = new Laptops();
    private  String filePath;


    @BeforeEach
    void setup(){
        try {
            Data.getData().forEach(laptops::addLaptop);
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }

    }

    @Test
    void testStaxDom()  {
        filePath= "datafiles/staxLaptops.xml";
        // Stax parsing
        LaptopsStaxParser staxParser = new LaptopsStaxParser(laptops, filePath);
        staxParser.staxWriteXML();

        // Dom parsing
        Laptops parsedLaptops = LaptopsDomParser.domReadXML(filePath);

        // Vergelijk originalLaptops met parsedLaptops
        assertEquals(laptops.getSize(), parsedLaptops.getSize());
        assertEquals(laptops.getAllLaptops(), parsedLaptops.getAllLaptops());

    }

    @Test
    void testJaxb(){
        filePath = "datafiles/jaxbLaptops.xml";
        // writing laptops from XML using JAXB
        LaptopsJaxbParser.JaxbWriteXml(filePath, laptops);

        // Reading laptops from XML using JAXB
        Laptops parsedLaptops = LaptopsJaxbParser.JaxbReadXml(filePath, Laptops.class);

        assertEquals(laptops.getSize(), parsedLaptops.getSize());
//        assertDoesNotThrow(() -> LaptopsJaxbParser.JaxbWriteXml(filePath, laptops));
//        Laptops[] laptopList = new Laptops[1];
//        assertDoesNotThrow(() -> laptopList[0] = LaptopsJaxbParser.JaxbReadXml(filePath, Laptops.class));
//        assertEquals(laptops.getLaptopList(), laptopList[0].getLaptopList());
    }

    @Test
    void testGson() {
        filePath="datafiles/laptops.json";
        assertDoesNotThrow(() -> LaptopsGsonParser.writeJson(laptops, filePath));
        assertDoesNotThrow(() -> LaptopsGsonParser.readJson(filePath));
        assertEquals(laptops.getLaptopList(), LaptopsGsonParser.readJson(filePath).getLaptopList());



    }
}


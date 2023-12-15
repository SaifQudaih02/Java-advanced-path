package parsing;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class LaptopsJaxbParser {
    /**
     * Maak gebruik van een Marshaller om het meegegeven root-object te converteren
     * naar een nieuw XML-bestand.
     */
    public static void JaxbWriteXml(String file, Object root) {

        JAXBContext context;
        try {
            context = JAXBContext.newInstance(root.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(root, new File(file));

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass) {
        try {
            JAXBContext jc = JAXBContext.newInstance(typeParameterClass.getClasses());
            return (T) jc.createUnmarshaller().unmarshal(new FileReader(file));
//            Unmarshaller unmarshaller = jc.createUnmarshaller();
//            File file1 = new File(file);
//
//            Class<T> tP = (Class<T>) unmarshaller.unmarshal(file1);
//
//            return (T) tP;
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();

        } 
        return null;
    }

}

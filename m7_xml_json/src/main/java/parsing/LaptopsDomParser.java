package parsing;

import Data.Data;
import model.Category;
import model.Laptop;
import model.Laptops;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class LaptopsDomParser {
    public static Laptops domReadXML(String fileName) {
        try {

            Laptops laptops = new Laptops();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

            Document doc = builder.parse(new File(fileName));
            Element rootElement = doc.getDocumentElement();

            NodeList laptopNodes = rootElement.getChildNodes();
            for (int i = 0; i < laptopNodes.getLength(); i++) {
                if (laptopNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element element = (Element) laptopNodes.item(i);

                Category category = Category.valueOf(element.getAttribute("Category"));
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String colour = element.getElementsByTagName("Colour").item(0).getTextContent();
                double price = Double.parseDouble(element.getElementsByTagName("Price").item(0).getTextContent());
                int ram = Integer.parseInt(element.getElementsByTagName("Ram").item(0).getTextContent());
                int storage = Integer.parseInt(element.getElementsByTagName("Storage").item(0).getTextContent());
                LocalDate releaseDate = LocalDate.parse(element.getElementsByTagName("ReleaseDate").item(0).getTextContent());
                String macAddress = element.getElementsByTagName("MacAddress").item(0).getTextContent();

                Laptop laptop = new Laptop(name, colour, price, ram, storage, category, releaseDate, macAddress);

                laptops.addLaptop(laptop);

            }
            return laptops;
        }catch ( ParserConfigurationException| IOException | SAXException  e){
            e.printStackTrace();
        }
        return null;
    }
}




















        /*
        Laptops laptops = new Laptops();

        try {
            File xmlFile = new File(fileName);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            NodeList laptopNodes = document.getElementsByTagName("Laptop");

            for (int i = 0; i < laptopNodes.getLength(); i++) {
                Node laptopNode = laptopNodes.item(i);
                if (laptopNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element laptopElement = (Element) laptopNode;
                    Laptop laptop = parseLaptopElement(laptopElement);
                    laptops.setLaptop(laptop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return laptops;
    }

    private static Laptop parseLaptopElement(Element laptopElement) {
        Laptop laptop = new Laptop();

        laptop.setName(getElementValue(laptopElement, "Name"));
        laptop.setColour(getElementValue(laptopElement, "Colour"));
        laptop.setRam(Integer.parseInt(getElementValue(laptopElement, "RAM")));
        laptop.setPrice(Double.parseDouble(getElementValue(laptopElement, "Price")));
        laptop.setStorage(Integer.parseInt(getElementValue(laptopElement, "Storage")));
        laptop.setCategory(Category.valueOf(getAttribute(laptopElement, "Category")));
        laptop.setReleaseDate(LocalDate.parse(getElementValue(laptopElement, "ReleaseDate")));
        laptop.setMacAdres(getElementValue(laptopElement, "MacAddress"));

        return laptop;
    }

    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    private static String getAttribute(Element element, String attributeName) {
        return element.getAttribute(attributeName);
    }



            Laptop laptop = new Laptop(
                    e.getAttribute("name"),
                    e.getAttribute("colour"),
                    Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent()),
                    Integer.parseInt(e.getElementsByTagName("ram").item(0).getTextContent()),
                    Integer.parseInt(e.getElementsByTagName("storage").item(0).getTextContent()),
                    Category.valueOf(e.getElementsByTagName("category").item(0).getTextContent()),
                    LocalDate.parse(e.getElementsByTagName("releaseDate").item(0).getTextContent()),
                    e.getAttribute("macAdres")
            );

            laptops.setLaptop(laptop);
        }
        return laptops;


*/




package object;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class Topic {
    private String title;
    private String imageSource;
    private String content;
    private String link;
    public Topic(){}
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }
    public String getImageSource() {
        return this.imageSource;
    }
    public void setContent(String Content) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getLink() {
        return this.link;
    }
    public void parseData(Document doc){
        try {
            Node node = doc.getElementsByTagName("item").item(0);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element el = (Element) node;
                String title = el.getElementsByTagName("title").item(0).getTextContent();
                Element media = (Element) el.getElementsByTagName("media:content").item(0);
                String imageSource = media.getAttribute("url");
                Element contentEl = (Element) el.getElementsByTagName("description").item(0);
                String content = getValueFromCData(contentEl);
                String link = el.getElementsByTagName("link").item(0).getTextContent();
                setContent(content);
                setTitle(title);
                setImageSource(imageSource);
                setLink(link);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void show() {
        System.out.println("Title: " + this.title);
        System.out.println("Content: " + this.content);
        System.out.println("Image source: " + this.imageSource);
        System.out.println("Link: " + this.link);
    }
    public String getValueFromCData(Element e){
        NodeList list = e.getChildNodes();
        String data;

        for(int index = 0; index < list.getLength(); index++){
            if(list.item(index) instanceof CharacterData){
                CharacterData child = (CharacterData) list.item(index);
                data = child.getData();

                if(data != null && data.trim().length() > 0)
                    return child.getData();
            }
        }
        return "";
    }
}

package sg.edu.nus.iss.WS13Redo.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class Contact {

    private String id;
    private String name;
    private String email;
    private int phone;

    public Contact() {
        this.id = this.generateId(8);
    }

    public Contact(String name, String email, int phone) {
        this.id = this.generateId(8);
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Contact(String id, String name, String email, int phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    private synchronized String generateId(int numchars) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }

    public void saveContact(Contact contact, Environment env) throws IOException {
        String argPath = env.getProperty("dataDir");
        if(argPath != null){
        FileWriter fileWriter = new FileWriter(argPath + "/" + id, Charset.forName("utf-8"));
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(fileWriter);
            pw.println(contact.getName());
            pw.println(contact.getEmail());
            pw.println(contact.getPhone());
        } finally {
            pw.close();
        }
    }

    }

    public Contact getContactById(Contact contact, String contactId, Environment env) throws IOException{
        Contact cResp = new Contact();
        String argPath = env.getProperty("dataDir");
        if (argPath !=null){
        Path filepath = Paths.get(argPath +"/" + contactId);
        if(Files.exists(filepath)){
            List<String> stringList = Files.readAllLines(filepath,  Charset.forName("utf-8"));
                        cResp.setName(stringList.get(0));
                        cResp.setEmail(stringList.get(1));
                        cResp.setPhone(Integer.parseInt(stringList.get(2)));
        }else{
            return null;
        }
    }
        return cResp;
    
        
    }

}

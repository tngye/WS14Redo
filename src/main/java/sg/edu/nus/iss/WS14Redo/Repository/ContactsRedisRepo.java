package sg.edu.nus.iss.WS14Redo.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.WS14Redo.model.Contact;

@Service
public class ContactsRedisRepo implements RedisRepo{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String CONTACT_ENTITY = "contactlist";

    @Override
    public void save(final Contact ctc){
        redisTemplate.opsForList().leftPush(CONTACT_ENTITY, ctc.getId());
        redisTemplate.opsForHash().put(CONTACT_ENTITY + "_Map", ctc.getId(), ctc);
    }

    @Override
    public Contact findById(final String contactId){
        Contact result = (Contact)redisTemplate.opsForHash()
            .get(CONTACT_ENTITY + "_Map", contactId);
        return result;
    }

    @Override
    public List<Contact> findAll(int startIndex){
        List<Object> fromContactList = redisTemplate.opsForList()
            .range(CONTACT_ENTITY, startIndex, startIndex + 9);
        List<Contact> ctcs = 
            (List<Contact>)redisTemplate.opsForHash()
                .multiGet(CONTACT_ENTITY + "_Map", fromContactList)
                .stream()
                .filter(Contact.class::isInstance)
                .map(Contact.class::cast)
                .toList();

        return ctcs;
    }

    // public void save(Contact contact) {
    //     template.opsForList().leftPush(contact.getId(), contact);
    // }

    // public List<Contact> getContact(int endIndex) {
    //     List<Contact> allContacts = template.opsForList().range(contact.getId(), 0, endIndex);
    // }
}

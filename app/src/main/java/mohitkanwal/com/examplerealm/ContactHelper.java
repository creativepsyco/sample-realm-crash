package mohitkanwal.com.examplerealm;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * User: msk
 * Date: 28/11/14
 */
public class ContactHelper {
    private static final int MAX_FIXTURES = 500;

    public static Contact getOrCreateContactModel(Integer id) {
        Realm realm = Realm.getInstance(ExampleApp.getApplication());
        RealmQuery<Contact> query = realm.where(Contact.class);
        query.equalTo("id", id.longValue());
        Contact theContact = query.findFirst();
        if (theContact == null) {
            theContact = realm.createObject(Contact.class);
            theContact.setId(id);
        }
        return theContact;
    }


    public static Contact getContactById(long id) {
        Realm realm = Realm.getInstance(ExampleApp.getApplication());
        RealmQuery<Contact> query = realm.where(Contact.class);
        query.equalTo("id", id);
        return query.findFirst();
    }

    public static List<Contact> getAllContacts() {
        RealmQuery<Contact> query = Realm.getInstance(ExampleApp.getApplication())
                .where(Contact.class);
        return query.findAll().sort("firstName", RealmResults.SORT_ORDER_ASCENDING);
    }

    public static String getDisplayName(Contact data) {
        return data.getFirstName() + " " + data.getLastName();
    }

    private static SecureRandom random = new SecureRandom();

    public static void populate() {
        Realm realm = Realm.getInstance(ExampleApp.getApplication());
        for (int i = 0; i < MAX_FIXTURES; i++) {
            realm.beginTransaction();
            Contact contact = realm.createObject(Contact.class);
            contact.setFirstName(new BigInteger(130, random).toString(32));
            contact.setLastName(new BigInteger(130, random).toString(32));
            contact.setEmail(new BigInteger(130, random).toString(16) + "@" + "mohitkanwal.com");
            contact.setId(new BigInteger(130, random).intValue());
            realm.commitTransaction();
        }

    }
}

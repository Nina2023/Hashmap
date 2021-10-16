package a8;

import java.util.*;

public class PasswordManager<K,V> implements Map<K,V> {
    private static final String MASTER_PASSWORD = "YOUR PASSWORD HERE";
    private Account[] _passwords;

    public PasswordManager() {
        _passwords = new Account[50];
    }
    private int _size;


    // TODO: put
    @Override
    public void put(K key, V value) {
        Account One = new Account(key, value);
        // index for account object
       int index = Math.abs(key.hashCode())%_passwords.length;
       if (_passwords[index] == null){
           _passwords[index] = One;
           _size += 1;
       } else{
           Account Temp = _passwords[index];
           if(Temp.getWebsite().equals(key)){
               Temp.setPassword(value);
               return;

           }
           while(Temp.getNext() != null){
               if(Temp.getWebsite().equals(key)){
                   Temp.setPassword(value);
                   return;

               }
               Temp = Temp.getNext();
           }
           Temp.setNext(One);
           _size += 1;
       }
    }

    // TODO: get
    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode())%50;
        if(_passwords[index]== null){
            return null;
        }
        Account One = _passwords[index];
        while(One != null){
            if(One.getWebsite().equals(key)){
                return (V) One.getPassword();
            }
            One = One.getNext();
        }
        return null;
    }

    // TODO: size
    @Override
    public int size() {
        return _size;
    }

    // TODO: keySet
    @Override
    public Set<K> keySet() {
        Set<K> newset = new HashSet<K>();
        for (int i = 0; i < _passwords.length; i++) {
            Account temp = _passwords[i];
             while(temp != null){
                 newset.add((K)temp.getWebsite());
                 temp = temp.getNext();
             }
         }
         return newset;

    }

    // TODO: remove (same linked list)
    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode())%_passwords.length;
        V returnvalue = null;
        Account temp = _passwords[index];
        if (temp == null){
            return null;
        }
        if (temp.getWebsite().equals(key)){
            _size--;
            returnvalue = (V) temp.getPassword();
            _passwords[index] = temp.getNext();
            return returnvalue;
        } else{
            while(temp.getNext() != null){
                if(temp.getNext().getWebsite().equals(key)){
                    returnvalue = (V) temp.getNext().getPassword();
                    temp.setNext(temp.getNext().getNext());
                    _size--;
                    return returnvalue ;
                }
                temp = temp.getNext();
            }
        }
        return null;
    }

    // TODO: checkDuplicate
    @Override
    public List<K> checkDuplicate(V value) {
        List<K> Timmyboy = new ArrayList<K>();

        for(int i = 0; i < _passwords.length; i++){
            Account Teapot = _passwords[i];
            while(Teapot != null){
                if(Teapot.getPassword().equals(value)){
                    Timmyboy.add((K)Teapot.getWebsite());
                }
                Teapot= Teapot.getNext();
            }
        }
        return Timmyboy;
    }

    // TODO: checkMasterPassword
    @Override
    public boolean checkMasterPassword(String enteredPassword) {
        return MASTER_PASSWORD.equals(enteredPassword);
    }

    /*
    Generates random password of input length
     */
    @Override
    public String generateRandomPassword(int length) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    /*
    Used for testing, do not change
     */
    public Account[] getPasswords() {
        return _passwords;
    }
}
/*
public void put (K key, V vaule){
Account Temp = new Account (key, value);
int index = (Math.abs(key.hashcode())) % _passwords.length);
if(_passwords[index] == null){
_passwords[index] = Temp;
} else{
if(_passwords[temp].getWebsite.equals(key)){
_passwords[index]= Temp;
}
{

 */
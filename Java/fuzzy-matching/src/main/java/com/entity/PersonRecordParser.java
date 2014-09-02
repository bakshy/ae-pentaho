package com.entity;

import org.apache.hadoop.io.Text;

/**
 *  1) Parse data from string
 *  2) Create match-code :  If len(LastName) then match-code =  LastName else  match-code = First leter of LastName + last vowels of LastName
 *          example 1) Petrov -> match-code = ptrv
 *                  2) Kim    -> match-code = kim
 *
 *
 */
public class PersonRecordParser {

    private Person person;

    public PersonRecordParser()
    {
        person = new Person();
    }

    public Person getPerson()
    {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Parse input data.
     * Clearing input data.
     *
     * @param record
     */


    public void parseInData(String record)
    {

        String[] token =  record.split(",");
        int length =  token.length;
        //if record does not have lastname then exit
        if (length<4)
            return ;
        // put in to person  main data
        if (length>=4)
        {
            person.setId(token[0]);
            person.setFirstName(regExprAlpha(token[1]));
            person.setMidName(regExprAlpha(token[2]));
            person.setLastName(regExprAlpha(token[3]));
        }
        if (length>=5)
            person.setEmail(token[4]);
        if (length>=6)
            person.setPhone1(regExprDigit(token[5]));
        if (length>=7)
            person.setPhone2(regExprDigit(token[6]));

    }


    public void parseInData(Text record) {
        parseInData(record.toString());
    }

    public String getKey(){

        if (person.getId() == null)
            return null;


        String lastName  = person.getLastName().toLowerCase();
        String key ;
        int length = lastName.length();

        if (lastName == null || length <= 0) {
            key = null;
        } else if (length <= 4) {
            key =  lastName;
        } else {
            key =  lastName.substring(0, 1) + regExprVowels(lastName.substring(1, length));
        }

        return key;
    }


    public String  getValue() {

        if (person.getId() == null)
            return null;

        String  value = person.getId() + ","
                +person.getFirstName() + ","
                +person.getMidName() + ","
                +person.getLastName() +","
                +person.getEmail() +","
                +person.getPhone1()+","
                +person.getPhone2();
        return value;
    }



    protected   String subString(String item, int len)
    {
        final String value;

        if (item == null || item.length() <= 0) {
            value = "";

        } else if (item.length() <= len) {
            value = item;

        } else {
            value = item.substring(0,len);
        }
        return value;
    }

    public String regExprAlpha(String world)
    {
        return world.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "");
    }

    public String regExprDigit(String world)
    {
        return world.replaceAll("[^0-9]", "");
    }

    public String regExprVowels(String world)
    {
       return world.replaceAll("[${aeiouAEIOUаеёиоыэуюяАЕЁИОЫЭУЮЯ}}]", "");
    }
}

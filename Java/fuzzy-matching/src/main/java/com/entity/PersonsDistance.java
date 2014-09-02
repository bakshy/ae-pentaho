package com.entity;


/**
 * Created by user1 on 21.08.14.
 */
public class PersonsDistance {

    /*

    recordPerson[0] - id
    recordPerson[1] - Name
    recordPerson[2] - MidName
    recordPerson[3] - LastName
    recordPerson[4] - email
    recordPerson[5] - phone1
    recordPerson[6] - phone2

     */


    public String  calcDistance(String strPerson1 , String strPerson2) {

        String strDistance ="";
        int distance = 0 ;
        int sumDistances= 0;

        String[] recordPerson1 = strPerson1.split(",");
        String[] recordPerson2 = strPerson2.split(",");


        int minIndex =   Math.min(recordPerson1.length, recordPerson2.length);

        for (int i= 1 ; i< minIndex; i++)
        {
            distance = LevenshteinDistance.distance(recordPerson1[i], recordPerson2[i]);
            // create string with
            if (i < 5 ) strDistance = strDistance + "," + distance;
            // calculate Distance from Name, MidName, LastName
            if (i < 4 ) sumDistances= sumDistances +  distance *distance;

        }

        strDistance = Math.round(Math.sqrt(sumDistances)) + strDistance;
        return strDistance;

    }


    }
